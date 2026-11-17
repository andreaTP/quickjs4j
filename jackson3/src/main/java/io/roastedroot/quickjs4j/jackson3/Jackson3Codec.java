package io.roastedroot.quickjs4j.jackson3;

import io.roastedroot.quickjs4j.core.JsonCodec;
import io.roastedroot.quickjs4j.core.JsonCodecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.json.JsonMapper;

/** {@link JsonCodec} backed by a Jackson 3 {@link ObjectMapper}. */
public final class Jackson3Codec implements JsonCodec {
    private static final String JACKSON2_PACKAGE = "com.fasterxml.jackson.databind.";

    private final ObjectMapper mapper;
    private final ObjectReader treeReader;
    private final Map<Class<?>, ObjectWriter> writers = new ConcurrentHashMap<>();

    /** Uses Jackson 2 defaults, as the Jackson 2 codec. */
    public Jackson3Codec() {
        this(JsonMapper.builderWithJackson2Defaults().build());
    }

    public Jackson3Codec(ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "mapper");
        // untrusted JS must not choose the classes to instantiate
        if (mapper.deserializationConfig().getDefaultTyper(mapper.constructType(Object.class))
                != null) {
            throw new IllegalArgumentException(
                    "ObjectMapper with default typing enabled is not supported");
        }
        this.treeReader = mapper.reader().with(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }

    public ObjectMapper mapper() {
        return mapper;
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public String toJson(Object value, Class<?> type) {
        try {
            if (type == Object.class) {
                if (value != null) {
                    rejectJackson2(value.getClass());
                }
                return mapper.writeValueAsString(value);
            }
            rejectJackson2(type);
            return writers.computeIfAbsent(type, mapper::writerFor).writeValueAsString(value);
        } catch (JacksonException e) {
            throw new JsonCodecException("Failed to serialize value as " + type, e);
        }
    }

    @Override
    public List<Object> fromJsonArray(String json, List<Class<?>> types) {
        types.forEach(Jackson3Codec::rejectJackson2);
        try {
            var array = treeReader.readTree(json);
            if (array == null || !array.isArray()) {
                throw new JsonCodecException("Expected a JSON array");
            }
            var values = new ArrayList<Object>(types.size());
            for (int i = 0; i < types.size(); i++) {
                var element = array.get(i);
                values.add(element == null ? null : mapper.treeToValue(element, types.get(i)));
            }
            return values;
        } catch (JacksonException e) {
            throw new JsonCodecException("Failed to deserialize JSON as " + types, e);
        }
    }

    private static void rejectJackson2(Class<?> type) {
        if (type.getName().startsWith(JACKSON2_PACKAGE)) {
            throw new IllegalArgumentException(
                    type.getName()
                            + " is a Jackson 2 type, use the Jackson 2 codec from"
                            + " io.roastedroot:quickjs4j-jackson2");
        }
    }
}

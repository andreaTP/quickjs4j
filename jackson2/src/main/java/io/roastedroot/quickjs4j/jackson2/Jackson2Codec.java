package io.roastedroot.quickjs4j.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.roastedroot.quickjs4j.core.JsonCodec;
import io.roastedroot.quickjs4j.core.JsonCodecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/** {@link JsonCodec} backed by a Jackson 2 {@link ObjectMapper}. */
public final class Jackson2Codec implements JsonCodec {
    private final ObjectMapper mapper;
    private final ObjectReader treeReader;
    private final Map<Class<?>, ObjectWriter> writers = new ConcurrentHashMap<>();

    public Jackson2Codec() {
        this(new ObjectMapper());
    }

    public Jackson2Codec(ObjectMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "mapper");
        // untrusted JS must not choose the classes to instantiate
        if (mapper.getDeserializationConfig().getDefaultTyper(mapper.constructType(Object.class))
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
        return 2;
    }

    @Override
    public String toJson(Object value, Class<?> type) {
        try {
            if (type == Object.class) {
                return mapper.writeValueAsString(value);
            }
            return writers.computeIfAbsent(type, mapper::writerFor).writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonCodecException("Failed to serialize value as " + type, e);
        }
    }

    @Override
    public List<Object> fromJsonArray(String json, List<Class<?>> types) {
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
        } catch (JsonProcessingException e) {
            throw new JsonCodecException("Failed to deserialize JSON as " + types, e);
        }
    }
}

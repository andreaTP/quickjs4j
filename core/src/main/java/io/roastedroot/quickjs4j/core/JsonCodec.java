package io.roastedroot.quickjs4j.core;

import java.util.List;

/** Maps values to and from JSON; implementations must be thread-safe. */
public interface JsonCodec {

    /** {@code value} may be {@code null}; {@code Object.class} means its runtime type. */
    String toJson(Object value, Class<?> type);

    /**
     * Decodes a JSON array, element {@code i} as {@code types.get(i)}: returns {@code
     * types.size()} values, {@code null} for missing elements, ignoring extra ones. Must reject
     * trailing content.
     */
    List<Object> fromJsonArray(String json, List<Class<?>> types);

    /** The highest priority wins when several codecs are found with ServiceLoader. */
    default int priority() {
        return 0;
    }
}

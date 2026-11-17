package io.roastedroot.quickjs4j.core;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class JsonCodecsTest {

    private static JsonCodec codec(int priority) {
        return new JsonCodec() {
            @Override
            public String toJson(Object value, Class<?> type) {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<Object> fromJsonArray(String json, List<Class<?>> types) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int priority() {
                return priority;
            }
        };
    }

    @Test
    public void noCodec() {
        var ex = assertThrows(IllegalStateException.class, () -> JsonCodecs.select(List.of()));
        assertTrue(ex.getMessage().contains("io.roastedroot:quickjs4j-jackson2"));
        assertTrue(ex.getMessage().contains("io.roastedroot:quickjs4j-jackson3"));
    }

    @Test
    public void singleCodec() {
        var codec = codec(0);
        assertSame(codec, JsonCodecs.select(List.of(codec)));
    }

    @Test
    public void highestPriorityWins() {
        var jackson2 = codec(2);
        var jackson3 = codec(3);
        assertSame(jackson3, JsonCodecs.select(List.of(jackson2, jackson3)));
        assertSame(jackson3, JsonCodecs.select(List.of(jackson3, jackson2)));
    }

    @Test
    public void samePriorityIsAmbiguous() {
        var ex =
                assertThrows(
                        IllegalStateException.class,
                        () -> JsonCodecs.select(List.of(codec(1), codec(1), codec(0))));
        assertTrue(ex.getMessage().contains("withJsonCodec"));
    }

    @Test
    public void discoveredThroughServiceLoader() {
        try (var engine = Engine.builder().build()) {
            assertInstanceOf(TestJsonCodec.class, engine.jsonCodec());
        }
    }

    @Test
    public void explicitCodec() {
        var codec = new TestJsonCodec();
        try (var engine = Engine.builder().withJsonCodec(codec).build()) {
            assertSame(codec, engine.jsonCodec());
        }
    }
}

package com.github.jinahya.jsonrpc.bind.v2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.github.jinahya.jsonrpc.bind.v2.BeanValidationUtils.requireValid;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public final class JsonTests {

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> void doWithResource(final String resourceName, final Class<? extends T> valueType,
                                          final Consumer<? super T> valueConsumer)
            throws IOException {
        final List<T> list = new ArrayList<>();
        {
            final T value = JsonbUtils.fromResource(resourceName, valueType);
            log.debug("value: {}", value);
            requireValid(value);
            list.add(value);
            final String json = JsonbUtils.JSONB.toJson(value);
            log.debug("jsonb: {}", json);
        }
        {
            final T value = JacksonUtils.readResource(resourceName, valueType);
            log.debug("value: {}", value);
            requireValid(value);
            list.add(value);
            final String json = JacksonUtils.OBJECT_MAPPER.writeValueAsString(value);
            log.debug("jackson: {}", json);
        }
        for (final T value1 : list) {
            if (valueConsumer != null) {
                valueConsumer.accept(value1);
            }
            for (final T value2 : list) {
                assertEquals(value1, value2);
            }
        }
    }

    public static <T> void doWithResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        doWithResource(resourceName, valueType, null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonTests() {
        super();
    }
}

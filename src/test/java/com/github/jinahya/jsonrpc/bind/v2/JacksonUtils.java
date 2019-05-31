package com.github.jinahya.jsonrpc.bind.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Slf4j
public final class JacksonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <R> R applyObjectMapper(final Function<? super ObjectMapper, ? extends R> function) {
        return function.apply(OBJECT_MAPPER);
    }

    public static <U, R> R applyObjectMapper(final Supplier<? extends U> supplier,
                                             final BiFunction<? super ObjectMapper, ? super U, ? extends R> function) {
        return applyObjectMapper(v -> function.apply(v, supplier.get()));
    }

    public void acceptObjectMapper(final Consumer<? super ObjectMapper> consumer) {
        applyObjectMapper(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public <U> void acceptObjectMapper(final Supplier<? extends U> supplier,
                                       final BiConsumer<? super ObjectMapper, ? super U> consumer) {
        acceptObjectMapper(v -> consumer.accept(v, supplier.get()));
    }

    public static <T> T readResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resourceStream = JacksonUtils.class.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream);
            return OBJECT_MAPPER.readValue(resourceStream, valueType);
        }
    }

    private JacksonUtils() {
        super();
    }
}

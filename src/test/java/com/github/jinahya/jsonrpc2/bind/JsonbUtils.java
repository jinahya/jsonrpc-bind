package com.github.jinahya.jsonrpc2.bind;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonbUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static final Jsonb JSONB = JsonbBuilder.create();

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyJsonb(final Function<? super Jsonb, ? extends R> function) {
        return function.apply(JSONB);
    }

    public static <U, R> R applyJsonb(final Supplier<? extends U> supplier,
                                      final BiFunction<? super Jsonb, ? super U, ? extends R> function) {
        return applyJsonb(v -> function.apply(v, supplier.get()));
    }

    public void acceptJsonb(final Consumer<? super Jsonb> consumer) {
        applyJsonb(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public <U> void acceptJsonb(final Supplier<? extends U> supplier,
                                final BiConsumer<? super Jsonb, ? super U> consumer) {
        acceptJsonb(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T fromJson(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resourceStream = JacksonUtils.class.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream);
            return applyJsonb(v -> v.fromJson(resourceStream, valueType));
        }
    }
}

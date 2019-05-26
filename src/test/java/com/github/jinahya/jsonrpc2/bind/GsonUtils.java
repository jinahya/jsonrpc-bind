package com.github.jinahya.jsonrpc2.bind;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Slf4j
public final class GsonUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static final Gson GSON = new Gson();

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyGson(final Function<? super Gson, ? extends R> function) {
        return function.apply(GSON);
    }

    public static <U, R> R applyGson(final Supplier<? extends U> supplier,
                                     final BiFunction<? super Gson, ? super U, ? extends R> function) {
        return applyGson(v -> function.apply(v, supplier.get()));
    }

    public void acceptGson(final Consumer<? super Gson> consumer) {
        applyGson(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public <U> void acceptGson(final Supplier<? extends U> supplier,
                               final BiConsumer<? super Gson, ? super U> consumer) {
        acceptGson(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T fromResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resourceStream = GsonUtils.class.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream);
            try (InputStreamReader streamReader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)) {
                return applyGson(v -> v.fromJson(streamReader, valueType));
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private GsonUtils() {
        super();
    }
}

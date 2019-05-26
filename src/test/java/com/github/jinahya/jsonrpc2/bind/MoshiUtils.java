package com.github.jinahya.jsonrpc2.bind;

import com.squareup.moshi.Moshi;
import okio.Okio;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class MoshiUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static final Moshi MOSHI = new Moshi.Builder().build();

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyMoshi(final Function<? super Moshi, ? extends R> function) {
        return function.apply(MOSHI);
    }

    public static <U, R> R applyMoshi(final Supplier<? extends U> supplier,
                                      final BiFunction<? super Moshi, ? super U, ? extends R> function) {
        return applyMoshi(v -> function.apply(v, supplier.get()));
    }

    public void acceptMoshi(final Consumer<? super Moshi> consumer) {
        applyMoshi(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public <U> void acceptMoshi(final Supplier<? extends U> supplier,
                                final BiConsumer<? super Moshi, ? super U> consumer) {
        acceptMoshi(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T fromResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resourceStream = MoshiUtils.class.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream);
            return MOSHI.adapter(valueType).fromJson(Okio.buffer(Okio.source(resourceStream)));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private MoshiUtils() {
        super();
    }
}

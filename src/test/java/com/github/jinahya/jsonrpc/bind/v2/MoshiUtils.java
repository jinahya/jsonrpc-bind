package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lombok.extern.slf4j.Slf4j;
import okio.Okio;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public final class MoshiUtils {

    public static final Moshi MOSHI = new Moshi.Builder().build(); // https://github.com/square/moshi/issues/468

    public static <R> R applyMoshi(final Function<? super Moshi, ? extends R> function) {
        return function.apply(MOSHI);
    }

    public static <U, R> R applyMoshi(final Supplier<? extends U> supplier,
                                      final BiFunction<? super Moshi, ? super U, ? extends R> function) {
        return applyMoshi(v -> function.apply(v, supplier.get()));
    }

    public static void acceptMoshi(final Consumer<? super Moshi> consumer) {
        applyMoshi(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public static <U> void acceptMoshi(final Supplier<? extends U> supplier,
                                       final BiConsumer<? super Moshi, ? super U> consumer) {
        acceptMoshi(v -> consumer.accept(v, supplier.get()));
    }

    public static <T> T fromResource(final String resourceName, final Class<T> valueClass,
                                     final BiConsumer<? super T, ? super String> valueConsumer)
            throws IOException {
        try (InputStream resourceStream = valueClass.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream, "null resource stream for " + resourceName);
            final JsonAdapter<T> adapter = MOSHI.adapter(valueClass);
            final T value = adapter.fromJson(Okio.buffer(Okio.source(resourceStream)));
            final String string = adapter.toJson(value);
            log.debug("moshi: {}", value);
            log.debug("moshi: {}", string);
            valueConsumer.accept(value, string);
            return value;
        }
    }

    public static <T> T fromResource(final String resourceName, final Class<T> valueClass,
                                     final Consumer<? super String> stringConsumer)
            throws IOException {
        return fromResource(resourceName, valueClass, (v, s) -> stringConsumer.accept(s));
    }

    public static <T> T fromResource(final String resourceName, final Class<T> valueClass)
            throws IOException {
        return fromResource(resourceName, valueClass, s -> {
        });
    }

    private MoshiUtils() {
    }
}

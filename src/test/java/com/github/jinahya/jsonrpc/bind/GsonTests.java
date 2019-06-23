package com.github.jinahya.jsonrpc.bind;

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

import static com.github.jinahya.jsonrpc.bind.BeanValidationUtils.requireValid;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public final class GsonTests {

    // -----------------------------------------------------------------------------------------------------------------
    public static final Gson GSON = new Gson(); // thread-safe!

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyGson(final Function<? super Gson, ? extends R> function) {
        return function.apply(GSON);
    }

    public static <U, R> R applyGson(final Supplier<? extends U> supplier,
                                     final BiFunction<? super Gson, ? super U, ? extends R> function) {
        return applyGson(v -> function.apply(v, supplier.get()));
    }

    public static void acceptGson(final Consumer<? super Gson> consumer) {
        applyGson(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public static <U> void acceptGson(final Supplier<? extends U> supplier,
                                      final BiConsumer<? super Gson, ? super U> consumer) {
        acceptGson(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T fromResource(final String resourceName, final Class<? extends T> valueClass)
            throws IOException {
        try (InputStream resourceStream = valueClass.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream, "null resource stream for " + resourceName);
            return applyGson(v -> {
                final T value = requireValid(v.fromJson(
                        new InputStreamReader(resourceStream, StandardCharsets.UTF_8), valueClass));
                final String string = v.toJson(value);
                log.debug("gson: {}", value);
                log.debug("gson: {}", string);
                return value;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private GsonTests() {
        super();
    }
}

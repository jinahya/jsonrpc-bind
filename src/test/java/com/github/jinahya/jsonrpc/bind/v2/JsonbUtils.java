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

public final class JsonbUtils {

    public static final Jsonb JSONB = JsonbBuilder.create();

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

    /**
     * Opens a resource of specified name and returns an instance of specified type read from it.
     *
     * @param resourceName the name of the resource to open.
     * @param valueType    the type of the value to read.
     * @param <T>          value type parameter.
     * @return an instance of parsed value of specified type.
     * @throws IOException if an I/O error occurs.
     */
    public static <T> T fromResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resourceStream = JacksonUtils.class.getResourceAsStream(resourceName)) {
            assertNotNull(resourceStream);
            return applyJsonb(v -> v.fromJson(resourceStream, valueType));
        }
    }

    private JsonbUtils() {
        super();
    }
}

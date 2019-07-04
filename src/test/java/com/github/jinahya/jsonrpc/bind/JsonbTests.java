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

import lombok.extern.slf4j.Slf4j;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.github.jinahya.jsonrpc.bind.BeanValidationTests.requireValid;
import static com.github.jinahya.jsonrpc.bind.JsonrpcTests.applyResourceStream;

/**
 * Constants and utilities for JSON-B.
 */
@Slf4j
public final class JsonbTests {

    // -----------------------------------------------------------------------------------------------------------------
    public static final Jsonb JSONB = JsonbBuilder.create();

    static {
        log.debug("JSON: {}", JSONB);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyJsonb(final Function<? super Jsonb, ? extends R> function) {
        return function.apply(JSONB);
    }

    public static <U, R> R applyJsonb(final Supplier<? extends U> supplier,
                                      final BiFunction<? super Jsonb, ? super U, ? extends R> function) {
        return applyJsonb(v -> function.apply(v, supplier.get()));
    }

    public static void acceptJsonb(final Consumer<? super Jsonb> consumer) {
        applyJsonb(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public static <U> void acceptJsonb(final Supplier<? extends U> supplier,
                                       final BiConsumer<? super Jsonb, ? super U> consumer) {
        acceptJsonb(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Opens a resource of specified name and returns an instance of specified type read from it.
     *
     * @param resourceName the name of the resource to open.
     * @param valueClass   the type of the value to read.
     * @param <T>          value type parameter
     * @return an instance of parsed value of specified type.
     * @throws IOException if an I/O error occurs.
     */
    public static <T> T fromResource(final String resourceName, final Class<? extends T> valueClass)
            throws IOException {
        return applyResourceStream(
                resourceName,
                s -> applyJsonb(v -> {
                    final T value = v.fromJson(s, valueClass);
                    final String string = v.toJson(value);
                    log.debug("jsonb: {}", value);
                    log.debug("jsonb: {}", string);
                    return requireValid(value);
                })
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonbTests() {
        super();
    }
}

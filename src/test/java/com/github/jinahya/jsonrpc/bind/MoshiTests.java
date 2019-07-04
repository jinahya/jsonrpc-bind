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

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import lombok.extern.slf4j.Slf4j;
import okio.Okio;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.github.jinahya.jsonrpc.bind.JsonrpcTests.applyResourceStream;

@Slf4j
public final class MoshiTests {

    // -----------------------------------------------------------------------------------------------------------------
    // https://github.com/square/moshi/issues/866
    private static class VoidAdapter extends JsonAdapter<Void> {

        @Override
        public Void fromJson(final JsonReader reader) throws IOException {
            reader.skipValue();
            return null;
        }

        @Override
        public void toJson(final JsonWriter writer, final Void value) throws IOException {
            writer.nullValue();
        }
    }

    // https://github.com/square/moshi/issues/468
    public static final Moshi MOSHI = new Moshi.Builder().add(Void.class, new VoidAdapter()).build();

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyMoshi(final Function<? super Moshi, ? extends R> function) {
        return function.apply(MOSHI);
    }

    public static <U, R> R applyMoshi(final BiFunction<? super Moshi, ? super U, ? extends R> function,
                                      final Supplier<? extends U> supplier) {
        return applyMoshi(v -> function.apply(v, supplier.get()));
    }

    public static void acceptMoshi(final Consumer<? super Moshi> consumer) {
        applyMoshi(v -> {
            consumer.accept(v);
            return null;
        });
    }

    public static <U> void acceptMoshi(final BiConsumer<? super Moshi, ? super U> consumer,
                                       final Supplier<? extends U> supplier) {
        acceptMoshi(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T fromResource(final String resourceName, final Class<T> valueClass) throws IOException {
        return applyResourceStream(
                resourceName,
                s -> {
                    try {
                        final JsonAdapter<T> adapter = MOSHI.adapter(valueClass);
                        final T value = adapter.fromJson(Okio.buffer(Okio.source(s)));
                        final String string = adapter.toJson(value);
                        log.debug("moshi: {}", value);
                        log.debug("moshi: {}", string);
                        return value;
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                }
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private MoshiTests() {
        super();
    }
}

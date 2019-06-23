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

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public final class JsonpTests {

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyJsonReaderOfResource(final Class<?> caller, final String name,
                                                  final Function<? super JsonReader, ? extends R> function)
            throws IOException {
        try (InputStream resourceStream = caller.getResourceAsStream(name)) {
            assertNotNull(resourceStream, "null resource stream for " + name);
            try (JsonReader jsonReader = Json.createReader(resourceStream)) {
                return function.apply(jsonReader);
            }
        }
    }

    public static <U, R> R applyJsonReaderOfResource(
            final Class<?> caller, final String name, final Supplier<? extends U> supplier,
            final BiFunction<? super JsonReader, ? super U, ? extends R> function)
            throws IOException {
        return applyJsonReaderOfResource(caller, name, v -> function.apply(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static JsonStructure readJsonStructureFromResource(final Class<?> caller, final String name)
            throws IOException {
        return applyJsonReaderOfResource(caller, name, JsonReader::read);
    }

    public static JsonObject readJsonObjectFromResource(final Class<?> caller, final String name) throws IOException {
        return applyJsonReaderOfResource(caller, name, JsonReader::readObject);
    }

    public static JsonArray readJsonArrayFromResource(final Class<?> caller, final String name) throws IOException {
        return applyJsonReaderOfResource(caller, name, JsonReader::readArray);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonpTests() {
        super();
    }
}
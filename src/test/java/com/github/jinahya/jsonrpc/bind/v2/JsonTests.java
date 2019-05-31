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

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.github.jinahya.jsonrpc.bind.v2.BeanValidationUtils.requireValid;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public final class JsonTests {

    public static <T> void doWithResource(final String resourceName, final Class<? extends T> valueType,
                                          final Consumer<? super T> valueConsumer)
            throws IOException {
        final List<T> list = new ArrayList<>();
        {
            final T value = JsonbUtils.fromResource(resourceName, valueType);
            log.debug("value: {}", value);
            requireValid(value);
            list.add(value);
            final String json = JsonbUtils.JSONB.toJson(value);
            log.debug("jsonb: {}", json);
        }
        {
            final T value = JacksonUtils.readResource(resourceName, valueType);
            log.debug("value: {}", value);
            requireValid(value);
            list.add(value);
            final String json = JacksonUtils.OBJECT_MAPPER.writeValueAsString(value);
            log.debug("jackson: {}", json);
        }
        for (final T value1 : list) {
            if (valueConsumer != null) {
                valueConsumer.accept(value1);
            }
            for (final T value2 : list) {
                assertEquals(value1, value2);
            }
        }
    }

    public static <T> void doWithResource(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        doWithResource(resourceName, valueType, null);
    }

    private JsonTests() {
        super();
    }
}

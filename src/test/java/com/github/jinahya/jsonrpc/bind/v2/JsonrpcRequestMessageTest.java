package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.fromJson;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.newInstance;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.toJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class JsonrpcRequestMessageTest {

    @Test
    void testNewInstance() {
        final JsonrpcRequestMessage message = newInstance();
        assertNotNull(message); // dummy
    }

    @Test
    void testFromJson() {
        final JsonrpcRequestMessage message = fromJson(new Object());
        assertNull(message); // dummy
    }

    @Test
    void testToJson() {
        toJson(newInstance(), new StringWriter());
    }
}

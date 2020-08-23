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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class JsonrpcMessageTest<T extends JsonrpcMessage> extends JsonrpcObjectTest<T> {

    JsonrpcMessageTest(final Class<T> objectClass) {
        super(objectClass);
    }

    @Test
    void testGetJsonrpc() {
        {
            final String jsonrpc = objectInstance().getJsonrpc();
            assertEquals(JsonrpcMessageConstants.PROPERTY_VALUE_JSONRPC, jsonrpc);
        }
        {
            final String jsonrpc = validationProxy().getJsonrpc();
            assertEquals(JsonrpcMessageConstants.PROPERTY_VALUE_JSONRPC, jsonrpc);
        }
    }

    @Test
    void testSetJsonrpc() {
        assertDoesNotThrow(() -> objectInstance().setJsonrpc(null));
    }

    JsonrpcMessage validationProxy() {
        return (JsonrpcMessage) super.validationProxy();
    }
}

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

import java.util.ArrayList;

abstract class AbstractJsonrpcRequestMessageTest<T extends AbstractJsonrpcRequestMessage>
        extends AbstractJsonrpcMessageTest<T> {

    AbstractJsonrpcRequestMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }

    @Test
    void testIsNotification() {
        final boolean result = newInstance().isNotification();
    }

    @Test
    void testGetMethod() {
        final String method = newInstance().getMethod();
    }

    @Test
    void testSetMethod() {
        newInstance().setMethod("method");
        newInstance().setMethod(null);
    }

    @Test
    void testIsMethodReservedForRpcInternal() {
        newInstance().isMethodReservedForRpcInternal();
    }

    @Test
    void testHasParams() {
        newInstance().hasParams();
    }

    @Test
    void testIsParamsContextuallyValid() {
        newInstance().isParamsContextuallyValid();
    }

    @Test
    void testGetParamsAsArray() {
        newInstance().getParamsAsArray(Object.class);
    }

    @Test
    void testSetParamsAsArray() {
        newInstance().setParamsAsArray(new ArrayList<>());
        newInstance().setParamsAsArray(null);
    }

    @Test
    void testGetParamsAsObject() {
        newInstance().getParamsAsObject(Object.class);
    }

    @Test
    void testSetParamsAsObject() {
        newInstance().setParamsAsObject(new Object());
        newInstance().setParamsAsObject(null);
    }
}

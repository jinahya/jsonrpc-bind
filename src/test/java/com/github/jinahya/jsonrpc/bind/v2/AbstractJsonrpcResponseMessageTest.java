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

abstract class AbstractJsonrpcResponseMessageTest<T extends AbstractJsonrpcResponseMessage>
        extends AbstractJsonrpcMessageTest<T> {

    AbstractJsonrpcResponseMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }

    @Test
    void testIsResultAndErrorExclusive() {
        final boolean result = newInstance().isResultAndErrorExclusive();
    }

    // -------------------------------------------------------------------------------------------------------- $.result
    @Test
    void testHasResult() {
        final boolean result = newInstance().hasResult();
    }

    @Test
    void testIsResultContextuallyValid() {
        final boolean result = newInstance().isResultContextuallyValid();
    }

    @Test
    void testGetResultAsArray() {
        newInstance().getResultAsArray(Object.class);
    }

    @Test
    void testSetResultAsArray() {
        newInstance().setResultAsArray(new ArrayList<>());
        newInstance().setResultAsArray(null);
    }

    @Test
    void testGetResultAsObject() {
        newInstance().getResultAsObject(Object.class);
    }

    @Test
    void testSetResultAsObject() {
        newInstance().setResultAsObject(new Object());
        newInstance().setResultAsObject(null);
    }

    // --------------------------------------------------------------------------------------------------------- $.error
    @Test
    void testHasError() {
        final boolean result = newInstance().hasError();
    }

    @Test
    void testIsErrorContextuallyValid() {
        final boolean result = newInstance().isErrorContextuallyValid();
    }
}

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

abstract class AbstractJsonrpcResponseMessageErrorTest<T extends AbstractJsonrpcResponseMessageError>
        extends AbstractJsonrpcObjectTest<T> {

    AbstractJsonrpcResponseMessageErrorTest(final Class<T> errorClass) {
        super(errorClass);
    }

    // ---------------------------------------------------------------------------------------------------------- $.code
    @Test
    void testGetCode() {
        final int result = newInstance().getCode();
    }

    @Test
    void testSetCode() {
        newInstance().setCode(0);
    }

    @Test
    void testIsCodeReservedForPredefinedErrors() {
        final boolean result = newInstance().isCodeReservedForPredefinedErrors();
    }

    @Test
    void testIsCodeForImplementationDefinedServerErrors() {
        final boolean result = newInstance().isCodeForImplementationDefinedServerErrors();
    }

    // ------------------------------------------------------------------------------------------------------- $.message
    @Test
    void testGetMessage() {
        final String result = newInstance().getMessage();
    }

    @Test
    void testSetMessage() {
        newInstance().setMessage("message");
        newInstance().setMessage(null);
    }

    // -------------------------------------------------------------------------------------------------------- $.data
    @Test
    void testHasData() {
        final boolean result = newInstance().hasData();
    }

    @Test
    void testIsDataContextuallyValid() {
        final boolean result = newInstance().isDataContextuallyValid();
    }

    @Test
    void testGetDataAsArray() {
        newInstance().getDataAsArray(Object.class);
    }

    @Test
    void testSetDataAsArray() {
        newInstance().setDataAsArray(new ArrayList<>());
        newInstance().setDataAsArray(null);
    }

    @Test
    void testGetDataAsObject() {
        newInstance().getDataAsObject(Object.class);
    }

    @Test
    void testSetDataAsObject() {
        newInstance().setDataAsObject(new Object());
        newInstance().setDataAsObject(null);
    }
}

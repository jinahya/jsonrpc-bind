package com.github.jinahya.jsonrpc.bind.v2;

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

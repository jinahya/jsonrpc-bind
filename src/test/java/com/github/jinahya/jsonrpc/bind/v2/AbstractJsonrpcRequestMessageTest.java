package com.github.jinahya.jsonrpc.bind.v2;

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
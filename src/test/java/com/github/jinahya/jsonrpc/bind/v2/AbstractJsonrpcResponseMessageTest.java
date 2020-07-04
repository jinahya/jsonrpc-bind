package com.github.jinahya.jsonrpc.bind.v2;

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
package com.github.jinahya.jsonrpc.bind.v2;

import org.junit.jupiter.api.Test;

abstract class AbstractJsonrpcMessageTest<T extends AbstractJsonrpcMessage>
        extends AbstractJsonrpcObjectTest<T> {

    AbstractJsonrpcMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }

    // ------------------------------------------------------------------------------------------------------- $.jsonrpc
    @Test
    void testGetJsonrpc() {
        final String jsonrpc = newInstance().getJsonrpc();
    }

    @Test
    void testSetJsonrpc() {
        newInstance().setJsonrpc(JsonrpcMessage.PROPERTY_VALUE_JSONRPC);
        newInstance().setJsonrpc(null);
    }

    // ------------------------------------------------------------------------------------------------------------ $.id
    @Test
    void testHasId() {
        newInstance().hasId();
    }

    @Test
    void testIsIdContextuallyValid() {
        newInstance().isIdContextuallyValid();
    }

    @Test
    void testGetIdAsLong() {
        newInstance().getIdAsLong();
    }

    @Test
    void testSetIdAsLong() {
        newInstance().setIdAsLong(0L);
    }

    @Test
    void testGetIdAsInteger() {
        newInstance().getIdAsInteger();
    }

    @Test
    void testSetIdAsInteger() {
        newInstance().setIdAsInteger(0);
    }
}
package com.github.jinahya.jsonrpc.bind.v2;

abstract class AbstractJsonrpcRequestMessageTest<T extends AbstractJsonrpcRequestMessage>
        extends AbstractJsonrpcMessageTest<T> {

    protected AbstractJsonrpcRequestMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }
}
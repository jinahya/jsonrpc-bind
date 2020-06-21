package com.github.jinahya.jsonrpc.bind.v2;

abstract class AbstractJsonrpcResponseMessageTest<T extends AbstractJsonrpcResponseMessage>
        extends AbstractJsonrpcMessageTest<T> {

    protected AbstractJsonrpcResponseMessageTest(final Class<T> messageClass) {
        super(messageClass);
    }
}
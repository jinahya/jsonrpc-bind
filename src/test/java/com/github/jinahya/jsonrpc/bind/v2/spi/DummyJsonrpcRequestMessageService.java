package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage;

public class DummyJsonrpcRequestMessageService
        implements JsonrpcRequestMessageService {

    @Override
    public JsonrpcRequestMessage fromJson(Object source) {
        return null;
    }

    @Override
    public void toJson(JsonrpcRequestMessage message, Object target) {
        // does nothing.
    }
}

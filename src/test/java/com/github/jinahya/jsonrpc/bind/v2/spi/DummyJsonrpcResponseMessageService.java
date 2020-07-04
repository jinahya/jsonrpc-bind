package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessage;

public class DummyJsonrpcResponseMessageService
        implements JsonrpcResponseMessageService {

    @Override
    public JsonrpcResponseMessage fromJson(Object source) {
        return null;
    }

    @Override
    public void toJson(JsonrpcResponseMessage message, Object target) {
        // does nothing.
    }
}

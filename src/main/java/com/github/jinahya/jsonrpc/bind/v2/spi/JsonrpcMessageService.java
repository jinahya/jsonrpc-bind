package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessage;

interface JsonrpcMessageService<T extends JsonrpcMessage>
        extends AutoCloseable {

    T fromJson(Object source);

//    <T extends JsonrpcMessage> T fromJson(Object source, Type type);

    void toJson(T object, Object target);

    //void toJson(Object object, Type type, Object target);

//    default String toJson(JsonrpcMessage message) {
//    }
}

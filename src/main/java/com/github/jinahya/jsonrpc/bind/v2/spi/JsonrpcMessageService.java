package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessage;

interface JsonrpcMessageService<T extends JsonrpcMessage> {

    /**
     * Reads an instance from specified source.
     *
     * @param source the source from which the instance is read.
     * @return a new instance.
     */
    T fromJson(Object source);

    /**
     * Writes specified message to specified target.
     *
     * @param message the message to write.
     * @param target  the target which the {@code message} is written.
     */
    void toJson(T message, Object target);
}

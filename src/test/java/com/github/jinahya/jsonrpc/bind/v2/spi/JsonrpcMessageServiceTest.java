package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessage;

import java.util.ServiceLoader;

import static java.util.Objects.requireNonNull;

abstract class JsonrpcMessageServiceTest<T extends JsonrpcMessageService<U>, U extends JsonrpcMessage> {

    JsonrpcMessageServiceTest(final Class<T> serviceClass, final Class<U> messageClass) {
        super();
        this.serviceClass = requireNonNull(serviceClass, "serviceClass is null");
        this.messageClass = requireNonNull(messageClass, "messageClass is null");
    }

    T load() {
        return ServiceLoader.load(serviceClass).iterator().next();
    }

    final Class<T> serviceClass;

    final Class<U> messageClass;
}
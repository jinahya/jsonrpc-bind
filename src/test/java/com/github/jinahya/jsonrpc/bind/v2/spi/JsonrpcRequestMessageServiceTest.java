package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class JsonrpcRequestMessageServiceTest<T extends JsonrpcRequestMessageService>
        extends JsonrpcMessageServiceTest<T, JsonrpcRequestMessage> {

    JsonrpcRequestMessageServiceTest(final Class<T> serviceClass) {
        super(serviceClass, JsonrpcRequestMessage.class);
    }

    @Test
    void testLoad() {
        final JsonrpcRequestMessageService service = load();
        assertNotNull(service);
    }
}
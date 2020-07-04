package com.github.jinahya.jsonrpc.bind.v2.spi;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class JsonrpcResponseMessageServiceTest<T extends JsonrpcResponseMessageService>
        extends JsonrpcMessageServiceTest<T, JsonrpcResponseMessage> {

    JsonrpcResponseMessageServiceTest(final Class<T> serviceClass) {
        super(serviceClass, JsonrpcResponseMessage.class);
    }

    @Test
    void testLoad() {
        final JsonrpcResponseMessageService service = load();
        assertNotNull(service);
    }
}
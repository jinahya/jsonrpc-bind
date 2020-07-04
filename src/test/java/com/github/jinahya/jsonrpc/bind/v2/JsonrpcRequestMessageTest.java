package com.github.jinahya.jsonrpc.bind.v2;

import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcRequestMessageService;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.assertNull;

class JsonrpcRequestMessageTest {

    @Test
    void testFromJson() {
        final ServiceLoader<JsonrpcRequestMessageService> l = ServiceLoader.load(JsonrpcRequestMessageService.class);
        l.iterator().next();
        final JsonrpcRequestMessage message = JsonrpcRequestMessage.fromJson(new Object());
        assertNull(message); // dummy
    }
}
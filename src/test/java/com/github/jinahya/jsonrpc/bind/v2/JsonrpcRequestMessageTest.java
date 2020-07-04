package com.github.jinahya.jsonrpc.bind.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class JsonrpcRequestMessageTest {

    @Test
    void testFromJson() {
        final JsonrpcRequestMessage message = JsonrpcRequestMessage.fromJson(new Object());
        assertNull(message); // dummy
    }
}
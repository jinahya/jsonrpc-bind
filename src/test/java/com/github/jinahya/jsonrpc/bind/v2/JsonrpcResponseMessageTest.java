package com.github.jinahya.jsonrpc.bind.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class JsonrpcResponseMessageTest {

    @Test
    void testReadFrom() {
        final JsonrpcResponseMessage message = JsonrpcResponseMessage.fromJson(new Object());
        assertNull(message); // dummy
    }
}
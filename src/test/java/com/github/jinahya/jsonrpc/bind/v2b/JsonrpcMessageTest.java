package com.github.jinahya.jsonrpc.bind.v2b;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class JsonrpcMessageTest {

    @Test
    void test() {
        assertEquals("2.0", JsonrpcMessage.PROPERTY_VALUE_JSONRPC);
    }
}
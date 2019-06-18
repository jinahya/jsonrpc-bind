package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

import com.github.jinahya.jsonrpc.bind.v1.RequestTest;
import org.junit.jupiter.api.Test;

class EchoRequestTest extends RequestTest<EchoRequest, String, Integer> {

    EchoRequestTest() {
        super(EchoRequest.class, String.class, Integer.class);
    }

    @Test
    void echo_01_request() {
    }
}

package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NonExistentMethodRequestTest {

    @Test
    void request01() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/non_existent_method_01_request.json",
                                 NonExistentMethodRequest.class);
    }

    @Test
    void request02() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/non_existent_method_01_request.json",
                                 NonExistentMethodRequest.class);
    }
}


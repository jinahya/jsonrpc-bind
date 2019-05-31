package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NamedParametersRequestTest {

    @Test
    void request01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/named_parameters_01_request.json", NamedParametersRequest.class
        );
    }

    @Test
    void request02() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/named_parameters_02_request.json",
                NamedParametersRequest.class
        );
    }
}

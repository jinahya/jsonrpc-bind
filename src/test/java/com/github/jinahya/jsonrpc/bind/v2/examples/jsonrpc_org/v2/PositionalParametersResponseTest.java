package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class PositionalParametersResponseTest {

    @Test
    void response01() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/positional_parameters_01_response.json",
                                 PositionalParametersResponse.class);
    }

    @Test
    void response02() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/positional_parameters_02_response.json",
                                 PositionalParametersResponse.class);
    }
}

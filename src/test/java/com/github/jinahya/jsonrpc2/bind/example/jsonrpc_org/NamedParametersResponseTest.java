package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NamedParametersResponseTest {

    @Test
    void response01() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/named_parameters_01_response.json",
                                 NamedParametersResponse.class);
    }

    @Test
    void response02() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/named_parameters_02_response.json",
                                 NamedParametersResponse.class);
    }
}

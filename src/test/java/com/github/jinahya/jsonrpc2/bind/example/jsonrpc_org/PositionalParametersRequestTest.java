package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class PositionalParametersRequestTest {

    @Test
    void readValue01() throws IOException {
        final PositionalParametersRequest value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/positional_parameters_01_request.json", PositionalParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
    }

    @Test
    void readValue02() throws IOException {
        final PositionalParametersRequest value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/positional_parameters_02_request.json", PositionalParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
    }
}

package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NamedParametersRequestTest {

    @Test
    void readValue01() throws IOException {
        final NamedParametersRequest value = JacksonUtils.readValue(
                "/examples/jsonrpc_org/named_parameters_01_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
    }

    @Test
    void readValue02() throws IOException {
        final NamedParametersRequest value = JacksonUtils.readValue(
                "/examples/jsonrpc_org/named_parameters_02_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
    }
}

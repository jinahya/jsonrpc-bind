package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NonExistentMethodRequestTest {

    @Test
    void readValue01() throws IOException {
        final NonExistentMethodRequest value = JacksonUtils.readValue(
                "/examples/jsonrpc_org/non_existent_method_01_request.json", NonExistentMethodRequest.class);
        log.debug("value: {}", value);
    }
}

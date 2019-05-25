package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JsonbUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc2.bind.JacksonUtils.OBJECT_MAPPER;
import static com.github.jinahya.jsonrpc2.bind.JacksonUtils.readValue;
import static com.github.jinahya.jsonrpc2.bind.JsonbUtils.fromJson;

@Slf4j
class NonExistentMethodRequestTest {

    @Test
    void jackson01() throws IOException {
        final NonExistentMethodRequest value = readValue(
                "/examples/jsonrpc_org/non_existent_method_01_request.json", NonExistentMethodRequest.class);
        log.debug("value: {}", value);
        log.debug("json: {}", OBJECT_MAPPER.writeValueAsString(value));
    }

    @Test
    void jsonb01() throws IOException {
        final NonExistentMethodRequest value = fromJson(
                "/examples/jsonrpc_org/non_existent_method_01_request.json", NonExistentMethodRequest.class);
        log.debug("value: {}", value);
        log.debug("json: {}", JsonbUtils.JSONB.toJson(value));
    }
}


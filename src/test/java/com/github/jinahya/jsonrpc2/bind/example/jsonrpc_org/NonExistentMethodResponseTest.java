package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.GsonUtils;
import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import com.github.jinahya.jsonrpc2.bind.JsonbUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NonExistentMethodResponseTest {

    @Test
    void jackson01() throws IOException {
        final NonExistentMethodResponse value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/non_existent_method_01_response.json", NonExistentMethodResponse.class);
        log.debug("value: {}", value);
        log.debug("json: {}", JacksonUtils.OBJECT_MAPPER.writeValueAsString(value));
    }

    @Test
    void jsonb01() throws IOException {
        final NonExistentMethodResponse value = JsonbUtils.fromResource(
                "/examples/jsonrpc_org/non_existent_method_01_response.json", NonExistentMethodResponse.class);
        log.debug("value: {}", value);
        log.debug("json: {}", JsonbUtils.JSONB.toJson(value));
    }

    @Test
    void gson01() throws IOException {
        final NonExistentMethodResponse value = GsonUtils.fromResource(
                "/examples/jsonrpc_org/non_existent_method_01_response.json", NonExistentMethodResponse.class);
        log.debug("value: {}", value);
        log.debug("json: {}", GsonUtils.GSON.toJson(value));
    }
}

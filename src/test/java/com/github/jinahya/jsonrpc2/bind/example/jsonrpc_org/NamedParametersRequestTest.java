package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.GsonUtils;
import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import com.github.jinahya.jsonrpc2.bind.JsonbUtils;
import com.github.jinahya.jsonrpc2.bind.MoshiUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NamedParametersRequestTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void jackson01() throws IOException {
        final NamedParametersRequest value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/named_parameters_01_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("json: {}", JacksonUtils.OBJECT_MAPPER.writeValueAsString(value));
    }

    @Test
    void jackson02() throws IOException {
        final NamedParametersRequest value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/named_parameters_02_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("json: {}", JacksonUtils.OBJECT_MAPPER.writeValueAsString(value));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void jsonb01() throws IOException {
        final NamedParametersRequest value = JsonbUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_01_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("json: {}", JsonbUtils.JSONB.toJson(value));
    }

    @Test
    void jsonb02() throws IOException {
        final NamedParametersRequest value = JsonbUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_02_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("json: {}", JsonbUtils.JSONB.toJson(value));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void gson01() throws IOException {
        final NamedParametersRequest value = GsonUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_01_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("gson: {}", GsonUtils.GSON.toJson(value));
    }

    @Test
    void gson02() throws IOException {
        final NamedParametersRequest value = GsonUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_02_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("gson: {}", GsonUtils.GSON.toJson(value));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void moshi01() throws IOException {
        final NamedParametersRequest value = MoshiUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_01_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("moshi: {}", MoshiUtils.MOSHI.adapter(NamedParametersRequest.class).toJson(value));
    }

    @Test
    void moshi02() throws IOException {
        final NamedParametersRequest value = MoshiUtils.fromResource(
                "/examples/jsonrpc_org/named_parameters_02_request.json", NamedParametersRequest.class);
        log.debug("value: {}", value);
        log.debug("value.id.class: {}", value.getId().getClass());
        log.debug("moshi: {}", MoshiUtils.MOSHI.adapter(NamedParametersRequest.class).toJson(value));
    }
}

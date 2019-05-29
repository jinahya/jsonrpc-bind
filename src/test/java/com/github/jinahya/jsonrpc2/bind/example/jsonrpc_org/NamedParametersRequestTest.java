package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.GsonUtils;
import com.github.jinahya.jsonrpc2.bind.JsonTests;
import com.github.jinahya.jsonrpc2.bind.MoshiUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NamedParametersRequestTest {

    @Test
    void request01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/named_parameters_01_request.json",
                NamedParametersRequest.class
        );
    }

    @Test
    void request02() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/named_parameters_02_request.json",
                NamedParametersRequest.class
        );
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

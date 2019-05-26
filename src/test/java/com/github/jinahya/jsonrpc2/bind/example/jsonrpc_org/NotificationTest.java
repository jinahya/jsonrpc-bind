package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import com.github.jinahya.jsonrpc2.bind.JsonbUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class NotificationTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void jsonb01() throws IOException {
        final Notification value = JsonbUtils.fromResource(
                "/examples/jsonrpc_org/notification_01_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {} {}", value.getParams(), value.getParams().getClass());
        assertTrue(value.isNotification());
        log.debug("jsonb: {}", JsonbUtils.JSONB.toJson(value));
    }

    @Test
    void jsonb02() throws IOException {
        final Notification value = JsonbUtils.fromResource(
                "/examples/jsonrpc_org/notification_02_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
        assertTrue(value.isNotification());
        log.debug("jsonb: {}", JsonbUtils.JSONB.toJson(value));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void jackson01() throws IOException {
        final Notification value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/notification_01_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {} {}", value.getParams(), value.getParams().getClass());
        assertTrue(value.isNotification());
        log.debug("jackson: {}", JacksonUtils.OBJECT_MAPPER.writeValueAsString(value));
    }

    @Test
    void jackson02() throws IOException {
        final Notification value = JacksonUtils.readResource(
                "/examples/jsonrpc_org/notification_02_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
        assertTrue(value.isNotification());
        log.debug("jackson: {}", JacksonUtils.OBJECT_MAPPER.writeValueAsString(value));
    }
}

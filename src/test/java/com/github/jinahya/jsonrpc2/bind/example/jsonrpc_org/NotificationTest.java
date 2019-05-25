package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class NotificationTest {

    @Test
    void readValue01() throws IOException {
        final Notification value = JacksonUtils.readValue(
                "/examples/jsonrpc_org/notification_01_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
        assertTrue(value.isNotification());
    }

    @Test
    void readValue02() throws IOException {
        final Notification value = JacksonUtils.readValue(
                "/examples/jsonrpc_org/notification_02_request.json", Notification.class);
        log.debug("value: {}", value);
        log.debug("value.params: {}", value.getParams());
        assertTrue(value.isNotification());
    }
}

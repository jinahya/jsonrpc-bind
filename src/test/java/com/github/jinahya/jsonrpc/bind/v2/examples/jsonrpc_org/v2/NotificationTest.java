package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class NotificationTest {

    @Test
    void request01() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/notification_01_request.json", Notification.class, v -> {
            assertTrue(v.isNotification());
        });
    }

    @Test
    void request02() throws IOException {
        JsonTests.doWithResource("/examples/jsonrpc_org/v2/notification_02_request.json", Notification.class, v -> {
            assertTrue(v.isNotification());
        });
    }
}

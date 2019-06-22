package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy ofError the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.jsonrpc.bind.v1.NotificationTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * A class for testing {@link HandleMessageNotification}.
 */
class HandleMessageNotificationTest extends NotificationTest<HandleMessageNotification, String> {

    /**
     * Creates a new instance.
     */
    HandleMessageNotificationTest() {
        super(HandleMessageNotification.class, String.class);
    }

    /**
     * Test with {@code handleMessage_01_notification.json}.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void handleMessage_01_notification() throws IOException {
        acceptValueFromResource(
                "handleMessage_01_notification.json",
                v -> {
                    assertEquals("handleMessage", v.getMethod());
                    assertIterableEquals(asList("user1", "we were just talking"), v.getParams());
                }
        );
    }

    @Test
    void handleMessage_02_notification() throws IOException {
        acceptValueFromResource(
                "handleMessage_02_notification.json",
                v -> {
                    assertEquals("handleMessage", v.getMethod());
                    assertIterableEquals(asList("user3", "sorry, gotta go now, ttyl"), v.getParams());
                }
        );
    }
}

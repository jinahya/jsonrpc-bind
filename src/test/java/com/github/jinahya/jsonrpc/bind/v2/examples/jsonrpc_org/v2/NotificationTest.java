package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import com.github.jinahya.jsonrpc.bind.v2.RequestObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class NotificationTest extends RequestObjectTest<Notification, List<Integer>> {

    @SuppressWarnings({"unchecked"})
    NotificationTest() {
        super(Notification.class, (Class<List<Integer>>) (Class<?>) List.class);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super Notification> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            assertTrue(v.isNotification());
            consumer.accept(v);
        });
    }

    @Test
    void notification_01_request() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/notification_01_request.json", v -> {
            final List<Integer> params = v.getParams();
            assertIterableEquals(asList(1, 2, 3, 4, 5), params);
        });
    }

    @Test
    void notification_02_request() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/notification_02_request.json", v -> {
            assertNull(v.getParams());
        });
    }
}

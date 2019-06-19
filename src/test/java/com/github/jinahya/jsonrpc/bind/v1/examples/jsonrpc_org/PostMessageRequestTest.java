package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

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

import com.github.jinahya.jsonrpc.bind.v1.RequestTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class PostMessageRequestTest extends RequestTest<PostMessageRequest, String, Long> {

    PostMessageRequestTest() {
        super(PostMessageRequest.class, String.class, Long.class);
    }

    @Test
    void postMessage_01_request() throws IOException {
        withResource(
                "postMessage_01_request.json",
                v -> {
                    assertEquals("postMessage", v.getMethod());
                    assertIterableEquals(singletonList("Hello all!"), v.getParams());
                    assertEquals(99L, (long) v.getId());
                }
        );
    }

    @Test
    void postMessage_02_request() throws IOException {
        withResource(
                "postMessage_02_request.json",
                v -> {
                    assertEquals("postMessage", v.getMethod());
                    assertIterableEquals(singletonList("I have a question:"), v.getParams());
                    assertEquals(101L, (long) v.getId());
                }
        );
    }
}

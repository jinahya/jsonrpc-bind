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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
class EchoRequestTest extends RequestTest<EchoRequest, String, Integer> {

    EchoRequestTest() {
        super(EchoRequest.class, String.class, Integer.class);
    }

    /**
     * Tests with {@code echo_01_request.json} resource.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void echo_01_request() throws IOException {
        acceptValueFromResource(
                "echo_01_request.json",
                v -> {
                    assertEquals("echo", v.getMethod());
                    assertIterableEquals(singletonList("Hello JSON-RPC"), v.getParams());
                    assertEquals(1, (int) v.getId());
                }
        );
    }
}

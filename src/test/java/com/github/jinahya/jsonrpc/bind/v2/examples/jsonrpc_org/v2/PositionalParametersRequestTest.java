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

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
class PositionalParametersRequestTest extends RequestObjectTest<PositionalParametersRequest> {

    PositionalParametersRequestTest() {
        super(PositionalParametersRequest.class);
    }

    @Test
    void positional_parameters_01_request() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/positional_parameters_01_request.json", v -> {
            assertIterableEquals(asList(42, 23), v.getParams());
            assertEquals(1L, v.getId());
        });
    }

    @Test
    void positional_parameters_02_request() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/positional_parameters_02_request.json", v -> {
            assertIterableEquals(asList(23, 42), v.getParams());
            assertEquals(2L, v.getId());
        });
    }
}

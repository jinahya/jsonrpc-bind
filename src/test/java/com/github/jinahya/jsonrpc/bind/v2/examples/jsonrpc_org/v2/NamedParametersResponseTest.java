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

import com.github.jinahya.jsonrpc.bind.v2.ResponseObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;
import static com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject.UnknownData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class NamedParametersResponseTest
        extends ResponseObjectTest<NamedParametersResponse, Integer, Integer, ErrorObject<Object>, Object> {

    NamedParametersResponseTest() {
        super(NamedParametersResponse.class, Integer.class, Integer.class, UnknownData.class, Object.class);
    }

    @Test
    void named_parameters_01_response() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/named_parameters_01_response.json", v -> {
            assertEquals(19, v.getResult());
            assertEquals(3, v.getId());
        });
    }

    @Test
    void named_parameters_02_response() throws IOException {
        acceptValueFromResource("/examples/jsonrpc_org/v2/named_parameters_02_response.json", v -> {
            assertEquals(19, v.getResult());
            assertEquals(4, v.getId());
        });
    }
}

package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

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

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PositionalParametersResponseTest
        extends ResponseObjectTest<PositionalParametersResponse, Integer, ErrorObject<Object>, Integer> {

    @SuppressWarnings({"unchecked"})
    PositionalParametersResponseTest() {
        super(PositionalParametersResponse.class, Integer.class,
              (Class<ErrorObject<Object>>) (Class<?>) ErrorObject.class, Integer.class);
    }

    @Test
    void positional_parameters_01_response() throws IOException {
        acceptValueFromResource(
                "positional_parameters_01_response.json",
                v -> {
                    assertEquals(19, (int) v.getResult());
                    assertEquals(1, (int) v.getId());
                }
        );
    }

    @Test
    void positional_parameters_02_response() throws IOException {
        acceptValueFromResource(
                "positional_parameters_02_response.json",
                v -> {
                    assertEquals(-19, (int) v.getResult());
                    assertEquals(2, (int) v.getId());
                }
        );
    }
}

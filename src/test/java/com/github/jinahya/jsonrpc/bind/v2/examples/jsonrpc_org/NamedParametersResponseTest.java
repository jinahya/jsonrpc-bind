package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

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

import com.github.jinahya.jsonrpc.bind.v2.ErrorObjectTest;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class NamedParametersResponseTest
        extends ResponseObjectTest<NamedParametersResponse, Integer, ErrorObjectTest.NoData, Integer> {

    // -----------------------------------------------------------------------------------------------------------------
    NamedParametersResponseTest() {
        super(NamedParametersResponse.class, Integer.class, ErrorObjectTest.NoData.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super NamedParametersResponse> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            final NamedParametersRequest request = new NamedParametersRequest();
            request.copyIdFrom(v);
            assertEquals(v.getId(), request.getId());
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void named_parameters_01_response() throws IOException {
        acceptValueFromResource(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/named_parameters_01_response.json",
                v -> {
                    assertEquals(19, (int) v.getResult());
                    assertEquals(3, (int) v.getId());
                }
        );
    }

    @Test
    void named_parameters_02_response() throws IOException {
        acceptValueFromResource(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/named_parameters_02_response.json",
                v -> {
                    assertEquals(19, (int) v.getResult());
                    assertEquals(4, (int) v.getId());
                }
        );
    }
}

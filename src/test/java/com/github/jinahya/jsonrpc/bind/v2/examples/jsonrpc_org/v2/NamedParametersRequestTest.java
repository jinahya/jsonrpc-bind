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

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jinahya.jsonrpc.bind.v2.JacksonUtils;
import com.github.jinahya.jsonrpc.bind.v2.RequestObject;
import com.github.jinahya.jsonrpc.bind.v2.RequestObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class NamedParametersRequestTest
        extends RequestObjectTest<NamedParametersRequest, Integer, NamedParametersRequest.Params> {

    NamedParametersRequestTest() {
        super(NamedParametersRequest.class, Integer.class, NamedParametersRequest.Params.class);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super NamedParametersRequest> consumer)
            throws IOException {
        super.acceptValueFromResource(
                name,
                v -> {
                    consumer.accept(v);
                }
        );
    }

    @Override
    protected <U> void acceptValueFromResource(final String name, final Supplier<? extends U> supplier,
                                               final BiConsumer<? super NamedParametersRequest, ? super U> consumer)
            throws IOException {
        super.acceptValueFromResource(
                name,
                supplier,
                (v, u) -> {
                    consumer.accept(v, u);
                }
        );
    }

    @Test
    void named_parameters_01_request() throws IOException {
        acceptValueFromResource(
                "/examples/jsonrpc_org/v2/named_parameters_01_request.json",
                v -> {
                    assertEquals("subtract", v.getMethod());
                    assertEquals(23, v.getParams().getSubtrahend());
                    assertEquals(42, v.getParams().getMinuend());
                    assertEquals(Integer.valueOf(3), v.getId());
                }
        );
        JacksonUtils.readTreeFromResource(
                "/examples/jsonrpc_org/v2/named_parameters_01_request.json",
                NamedParametersRequest.class,
                tree -> {
                    final JsonNode idNode = tree.get(RequestObject.PROPERTY_NAME_ID);
                    log.debug("idNode.textual: {}", idNode.isTextual());
                    log.debug("idNode.number: {}", idNode.isNumber());
                    log.debug("idNode.integralNumber: {}", idNode.isIntegralNumber());
                    log.debug("idNode.floatingPointNumber: {}", idNode.isFloatingPointNumber());
                    log.debug("idNode.asText() -> {}", idNode.asText());
                    log.debug("idNode.textValue() -> {}", idNode.textValue());
                }
        );
    }

    @Test
    void named_parameters_02_request() throws IOException {
        acceptValueFromResource(
                "/examples/jsonrpc_org/v2/named_parameters_02_request.json",
                v -> {
                    assertEquals("subtract", v.getMethod());
                    assertEquals(23, v.getParams().getSubtrahend());
                    assertEquals(42, v.getParams().getMinuend());
                    assertEquals(4, (int) v.getId());
                }
        );
    }
}

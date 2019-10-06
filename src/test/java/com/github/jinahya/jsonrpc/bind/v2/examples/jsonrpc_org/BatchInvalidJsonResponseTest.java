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

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc.bind.JacksonTests.applyObjectMapper;
import static com.github.jinahya.jsonrpc.bind.JsonbTests.JSONB;
import static com.github.jinahya.jsonrpc.bind.JsonrpcTests.applyResourceStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class BatchInvalidJsonResponseTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void batch_invalid_json_01_response__jackson() throws IOException {
        final TypeReference<BatchInvalidJsonResponse> typeReference = new TypeReference<BatchInvalidJsonResponse>() {
        };
        final BatchInvalidJsonResponse responseObject = applyResourceStream(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/batch_invalid_json_01_response.json",
                s -> applyObjectMapper(m -> {
                    try {
                        return m.readValue(s, typeReference);
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                })
        );
        assertEquals(ErrorObject.CODE_PARSE_ERROR, responseObject.getError().getCode());
        assertEquals("Parse error", responseObject.getError().getMessage());
    }

    @Test
    void batch_invalid_json_01_response__jsonb() throws IOException {
        final BatchInvalidJsonResponse responseObject = applyResourceStream(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/batch_invalid_json_01_response.json",
                s -> JSONB.fromJson(s, BatchInvalidJsonResponse.class));
        log.debug("responseObject: {}", responseObject);
        assertEquals(ErrorObject.CODE_PARSE_ERROR, responseObject.getError().getCode());
        assertEquals("Parse error", responseObject.getError().getMessage());
    }
}

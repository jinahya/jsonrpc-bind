package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcRequestMessageService;
import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcResponseMessageService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessageServiceHelper.requestMessageService;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessageServiceHelper.responseMessageService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JsonrpcMessageServiceHelperTest {

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                arguments(false, false),
                arguments(false, true),
                arguments(true, false),
                arguments(true, true)
        );
    }

    @MethodSource({"argumentsStream"})
    @ParameterizedTest
    void testLoadJsonrpcRequestMessageService(final boolean reuse, final boolean reload) {
        final JsonrpcRequestMessageService service = requestMessageService(reuse, reload);
        assertNotNull(service);
    }

    @MethodSource({"argumentsStream"})
    @ParameterizedTest
    void testLoadJsonrpcResponseMessageService(final boolean reuse, final boolean reload) {
        final JsonrpcResponseMessageService service = responseMessageService(reuse, reload);
        assertNotNull(service);
    }
}

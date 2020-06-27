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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class JsonrpcMessageConstantsTest {

    /**
     * Asserts the value of {@link JsonrpcMessage#PROPERTY_VALUE_JSONRPC} equals to {@code 2.0}.
     */
    @Test
    void assertPropertyValueJsonrpcEquals2Dot0() {
        final String expected = "2.0";
        final String actual = JsonrpcMessage.PROPERTY_VALUE_JSONRPC;
        assertEquals(expected, actual);
    }

    /**
     * Asserts the value of {@link JsonrpcMessage#PROPERTY_VALUE_JSONRPC} matches to the {@link
     * JsonrpcMessage#PROPERTY_PATTERN_REGEXP_JSONRPC}.
     */
    @Test
    void assertPropertyValueJsonrpcMatchesToThePattern() {
        final Pattern pattern = Pattern.compile(JsonrpcMessage.PROPERTY_PATTERN_REGEXP_JSONRPC);
        assertTrue(pattern.matcher(JsonrpcMessage.PROPERTY_VALUE_JSONRPC).matches());
    }
}

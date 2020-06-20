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

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

public final class JsonrpcObjects implements JsonrpcObject {

    /**
     * An unmodifiable set of reserved properties names for JSON-RPC 2.0.
     */
    public static final Set<String> RESERVED_PROPERTY_NAMES = unmodifiableSet(new HashSet<>(asList(
            JsonrpcMessage.PROPERTY_NAME_JSONRPC,
            JsonrpcMessage.PROPERTY_NAME_ID,
            JsonrpcRequestMessage.PROPERTY_NAME_METHOD,
            JsonrpcRequestMessage.PROPERTY_NAME_PARAMS,
            JsonrpcResponseMessage.PROPERTY_NAME_RESULT,
            JsonrpcResponseMessage.PROPERTY_NAME_ERROR)));

    private JsonrpcObjects() {
        throw new AssertionError("instantiation is not allowed");
    }
}

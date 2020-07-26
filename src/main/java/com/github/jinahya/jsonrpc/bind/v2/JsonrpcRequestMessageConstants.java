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

/**
 * A class defines constant values for {@link JsonrpcRequestMessage} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#request_object">Request Object (JSON-RPC 2.0)</a>
 */
public final class JsonrpcRequestMessageConstants {

    /**
     * The property name for {@code $.method} part. The value is {@value}.
     * <blockquote>
     * A String containing the name of the method to be invoked. Method names that begin with the word rpc followed by a
     * period character (U+002E or ASCII 46) are reserved for rpc-internal methods and extensions and MUST NOT be used
     * for anything else.
     * </blockquote>
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The name of the property for {@code $.params} part. The value is {@value}.
     * <blockquote>
     * A Structured value that holds the parameter values to be used during the invocation of the method. This member
     * MAY be omitted.
     * </blockquote>
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    private JsonrpcRequestMessageConstants() {
        super();
    }
}


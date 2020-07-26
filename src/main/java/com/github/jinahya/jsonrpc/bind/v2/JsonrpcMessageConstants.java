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
 * A class defines constant values for {@link JsonrpcMessage} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JsonrpcMessageConstants {

    /**
     * The name of the property maps to {@code $.jsonrpc} part. The value is {@value}.
     * <blockquote>
     * A String specifying the version of the JSON-RPC protocol. MUST be exactly "2.0".
     * </blockquote>
     *
     * @see #PROPERTY_PATTERN_REGEXP_JSONRPC
     * @see #PROPERTY_VALUE_JSONRPC
     */
    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    /**
     * The regexp for {@link #PROPERTY_NAME_JSONRPC} property. The value is {@value}.
     */
    public static final String PROPERTY_PATTERN_REGEXP_JSONRPC = "2\\.0";

    /**
     * The default (and fixed) value for {@link #PROPERTY_NAME_JSONRPC} property. The value is {@code 2.0}.
     */
    public static final String PROPERTY_VALUE_JSONRPC = PROPERTY_PATTERN_REGEXP_JSONRPC.replaceAll("\\\\.", ".");

    /**
     * The name of the property maps to {@code $.id} part. The value is {@value}.
     * <blockquote>
     * An identifier established by the Client that MUST contain a String, Number, or NULL value if included. If it is
     * not included it is assumed to be a notification. The value SHOULD normally not be Null and Numbers SHOULD NOT
     * contain fractional parts
     * </blockquote>
     */
    public static final String PROPERTY_NAME_ID = "id";

    private JsonrpcMessageConstants() {
        super();
    }
}

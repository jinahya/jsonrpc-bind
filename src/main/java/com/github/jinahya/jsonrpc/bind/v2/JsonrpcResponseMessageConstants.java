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
 * A class defines constant values for {@link JsonrpcResponseMessage} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#response_object">Response Object (JSON-RPC 2.0)</a>
 */
public final class JsonrpcResponseMessageConstants {

    /**
     * The property name for {@code $.result} part. The value is {@value}.
     * <blockquote>
     * This member is REQUIRED on success.
     * <br>This member MUST NOT exist if there was an error invoking the method.
     * <br>The value of this member is determined by the method invoked on the Server.
     * </blockquote>
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * The name of the property maps to {@code $.error} part. The value is {@value}.
     * <blockquote>
     * This member is REQUIRED on error.
     * <br>This member MUST NOT exist if there was no error triggered during invocation.
     * <br>The value for this member MUST be an Object as defined in section 5.1.
     * </blockquote>
     *
     * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Object (JSON-RPC 2.0)</a>
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    private JsonrpcResponseMessageConstants() {
        super();
    }
}

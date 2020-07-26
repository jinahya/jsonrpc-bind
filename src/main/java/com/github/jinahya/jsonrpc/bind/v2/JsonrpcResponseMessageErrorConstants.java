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
 * A class defines constant values for {@link JsonrpcResponseMessageError} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class JsonrpcResponseMessageErrorConstants {

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * The name of the property for error code. The value is {@value}.
     * <blockquote>
     * A Number that indicates the error type that occurred.
     * <br>This MUST be an integer.
     * </blockquote>
     */
    public static final String PROPERTY_NAME_CODE = "code";

    /**
     * The minimum value of {@link #PROPERTY_NAME_CODE} property for pre-defined errors.
     */
    public static final int CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN = -32768;

    /**
     * The maximum value of {@link #PROPERTY_NAME_CODE} property for pre-defined errors.
     */
    public static final int CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX = -32000;

    /**
     * A predefined {@link #PROPERTY_NAME_CODE} property value for representing parse errors.
     * <blockquote>
     * Invalid JSON was received by the server.
     * <br>
     * An error occurred on the server while parsing the JSON text.
     * </blockquote>
     */
    public static final int CODE_PARSE_ERROR = -32700;

    /**
     * A predefined {@link #PROPERTY_NAME_CODE} property value for representing invalid requests.
     * <blockquote>
     * The JSON sent is not a valid Request object.
     * </blockquote>
     */
    public static final int CODE_INVALID_REQUEST = -32600;

    /**
     * A predefined {@link #PROPERTY_NAME_CODE} property value for an unknown {@value
     * JsonrpcRequestMessageConstants#PROPERTY_NAME_METHOD}.
     * <blockquote>
     * The method does not exist / is not available.
     * </blockquote>
     */
    public static final int CODE_METHOD_NOT_FOUND = -32601;

    /**
     * A predefined {@link #PROPERTY_NAME_CODE} property value for an invalid {@value
     * JsonrpcRequestMessageConstants#PROPERTY_NAME_PARAMS}.
     * <blockquote>
     * Invalid method parameter(s).
     * </blockquote>
     */
    public static final int CODE_INVALID_PARAMS = -32602;

    /**
     * A predefined {@link #PROPERTY_NAME_CODE} property value for internal errors.
     * <blockquote>
     * Internal JSON-RPC error.
     * </blockquote>
     */
    public static final int CODE_INTERNAL_ERROR = -32603;

    /**
     * The minimum value of {@link #PROPERTY_NAME_CODE} property value for server errors. The value is {@value}.
     * <blockquote>
     * Reserved for implementation-defined server-errors.
     * </blockquote>
     */
    public static final int CODE_SERVER_ERROR_MIN = -32099;

    /**
     * The maximum value of {@link #PROPERTY_NAME_CODE} property value for server errors. The value is {@value}.
     * <blockquote>
     * Reserved for implementation-defined server-errors.
     * </blockquote>
     */
    public static final int CODE_SERVER_ERROR_MAX = -32000;

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * The name of the property for error message. The value is {@value}.
     * <blockquote>
     * A String providing a short description of the error.
     * <br>The message SHOULD be limited to a concise single sentence.
     * </blockquote>
     */
    public static final String PROPERTY_NAME_MESSAGE = "message";

    // ------------------------------------------------------------------------------------------------------------ data

    /**
     * The name of the error data. The value is {@value}.
     * <blockquote>
     * A Primitive or Structured value that contains additional information about the error.
     * <br>This may be omitted.
     * <br>The value of this member is defined by the Server (e.g. detailed error information, nested errors etc.).
     * </blockquote>
     */
    public static final String PROPERTY_NAME_DATA = "data";

    // -----------------------------------------------------------------------------------------------------------------
    private JsonrpcResponseMessageErrorConstants() {
        super();
    }
}

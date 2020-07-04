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

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.beans.Transient;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * An interface for {@link JsonrpcResponseMessage#PROPERTY_NAME_ERROR} property of JSON-RPC 2.0 response messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface JsonrpcResponseMessageError extends JsonrpcObject {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the property for error code. The value is {@value}.
     * <blockquote>
     * A Number that indicates the error type that occurred.
     * <br>This MUST be an integer.
     * </blockquote>
     */
    String PROPERTY_NAME_CODE = "code";

    /**
     * The minimum value of {@value #PROPERTY_NAME_CODE} property for pre-defined errors.
     */
    int CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN = -32768;

    /**
     * The maximum value of {@value #PROPERTY_NAME_CODE} property for pre-defined errors.
     */
    int CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX = -32000;

    /**
     * A predefined {@value #PROPERTY_NAME_CODE} property value for representing parse errors.
     * <blockquote>
     * Invalid JSON was received by the server.
     * <br>
     * An error occurred on the server while parsing the JSON text.
     * </blockquote>
     */
    int CODE_PARSE_ERROR = -32700;

    /**
     * A predefined {@value #PROPERTY_NAME_CODE} property value for representing invalid requests.
     * <blockquote>
     * The JSON sent is not a valid Request object.
     * </blockquote>
     */
    int CODE_INVALID_REQUEST = -32600;

    /**
     * A predefined {@value #PROPERTY_NAME_CODE} property value for an unknown {@value
     * com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage#PROPERTY_NAME_METHOD}.
     * <blockquote>
     * The method does not exist / is not available.
     * </blockquote>
     */
    int CODE_METHOD_NOT_FOUND = -32601;

    /**
     * A predefined {@value #PROPERTY_NAME_CODE} property value for an invalid {@value
     * com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage#PROPERTY_NAME_PARAMS}.
     * <blockquote>
     * Invalid method parameter(s).
     * </blockquote>
     */
    int CODE_INVALID_PARAMS = -32602;

    /**
     * A predefined {@value #PROPERTY_NAME_CODE} property value for internal errors.
     * <blockquote>
     * Internal JSON-RPC error.
     * </blockquote>
     */
    int CODE_INTERNAL_ERROR = -32603;

    /**
     * The minimum value of {@value #PROPERTY_NAME_CODE} property value for server errors. The value is {@value}.
     * <blockquote>
     * Reserved for implementation-defined server-errors.
     * </blockquote>
     */
    int CODE_SERVER_ERROR_MIN = -32099;

    /**
     * The maximum value of {@value #PROPERTY_NAME_CODE} property value for server errors. The value is {@value}.
     * <blockquote>
     * Reserved for implementation-defined server-errors.
     * </blockquote>
     */
    int CODE_SERVER_ERROR_MAX = -32000;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the property for error message. The value is {@value}.
     * <blockquote>
     * A String providing a short description of the error.
     * <br>The message SHOULD be limited to a concise single sentence.
     * </blockquote>
     */
    String PROPERTY_NAME_MESSAGE = "message";

    /**
     * The name of the error data. The value is {@value}.
     * <blockquote>
     * A Primitive or Structured value that contains additional information about the error.
     * <br>This may be omitted.
     * <br>The value of this member is defined by the Server (e.g. detailed error information, nested errors etc.).
     * </blockquote>
     */
    String PROPERTY_NAME_DATA = "data";

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns current value of {@value #PROPERTY_NAME_CODE} property.
     *
     * @return current value of {@value #PROPERTY_NAME_CODE} property
     */
    int getCode();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_CODE} property with specified value.
     *
     * @param code new value for {@value #PROPERTY_NAME_CODE} property.
     */
    void setCode(int code);

    /**
     * Indicates that current value of {@value #PROPERTY_NAME_CODE} property is between {@link
     * #CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN} and {@link #CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX}, both inclusive.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_CODE} property is in a range of reserved for
     * predefined values; {@code false} otherwise.
     */
    @Transient
    default boolean isCodeReservedForPredefinedError() {
        final int code = getCode();
        return code >= CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN && code <= CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX;
    }

    /**
     * Indicates that current value of {@value #PROPERTY_NAME_CODE} property is between {@link #CODE_SERVER_ERROR_MIN}
     * and {@link #CODE_SERVER_ERROR_MAX}, both inclusive.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_CODE} property means a server error; {@code
     * false} otherwise.
     */
    @Transient
    default boolean isCodeForImplementationDefinedServerError() {
        final int code = getCode();
        return code >= CODE_SERVER_ERROR_MIN && code <= CODE_SERVER_ERROR_MAX;
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Returns current value of {@value #PROPERTY_NAME_MESSAGE} property.
     *
     * @return current value of {@value #PROPERTY_NAME_MESSAGE} property
     */
    @NotBlank
    String getMessage();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_MESSAGE} property with specified value.
     *
     * @param message new value for {@value #PROPERTY_NAME_MESSAGE} property.
     */
    void setMessage(String message);

    // ------------------------------------------------------------------------------------------------------------ data

    /**
     * Indicates this error object has a value for {@value #PROPERTY_NAME_DATA} property.
     *
     * @return {@code true} if this error object has a value for {@value #PROPERTY_NAME_DATA} property; {@code false}
     * otherwise.
     */
    @Transient
    boolean hasData();

    /**
     * Indicates the value of {@value #PROPERTY_NAME_DATA} property is contextually valid.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_DATA} property is contextually valid; {@code
     * false} otherwise.
     * @apiNote It's considered to be valid when {@link #hasData()} returns {@code false}.
     * @implSpec The default implementation returns {@code true}.
     */
    @Transient
    @AssertTrue
    default boolean isDataContextuallyValid() {
        return true;
    }

    /**
     * Reads current value of {@value #PROPERTY_NAME_DATA} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    @Transient
    <T> List<T> getDataAsArray(Class<T> elementClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_DATA} property with specified list.
     *
     * @param data new value for {@value #PROPERTY_NAME_DATA} property.
     */
    void setDataAsArray(List<?> data);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_DATA} property with specified iterable.
     *
     * @param data new value for {@value #PROPERTY_NAME_DATA} property.
     */
    default void setDataAsArray(final Iterable<?> data) {
        setDataAsArray(
                ofNullable(data)
                        .map(d -> StreamSupport.stream(d.spliterator(), false).collect(toList()))
                        .orElse(null)
        );
    }

    /**
     * Reads current value of {@value #PROPERTY_NAME_DATA} property as an instance of specified class.
     *
     * @param objectClass the class of the value.
     * @param <T>         object type parameter
     * @return an instance of {@code objectClass}.
     */
    @Transient
    <T> T getDataAsObject(Class<T> objectClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_DATA} property with specified object.
     *
     * @param data new value for {@value #PROPERTY_NAME_DATA} property.
     */
    void setDataAsObject(Object data);
}

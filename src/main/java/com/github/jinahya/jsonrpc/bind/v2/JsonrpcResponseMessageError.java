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

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.beans.Transient;
import java.util.List;

import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessageErrorConstants.CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessageErrorConstants.CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessageErrorConstants.CODE_SERVER_ERROR_MAX;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessageErrorConstants.CODE_SERVER_ERROR_MIN;
import static java.util.ServiceLoader.load;

/**
 * An interface for {@link JsonrpcResponseMessageConstants#PROPERTY_NAME_ERROR} property of JSON-RPC 2.0 response
 * messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface JsonrpcResponseMessageError extends JsonrpcObject {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    static JsonrpcResponseMessageError newInstance() {
        return load(JsonrpcResponseMessageError.class).iterator().next();
    }

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property.
     *
     * @return current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property
     */
    int getCode();

    /**
     * Replaces current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property with specified
     * value.
     *
     * @param code new value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property.
     */
    void setCode(int code);

    /**
     * Indicates that current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property is
     * between {@link JsonrpcResponseMessageErrorConstants#CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN} and {@link
     * JsonrpcResponseMessageErrorConstants#CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX}, both inclusive.
     *
     * @return {@code true} if current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property
     * is in a range of reserved for predefined values; {@code false} otherwise.
     */
    @Transient
    default boolean isCodeReservedForPredefinedErrors() {
        final int code = getCode();
        return code >= CODE_RESERVED_FOR_PREDEFINED_ERRORS_MIN && code <= CODE_RESERVED_FOR_PREDEFINED_ERRORS_MAX;
    }

    /**
     * Indicates that current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property is
     * between {@link JsonrpcResponseMessageErrorConstants#CODE_SERVER_ERROR_MIN} and {@link
     * JsonrpcResponseMessageErrorConstants#CODE_SERVER_ERROR_MAX}, both inclusive.
     *
     * @return {@code true} if current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_CODE} property
     * means a server error; {@code false} otherwise.
     */
    @Transient
    default boolean isCodeForImplementationDefinedServerErrors() {
        final int code = getCode();
        return code >= CODE_SERVER_ERROR_MIN && code <= CODE_SERVER_ERROR_MAX;
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Returns current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_MESSAGE} property.
     *
     * @return current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_MESSAGE} property
     */
    @NotNull
    String getMessage();

    /**
     * Replaces current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_MESSAGE} property with
     * specified value.
     *
     * @param message new value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_MESSAGE} property.
     */
    void setMessage(String message);

    // ------------------------------------------------------------------------------------------------------------ data

    /**
     * Indicates this error object has a value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA}
     * property.
     *
     * @return {@code true} if this error object has a value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA}
     * property; {@code false} otherwise.
     */
    @Transient
    boolean hasData();

    /**
     * Indicates the value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property is contextually
     * valid.
     *
     * @return {@code true} if current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property
     * is contextually valid; {@code false} otherwise.
     * @apiNote It's considered to be valid when {@link #hasData()} returns {@code false}.
     * @implSpec The default implementation returns {@code true}.
     */
    @Transient
    @AssertTrue
    default boolean isDataContextuallyValid() {
        return true;
    }

    /**
     * Reads current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property as a list of
     * specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    @Transient
    <T> List<T> getDataAsArray(Class<T> elementClass);

    /**
     * Replaces current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property with specified
     * list.
     *
     * @param data new value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property.
     */
    void setDataAsArray(List<?> data);

    /**
     * Reads current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property as an instance of
     * specified class.
     *
     * @param objectClass the class of the value.
     * @param <T>         object type parameter
     * @return an instance of {@code objectClass}.
     */
    @Transient
    <T> T getDataAsObject(Class<T> objectClass);

    /**
     * Replaces current value of {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property with specified
     * object.
     *
     * @param data new value for {@link JsonrpcResponseMessageErrorConstants#PROPERTY_NAME_DATA} property.
     */
    void setDataAsObject(Object data);
}

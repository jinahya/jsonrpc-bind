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
import java.beans.Transient;
import java.util.List;

/**
 * An interface for JSON-RPC 2.0 response messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#response_object">Response Object (JSON-RPC 2.0)</a>
 */
@SuppressWarnings({"java:S1214"})
public interface JsonrpcResponseMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.result} part. The value is {@value}.
     * <blockquote>
     * This member is REQUIRED on success.
     * <br>This member MUST NOT exist if there was an error invoking the method.
     * <br>The value of this member is determined by the method invoked on the Server.
     * </blockquote>
     */
    String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error} part. The value is {@value}.
     * <blockquote>
     * This member is REQUIRED on error.
     * <br>This member MUST NOT exist if there was no error triggered during invocation.
     * <br>The value for this member MUST be an Object as defined in section <a href="https://www.jsonrpc.org/specification#error_object">5.1</a>.
     * </blockquote>
     *
     * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Object (JSON-RPC 2.0)</a>
     */
    String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether {@value #PROPERTY_NAME_RESULT} property and the {@value #PROPERTY_NAME_ERROR} property are
     * exclusively set.
     *
     * @return {@code true} if the {@value #PROPERTY_NAME_RESULT} property and the {@value #PROPERTY_NAME_ERROR}
     * property are exclusively set; {@code false} otherwise.
     * @implSpec The default implementation returns an XOR-ed value of {@link #hasResult()} and {@link #hasError()}.
     */
    @Transient
    default @AssertTrue boolean isResultAndErrorExclusive() {
        return hasResult() ^ hasError();
    }

    // ---------------------------------------------------------------------------------------------------------- result

    /**
     * Indicates this message has a value for {@value #PROPERTY_NAME_RESULT} property.
     *
     * @return {@code true} if this message has a value for {@value #PROPERTY_NAME_RESULT} property; {@code false}
     * otherwise.
     */
    @Transient
    boolean hasResult();

    /**
     * Indicates current value of {@value #PROPERTY_NAME_RESULT} property is contextually valid.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_RESULT} property is contextually valid; {@code
     * false} otherwise.
     * @implSpec It's considered to be contextually valid when {@link #hasResult()} returns {@code false}.
     * @implNote The default implementation returns {@code true}.
     * @see #PROPERTY_NAME_RESULT
     */
    @Transient
    default @AssertTrue boolean isResultContextuallyValid() {
        return true;
    }

    /**
     * Returns current value of {@value #PROPERTY_NAME_RESULT} property as a list of specified element class.
     *
     * @param elementClass the element class.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     * @apiNote This method may return {@code null} when {@link #hasResult()} returns {@code false}.
     */
    @Transient
    <T> List<T> getResultAsArray(Class<T> elementClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_RESULT} property with specified value.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     */
    void setResultAsArray(List<?> result);

    /**
     * Returns current value of {@value #PROPERTY_NAME_RESULT} property as an instance of specified object class.
     *
     * @param objectClass the object class.
     * @param <T>         object type parameter.
     * @return an instance of {@code objectClass}
     * @apiNote This method may return {@code null} when {@link #hasResult()} returns {@code false}.
     */
    @Transient
    <T> T getResultAsObject(Class<T> objectClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_RESULT} property with specified value.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     */
    void setResultAsObject(Object result);

    // ----------------------------------------------------------------------------------------------------------- error

    /**
     * Indicates this message has a value for {@link #PROPERTY_NAME_ERROR} property.
     *
     * @return {@code true} if this message has a value for {@link #PROPERTY_NAME_ERROR} property; {@code false}
     * otherwise.
     */
    @Transient
    boolean hasError();

    /**
     * Indicates that current value of {@value #PROPERTY_NAME_ERROR} property is contextually valid.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_ERROR} property is contextually valid; {@code
     * false} otherwise.
     * @implSpec It's considered to be contextually valid when {@link #hasError()} method returns {@code false}.
     * @implNote Default implementation returns {@code true}.
     * @see #PROPERTY_NAME_ERROR
     */
    @Transient
    default @AssertTrue boolean isErrorContextuallyValid() {
        return true;
    }

    /**
     * Returns current value of {@value #PROPERTY_NAME_ERROR} property as an instance of specified class.
     *
     * @param clazz the error class to bind.
     * @param <T>   error type parameter
     * @return an instance of {@code clazz}.
     */
    @Transient
    <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz);

    /**
     * Replaces current value of {@link #PROPERTY_NAME_ERROR} property with specified value.
     *
     * @param error new value for {@link #PROPERTY_NAME_ERROR} property.
     */
    void setErrorAs(JsonrpcResponseMessageError error);
}

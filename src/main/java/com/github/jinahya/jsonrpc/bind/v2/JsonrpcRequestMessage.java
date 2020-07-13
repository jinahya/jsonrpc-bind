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

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

import java.beans.Transient;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.ServiceLoader.load;

/**
 * An interface JSON-RPC 2.0 request messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#request_object">Request Object (JSON-RPC 2.0)</a>
 */
@SuppressWarnings({"java:S1214"})
public interface JsonrpcRequestMessage
        extends JsonrpcMessage {

    /**
     * The property name for {@code $.method} part. The value is {@value}.
     * <blockquote>
     * A String containing the name of the method to be invoked. Method names that begin with the word rpc followed by a
     * period character (U+002E or ASCII 46) are reserved for rpc-internal methods and extensions and MUST NOT be used
     * for anything else.
     * </blockquote>
     */
    String PROPERTY_NAME_METHOD = "method";

    /**
     * The name of the property for {@code $.params} part. The value is {@value}.
     * <blockquote>
     * A Structured value that holds the parameter values to be used during the invocation of the method. This member
     * MAY be omitted.
     * </blockquote>
     */
    String PROPERTY_NAME_PARAMS = "params";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    static JsonrpcRequestMessage newInstance() {
        return load(JsonrpcRequestMessage.class).iterator().next();
    }

    /**
     * Reads an instance from specified source.
     *
     * @param source the source from which the new instance is read.
     * @return a new instance read from {@code source}.
     */
    static JsonrpcRequestMessage fromJson(final Object source) {
        requireNonNull(source, "source is null");
        return load(JsonrpcRequestMessageService.class).iterator().next().fromJson(source);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Writes this message to specified target.
     *
     * @param target the target to which this message is written.
     */
    default void toJson(final Object target) {
        requireNonNull(target, "target is null");
        load(JsonrpcRequestMessageService.class).iterator().next().toJson(this, target);
    }

    /**
     * Returns a JSON representation of this message.
     *
     * @return a JSON representation of this message.
     */
    default String toJson() {
        return load(JsonrpcRequestMessageService.class).iterator().next().toJsonString(this);
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Indicates whether this message is a notification.
     * <blockquote>
     * A Notification is a Request object without an "id" member. A Request object that is a Notification signifies the
     * Client's lack of interest in the corresponding Response object, and as such no Response object needs to be
     * returned to the client. The Server MUST NOT reply to a Notification, including those that are within a batch
     * request. <br>Notifications are not confirmable by definition, since they do not have a Response object to be
     * returned. As such, the Client would not be aware of any errors (like e.g. "Invalid params","Internal error").
     * </blockquote>
     *
     * @return {@code true} if this message is a notification; {@code false} otherwise.
     * @implSpec Default implementation returns {@code !hasId()};
     */
    @Transient
    default boolean isNotification() {
        return !hasId();
    }

    // ---------------------------------------------------------------------------------------------------------- method

    /**
     * Returns current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    @NotBlank
    String getMethod();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    void setMethod(String method);

    /**
     * Indicates that current value of {@value #PROPERTY_NAME_METHOD} property is considered to be reserved for
     * rpc-internal.
     *
     * @return {@code true} if value of {@value #PROPERTY_NAME_METHOD} property is considered to be reserved for
     * rpc-internal; {@code false} otherwise.
     */
    @Transient
    default @AssertFalse boolean isMethodReservedForRpcInternal() {
        final String method = getMethod();
        return method == null || method.startsWith("rpc.");
    }

    // ---------------------------------------------------------------------------------------------------------- params

    /**
     * Indicates this message has a value for {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return {@code} true if this message has a value for {@value #PROPERTY_NAME_PARAMS} property; {@code false}
     * otherwise.
     * @implSpec Then this method returns {@code false}, any {@code getParamsAs...()} method should return {@code
     * null}.
     */
    @Transient
    boolean hasParams();

    /**
     * Indicate whether current value of {@value #PROPERTY_NAME_PARAMS} property is contextually valid.
     *
     * @return {@code true} if current value of {@value #PROPERTY_NAME_PARAMS} property is contextually valid; {@code
     * false} otherwise.
     * @apiNote It's considered to be contextually valid when {@link #hasParams()} returns {@code false}.
     */
    @Transient
    @AssertTrue
    boolean isParamsContextuallyValid();

    /**
     * Returns current value of {@value #PROPERTY_NAME_PARAMS} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}; {@code null} when {@link #hasParams()} method returns {@code false}.
     */
    <T> List<T> getParamsAsArray(final Class<T> elementClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsArray(List<?> params);

    /**
     * Returns current value of {@value #PROPERTY_NAME_PARAMS} property as an instance of specified object type.
     *
     * @param objectClass the object type.
     * @param <T>         object type parameter
     * @return an instance of {@code objectClass}; {@code null} when {@link #hasParams()} method returns {@code false}.
     */
    <T> T getParamsAsObject(final Class<T> objectClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_PARAMS} property with specified object.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsObject(Object params);
}

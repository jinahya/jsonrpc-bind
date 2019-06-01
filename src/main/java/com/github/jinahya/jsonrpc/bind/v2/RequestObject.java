package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Represents request objects.
 *
 * @param <T> {@code params} type parameter
 * @see <a href="https://www.jsonrpc.org/specification#request_object">4. Request Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class RequestObject<T> extends JsonrpcObject {

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
               "method=" + method +
               ",params=" + params +
               "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RequestObject)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final RequestObject<?> that = (RequestObject<?>) obj;
        return Objects.equals(getMethod(), that.getMethod()) &&
               Objects.equals(getParams(), that.getParams());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMethod(), getParams());
    }

    /**
     * Checks if method name is reserved for rpc-internal.
     *
     * @return {@code true} if {@code method} is reserved for rpc-internal, {@code false} otherwise
     */
    @JsonIgnore
    @JsonbTransient
    @AssertFalse
    private boolean isMethodNameReservedForRpcInternal() {
        return method != null && method.startsWith("rpc.");
    }

    /**
     * Checks if this request object is a notification. This method checks whether {@link #getId()} method returns
     * {@code null} or not.
     *
     * @return {@code true} if this request object is a notification, {@code false} otherwise
     * @see <a href="https://www.jsonrpc.org/specification#notification">4.1 Notification (JSON-RPC 2.0
     * Specification)</a>
     */
    @JsonIgnore
    @JsonbTransient
    public boolean isNotification() {
        return getId() == null;
    }

    /**
     * Returns the current value of {@code method} attribute.
     *
     * @return the current value of {@code method} attribute
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace the current value of {@code method} attribute with given.
     *
     * @param method new value for {@code method} attribute
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns the current value of {@code params} attribute.
     *
     * @return the current value of {@code params} attribute
     */
    public T getParams() {
        return params;
    }

    /**
     * Replaces the current value of {@code params} attribute with given.
     *
     * @param params new value for {@code params} attribute
     */
    public void setParams(final T params) {
        this.params = params;
    }

    @NotEmpty
    private String method;

    @Valid
    private T params;
}

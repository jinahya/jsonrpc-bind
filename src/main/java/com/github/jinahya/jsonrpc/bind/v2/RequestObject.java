package com.github.jinahya.jsonrpc.bind.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
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

    @Override
    public String toString() {
        return super.toString() + "{" +
               "method=" + method +
               ",params=" + params +
               "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestObject)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final RequestObject<?> that = (RequestObject<?>) o;
        return Objects.equals(getMethod(), that.getMethod()) &&
               Objects.equals(getParams(), that.getParams());
    }

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
     * Returns current value of {@code method} attribute.
     *
     * @return current value of {@code method} attribute
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace current value of {@code method} attribute with given.
     *
     * @param method new value for {@code method} attribute
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns current value of {@code params} attribute.
     *
     * @return current value of {@code params} attribute
     */
    public T getParams() {
        return params;
    }

    /**
     * Replaces current value of {@code params} attribute with given.
     *
     * @param params new value for {@code params} attribute
     */
    public void setParams(final T params) {
        this.params = params;
    }

    @NotEmpty
    private String method;

    private T params;
}

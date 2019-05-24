package com.github.jinahya.jsonrpc2.types;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * A class for request objects.
 *
 * @param <T> self type parameter
 * @param <U> {@code params} type parameter
 * @see <a href="https://www.jsonrpc.org/specification#request_object">4. Request Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class RequestObject<T extends RequestObject<T, U>, U> extends JsonrpcObject<T> {

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "method='" + method + '\''
               + ",params=" + params
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestObject)) {
            return false;
        }
        if (!super.equals(o)) return false;
        final RequestObject<?, ?> that = (RequestObject<?, ?>) o;
        return Objects.equals(getMethod(), that.getMethod())
               && Objects.equals(getParams(), that.getParams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMethod(), getParams());
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Checks if this request object is a notification.
     *
     * @return {@code true} if this request object is a notification, {@code false} otherwise
     * @see <a href="https://www.jsonrpc.org/specification#notification">4.1 Notification (JSON-RPC 2.0
     * Specification)</a>
     */
    public boolean isNotification() {
        return getId() == null;
    }

    // ---------------------------------------------------------------------------------------------------------- method
    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    @SuppressWarnings({"unchecked"})
    public T method(final String method) {
        setMethod(method);
        return (T) this;
    }

    /**
     * Checks if {@code method} attribute is starts with reserved for rpc-internal.
     *
     * @return {@code true} if reserved, {@code false} otherwise
     */
    @AssertFalse
    public boolean isMethodReservedForRpcInternal() {
        final String method = getMethod();
        return method != null && method.startsWith("rpc.");
    }

    // ---------------------------------------------------------------------------------------------------------- params

    /**
     * Returns current value of {@code params} attribute.
     *
     * @return current value of {@code params} attribute
     */
    public U getParams() {
        return params;
    }

    /**
     * Replaces value of {@code params} attribute with given.
     *
     * @param params new value for {@code params} attribute
     * @return this instance
     */
    public void setParams(final U params) {
        this.params = params;
    }

    /**
     * Replaces the value of {@code params} attribute with given and returns this instance.
     *
     * @param params new value for {@code params} attribute
     * @return this instance
     */
    @SuppressWarnings({"unchecked"})
    public T params(final U params) {
        setParams(params);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotEmpty
    private String method;

    private U params;
}

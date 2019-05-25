package com.github.jinahya.jsonrpc2.bind;

import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract class for request object and response object.
 *
 * @param <U> id type parameter
 */
public abstract class JsonrpcObject<U> implements Serializable {

    /**
     * A constant for {@code jsonrpc} attribute. The value is {@value #JSONRPC}.
     */
    public static final String JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc=" + jsonrpc
               + ",id=" + id
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonrpcObject)) {
            return false;
        }
        final JsonrpcObject<?> that = (JsonrpcObject<?>) o;
        return getJsonrpc().equals(that.getJsonrpc()) &&
               Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJsonrpc(), getId());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@code jsonrpc} attribute.
     *
     * @return the current value of {@code jsonrpc} attribute.
     */
    public String getJsonrpc() {
        return jsonrpc;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@code id} attribute.
     *
     * @return the current value of {@code id} attribute.
     */
    public U getId() {
        return id;
    }

    /**
     * Replaces current value of {@code id} attribute with given.
     *
     * @param id new value for {@code id} attribute.
     */
    public void setId(final U id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String jsonrpc = JSONRPC;

    private U id;
}

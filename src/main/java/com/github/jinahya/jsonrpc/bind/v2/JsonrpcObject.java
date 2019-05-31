package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract class for request object and response object.
 */

public abstract class JsonrpcObject implements Serializable {

    /**
     * A constant for {@code jsonrpc} attribute. The value is {@value #JSONRPC}.
     */
    public static final String JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
               "jsonrpc=" + jsonrpc +
               ",id=" + id +
               "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonrpcObject)) {
            return false;
        }
        final JsonrpcObject that = (JsonrpcObject) o;
        return getJsonrpc().equals(that.getJsonrpc()) &&
               Objects.equals(getId(), that.getId());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getJsonrpc(), getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    //@JsonIgnore
    //@JsonbTransient
    //@AssertTrue
    private boolean isIdValid() {
        if (id == null) { // a notification
            return true;
        }
        if (id instanceof String) {
            return true;
        }
        if (!(id instanceof Number)) {
            return false;
        }
        return true;
    }

    // --------------------------------------------------------------------------------------------------------- jsonrpc

    /**
     * Returns the current value of {@code jsonrpc} attribute which is always {@value #JSONRPC}.
     *
     * @return the current value of {@code jsonrpc} attribute.
     */
    public final String getJsonrpc() {
        return jsonrpc;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the current value of {@code id} attribute.
     *
     * @return the current value of {@code id} attribute.
     */
    public Object getId() {
        if (id == null) {
            return null;
        }
        if (id instanceof String) {
            return id;
        }
        if (id instanceof Number) {
            return ((Number) id).longValue();
        }
        return id;
    }

    /**
     * Replaces the current value of {@code id} attribute with given.
     *
     * @param id new value for {@code id} attribute.
     */
    public void setId(final Object id) {
        this.id = id;
        if (this.id instanceof Number) {
            this.id = ((Number) this.id).longValue();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    private final String jsonrpc = JSONRPC;

    private Object id;
}

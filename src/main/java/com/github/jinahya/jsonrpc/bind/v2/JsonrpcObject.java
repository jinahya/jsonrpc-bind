package com.github.jinahya.jsonrpc.bind.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.AssertTrue;
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

    @Override
    public String toString() {
        return super.toString() + "{" +
               "jsonrpc=" + jsonrpc +
               ",id=" + id +
               "}";
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
        final JsonrpcObject that = (JsonrpcObject) o;
        return getJsonrpc().equals(that.getJsonrpc()) &&
               Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJsonrpc(), getId());
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    @JsonIgnore
    @JsonbTransient
    @AssertTrue
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
     * Returns the current value of {@code jsonrpc} attribute.
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
            return id;
        }
        if (id instanceof String) {
            return id;
        }
        return ((Number) id).longValue();
    }

    /**
     * Replaces current value of {@code id} attribute with given.
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

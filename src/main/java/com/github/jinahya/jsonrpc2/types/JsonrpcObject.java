package com.github.jinahya.jsonrpc2.types;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * An abstract class for request object and response object.
 *
 * @param <T> self type parameter
 */
public abstract class JsonrpcObject<T extends JsonrpcObject<T>> implements Serializable {

    /**
     * A constant for {@code jsonrpc} attribute. The value is {@value #JSONRPC}.
     */
    public static final String JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc='" + jsonrpc + '\''
               + ",id='" + id + '\''
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
        return Objects.equals(getId(), that.getId());
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
    public String getId() {
        return id;
    }

    /**
     * Replaces current value of {@code id} attribute with given.
     *
     * @param id new value for {@code id} attribute.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Replaces current value of {@code id} attribute with given and returns this instance.
     *
     * @param id new value for {@code id} attribute.
     */
    @SuppressWarnings({"unchecked"})
    public T id(final String id) {
        setId(id);
        return (T) this;
    }

    @JsonIgnore
    @JsonbTransient
    public void setId(final Number id) {
        setId(ofNullable(id).map(Number::longValue).orElse(null));
    }

    @SuppressWarnings({"unchecked"})
    public T id(final Number id) {
        setId(id);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String jsonrpc = JSONRPC;

    private String id;
}

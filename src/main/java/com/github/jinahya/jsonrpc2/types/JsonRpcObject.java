package com.github.jinahya.jsonrpc2.types;

/**
 * An abstract class for request object and response object.
 * @param <T> self type parameter
 */
public abstract class JsonRpcObject<T extends JsonRpcObject<T>> {

    /**
     * A constant for {@code jsonrpc} attribute. The value is {@value #JSONRPC}.
     */
    public static final String JSONRPC = "2.0";

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

    // -----------------------------------------------------------------------------------------------------------------
    private final String jsonrpc = JSONRPC;

    private String id;
}

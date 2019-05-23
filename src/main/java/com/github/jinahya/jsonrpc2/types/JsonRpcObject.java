package com.github.jinahya.jsonrpc2.types;

public abstract class JsonRpcObject<T extends JsonRpcObject<T>> {

    public static final String JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------
    public String getJsonrpc() {
        return jsonrpc;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @SuppressWarnings({"unchecked"})
    public T id(final String id) {
        setId(id);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String jsonrpc = JSONRPC;

    private String id;
}

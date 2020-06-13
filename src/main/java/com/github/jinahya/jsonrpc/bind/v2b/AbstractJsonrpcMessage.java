package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class AbstractJsonrpcMessage implements JsonrpcMessage {

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc=" + jsonrpc
               + "}";
    }

    // --------------------------------------------------------------------------------------------------------- jsonrpc
    @Override
    public @Pattern(regexp = "2\\.0") @NotNull String getJsonrpc() {
        return jsonrpc;
    }

    @Override
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;
}

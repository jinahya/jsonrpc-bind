package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;

public abstract class AbstractJsonrpcResponseMessageError implements JsonrpcResponseMessageError {

    @Override
    public String toString() {
        return super.toString() + "{"
               + "code=" + code
               + ",message=" + message
               + "}";
    }

    // ------------------------------------------------------------------------------------------------------------ code
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(final int code) {
        this.code = code;
    }

    // --------------------------------------------------------------------------------------------------------- message
    @Override
    public @NotNull String getMessage() {
        return message;
    }

    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private int code;

    private String message = "";
}

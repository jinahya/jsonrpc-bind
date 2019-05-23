package com.github.jinahya.jsonrpc2.types;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;

public class RequestObject<T extends RequestObject<T, U>, U> extends JsonRpcObject<T> {

    // -----------------------------------------------------------------------------------------------------------------
    @AssertFalse
    private boolean isMethodReservedForRpcInternal() {
        return method.startsWith("rpc.");
    }

    // ---------------------------------------------------------------------------------------------------------- method
    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    // ---------------------------------------------------------------------------------------------------------- params
    public U getParams() {
        return params;
    }

    public void setParams(final U params) {
        this.params = params;
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public boolean isForNotification() {
        return getId() == null;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotEmpty
    private String method;

    private U params;
}

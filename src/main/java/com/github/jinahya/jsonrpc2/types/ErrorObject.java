package com.github.jinahya.jsonrpc2.types;

import javax.validation.constraints.NotNull;

public class ErrorObject<T extends ErrorObject<T, U>, U> {

    // -----------------------------------------------------------------------------------------------------------------
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public U getData() {
        return data;
    }

    public void setData(final U data) {
        this.data = data;
    }

    @SuppressWarnings({"unchecked"})
    public T data(final U data) {
        setData(data);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull // TODO: 2019-05-23 required, specifically?
    private Integer code;

    private String message;

    private U data;
}

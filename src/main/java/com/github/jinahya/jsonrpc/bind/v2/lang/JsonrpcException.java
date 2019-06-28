package com.github.jinahya.jsonrpc.bind.v2.lang;

public class JsonrpcException extends RuntimeException {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PROPERTY_NAME_CODE = "code";

    // -----------------------------------------------------------------------------------------------------------------
    public JsonrpcException(final String message, final Throwable cause, final int code) {
        super(message, cause, true, true);
        this.code = code;
    }

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns the current value of {@value #PROPERTY_NAME_CODE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_CODE} property.
     */
    public int getCode() {
        return code;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final int code;
}

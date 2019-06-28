package com.github.jinahya.jsonrpc.bind.v2.lang;

public class JsonrpcException extends RuntimeException {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property for {@code $.error.code} of {@link com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject}.
     */
    public static final String PROPERTY_NAME_CODE = "code";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()}
     *                method.
     * @param cause   the cause (which is saved for later retrieval by the {@link Throwable#getCause()}  method). (A
     *                {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param code    a value for {@value #PROPERTY_NAME_CODE} property.
     */
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

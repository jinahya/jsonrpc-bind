package com.github.jinahya.jsonrpc2.bind;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class ErrorObject<T> implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The minimum value for reserved codes. The value is {@value #MIN_RESERVED_CODE}.
     */
    public static final long MIN_RESERVED_CODE = -32768L;

    /**
     * The maximum value for reserved codes. The value is {@value #MAX_RESERVED_CODE}.
     */
    public static final long MAX_RESERVED_CODE = 32000L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final long CODE_PARSE_ERROR = -32700L;

    public static final long CODE_INVALID_REQUEST = -32600L;

    public static final long CODE_METHOD_NOT_FOUND = -32601L;

    public static final long CODE_INVALID_PARAMS = -32602L;

    public static final long CODE_INTERNAL_ERROR = -32603L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final long MIN_CODE_SERVER_ERROR = -32000L;

    public static final long MAX_CODE_SERVER_ERROR = -32099L;

    // -----------------------------------------------------------------------------------------------------------------
    public static class Undefined extends ErrorObject<Object> {

    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "code=" + code
               + ",message=" + message
               + ",data=" + data
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorObject)) {
            return false;
        }
        final ErrorObject<?> that = (ErrorObject<?>) o;
        return getCode() == that.getCode()
               && Objects.equals(getMessage(), that.getMessage())
               && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getData());
    }

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns current value of {@code code} attribute.
     *
     * @return current value of {@code code} attribute.
     */
    public long getCode() {
        return code;
    }

    /**
     * Replaces value of {@code code} attribute with given.
     *
     * @param code new value for {@code code} attribute
     */
    public void setCode(final long code) {
        this.code = code;
    }

    /**
     * Checks the current value of {@code code} attribute is between {@link #MIN_RESERVED_CODE} and {@link
     * #MAX_RESERVED_CODE} (both inclusive).
     *
     * @return {@code true} if the current value of {@code code} attribute is between {@value #MIN_RESERVED_CODE} and
     * {@value #MAX_RESERVED_CODE} (both inclusive).
     */
    public boolean isCodeReserved() {
        return code >= MIN_RESERVED_CODE && code <= MAX_RESERVED_CODE;
    }

    // --------------------------------------------------------------------------------------------------------- message
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    // ------------------------------------------------------------------------------------------------------------ data
    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private long code;

    @NotNull
    private String message;

    private T data;
}

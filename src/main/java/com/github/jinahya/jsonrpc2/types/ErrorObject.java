package com.github.jinahya.jsonrpc2.types;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class ErrorObject<T extends ErrorObject<T, U>, U> implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The minimum value for reserved codes. The value is {@value #MIN_RESERVED_CODE}.
     */
    public static final int MIN_RESERVED_CODE = -32768;

    /**
     * The maximum value for reserved codes. The value is {@value #MAX_RESERVED_CODE}.
     */
    public static final int MAX_RESERVED_CODE = 32000;

    // -----------------------------------------------------------------------------------------------------------------
    public static final int CODE_PARSE_ERROR = -32700;

    public static final int CODE_INVALID_REQUEST = -32600;

    public static final int CODE_METHOD_NOT_FOUND = -32601;

    public static final int CODE_INVALID_PARAMS = -32602;

    public static final int CODE_INTERNAL_ERROR = -32603;

    // -----------------------------------------------------------------------------------------------------------------
    public static final int MIN_CODE_SERVER_ERROR = -32000;

    public static final int MAX_CODE_SERVER_ERROR = -32099;

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "code=" + code
               + ",message='" + message + '\''
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
        final ErrorObject<?, ?> that = (ErrorObject<?, ?>) o;
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
    public int getCode() {
        return code;
    }

    /**
     * Replaces value of {@code code} attribute with given.
     *
     * @param code new value for {@code code} attribute
     */
    public void setCode(final int code) {
        this.code = code;
    }

    /**
     * Replaces value of {@code code} attribute with given and returns this instance.
     *
     * @param code new value for {@code code} attribute
     * @return this instance
     */
    @SuppressWarnings({"unchecked"})
    public T code(final int code) {
        setCode(code);
        return (T) this;
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

    @SuppressWarnings({"unchecked"})
    public T message(final String message) {
        setMessage(message);
        return (T) this;
    }

    // ------------------------------------------------------------------------------------------------------------ data
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
    private int code;

    @NotNull
    private String message;

    private U data;
}

package com.github.jinahya.jsonrpc2.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents error objects.
 *
 * @param <T> data type parameter
 * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Objects (JSON RPC 2.0 Specification)</a>
 */
public class ErrorObject<T> implements Serializable {

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

    /**
     * Represents error objects of unknown type of {@code data} attribute.
     */
    public static class UnknownData extends ErrorObject<Object> {

    }

    /**
     * Represents error objects without {@code data} attribute.
     */
    public static class NoData extends ErrorObject<Void> {

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
     * Checks the current value of {@code code} attribute is between {@link #MIN_RESERVED_CODE} and {@link
     * #MAX_RESERVED_CODE} (both inclusive).
     *
     * @return {@code true} if the current value of {@code code} attribute is between {@value #MIN_RESERVED_CODE} and
     * {@value #MAX_RESERVED_CODE} (both inclusive).
     */
    @JsonIgnore
    @JsonbTransient
    public boolean isCodeReserved() {
        return code >= MIN_RESERVED_CODE && code <= MAX_RESERVED_CODE;
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Returns current value of {@code message} attribute.
     *
     * @return current value of {@code message} attribute
     */
    public String getMessage() {
        return message;
    }

    /**
     * Replaces value of {@code message} attribute with given.
     *
     * @param message new value for {@code message} attribute
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    // ------------------------------------------------------------------------------------------------------------ data

    /**
     * Returns current value of {@code data} attribute.
     *
     * @return current value of {@code data} attribute
     */
    public T getData() {
        return data;
    }

    /**
     * Replaces value of {@code data} attribute with given.
     *
     * @param data new value for {@code data} attribute
     */
    public void setData(final T data) {
        this.data = data;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private int code;

    @NotNull
    private String message;

    private T data;
}

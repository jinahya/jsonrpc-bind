package com.github.jinahya.jsonrpc.bind.v2;

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

    /**
     * The minimum value for codes reserved for pre-defined errors. The value is {@value
     * #MIN_RESERVED_CODE_FOR_PREDEFINED_ERRORS}.
     */
    public static final long MIN_RESERVED_CODE_FOR_PREDEFINED_ERRORS = -32768L;

    /**
     * The maximum value for codes reserved for pre-defined errors. The value is {@value
     * #MAX_RESERVED_CODE_FOR_PREDEFINED_ERRORS}.
     */
    public static final long MAX_RESERVED_CODE_FOR_PREDEFINED_ERRORS = -32000L;

    public static final long CODE_PARSE_ERROR = -32700L;

    public static final long CODE_INVALID_REQUEST = -32600L;

    public static final long CODE_METHOD_NOT_FOUND = -32601L;

    public static final long CODE_INVALID_PARAMS = -32602L;

    public static final long CODE_INTERNAL_ERROR = -32603L;

    /**
     * The minimum value for codes reserved for implementation-defined server errors. The value is {@value
     * #MIN_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}.
     */
    public static final long MIN_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS = -32099L;

    /**
     * The maximum value for codes reserved for implementation-defined server errors. The value is {@value
     * #MAX_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}.
     */
    public static final long MAX_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS = -32000L;

    /**
     * Represents error objects of unknown type of {@code data} attribute.
     */
    public static class UnknownData extends ErrorObject<Object> {

    }

    /**
     * Represents error objects without {@code data} attribute.
     */
    public static class NoData extends ErrorObject<Void> {

        /**
         * {@inheritDoc} The {@code getData} method of {@code NoData} class always returns {@code null}.
         *
         * @return {@code null}
         */
        @Override
        public final Void getData() {
            final Void data = super.getData();
            if (data != null) {
                setData(null);
                return getData();
            }
            return data;
        }

        /**
         * {@inheritDoc} The {@code setData} method of {@code NoData} class does nothing.
         *
         * @param data new value for {@code data} attribute
         */
        @Override
        public final void setData(final Void data) {
            if (data != null) { // https://stackoverflow.com/a/644730/330457
                throw new IllegalArgumentException("can't set a non-null data: " + data);
            }
            super.setData(data);
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
               "code=" + code +
               ",message=" + message +
               ",data=" + data +
               "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorObject)) {
            return false;
        }
        final ErrorObject<?> that = (ErrorObject<?>) o;
        return getCode() == that.getCode() &&
               Objects.equals(getMessage(), that.getMessage()) &&
               Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getData());
    }

    /**
     * Returns the current value of {@code code} attribute.
     *
     * @return the current value of {@code code} attribute.
     */
    public long getCode() {
        return code;
    }

    /**
     * Replaces the current value of {@code code} attribute with given.
     *
     * @param code new value for {@code code} attribute
     */
    public void setCode(final long code) {
        this.code = code;
    }

    /**
     * Checks the current value of {@code code} attribute is for pre-defined errors. This method checks whether the
     * current value of {@code code} attribute is between {@link #MIN_RESERVED_CODE_FOR_PREDEFINED_ERRORS} and {@link
     * #MAX_RESERVED_CODE_FOR_PREDEFINED_ERRORS} (both inclusive) or not.
     *
     * @return {@code true} if the current value of {@code code} attribute is for pre-defined errors.
     */
    @JsonIgnore
    @JsonbTransient
    public boolean isCodeReservedForPredefinedErrors() {
        return code >= MIN_RESERVED_CODE_FOR_PREDEFINED_ERRORS && code <= MAX_RESERVED_CODE_FOR_PREDEFINED_ERRORS;
    }

    /**
     * Checks the current value of {@code code} attribute is for implementation-defined server errors. This method
     * checks whether the current value of {@code code} attribute is between {@link
     * #MIN_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS} and {@link #MAX_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}
     * (both inclusive) or not.
     *
     * @return {@code true} if the current value of {@code code} attribute is for implementation-defined server errors.
     */
    @JsonIgnore
    @JsonbTransient
    public boolean isCodeReservedForImplementationDefinedServerErrors() {
        return code >= MIN_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS &&
               code <= MAX_RESERVED_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS;
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Returns the current value of {@code message} attribute.
     *
     * @return the current value of {@code message} attribute
     */
    public String getMessage() {
        return message;
    }

    /**
     * Replaces the current value of {@code message} attribute with given.
     *
     * @param message new value for {@code message} attribute
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    // ------------------------------------------------------------------------------------------------------------ data

    /**
     * Returns the current value of {@code data} attribute.
     *
     * @return the current value of {@code data} attribute
     */
    public T getData() {
        return data;
    }

    /**
     * Replaces the current value of {@code data} attribute with given.
     *
     * @param data new value for {@code data} attribute
     */
    public void setData(final T data) {
        this.data = data;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private long code;

    @NotNull
    private String message;

    private T data;
}

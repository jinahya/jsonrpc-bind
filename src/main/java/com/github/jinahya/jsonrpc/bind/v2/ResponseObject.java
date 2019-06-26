package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * A class for binding response objects.
 *
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <IdType>     id type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">Response Object (JSON-RPC 2.0 Specification)</a>
 */
public class ResponseObject<ResultType, ErrorType extends ResponseObject.ErrorObject<?>, IdType>
        extends JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.result}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A class for binding {@code error} property of response objects.
     *
     * @param <DataType> data type parameter
     * @see <a href="https://www.jsonrpc.org/specification#error_object">Error Object (JSON-RPC 2.0 Specification)</a>
     */
    public static class ErrorObject<DataType> {

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The property name for {@code $.error.code}.
         */
        public static final String PROPERTY_NAME_CODE = "code";

        /**
         * The property name for {@code $.error.message}.
         */
        public static final String PROPERTY_NAME_MESSAGE = "message";

        /**
         * The property name for {@code $.error.data}.
         */
        public static final String PROPERTY_NAME_DATA = "data";

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The minimum value for codes reserved for pre-defined errors. The value is {@value
         * #MIN_CODE_PREDEFINED_ERROR}.
         */
        public static final long MIN_CODE_PREDEFINED_ERROR = -32768L;

        /**
         * The maximum value for codes reserved for pre-defined errors. The value is {@value
         * #MAX_CODE_PREDEFINED_ERROR}.
         */
        public static final long MAX_CODE_PREDEFINED_ERROR = -32000L;

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The code value for <i>parse error</i> meaning an invalid json received by the server or an error occurred on
         * the server while parsing the JSON text. The value is {@value #CODE_PARSE_ERROR}.
         */
        public static final long CODE_PARSE_ERROR = -32700L;

        /**
         * The code value for <i>invalid request</i> meaning the JSON sent is not a valid request object. The value is
         * {@value #CODE_INVALID_REQUEST}.
         */
        public static final long CODE_INVALID_REQUEST = -32600L;

        /**
         * The code value for <i>method not found</i> meaning the method does not exist nor available. The value is
         * {@value #CODE_METHOD_NOT_FOUND}.
         */
        public static final long CODE_METHOD_NOT_FOUND = -32601L;

        /**
         * The code value for <i>invalid params</i> meaning invalid parameter(s). The value is {@value
         * #CODE_INVALID_PARAMS}.
         */
        public static final long CODE_INVALID_PARAMS = -32602L;

        /**
         * The code value for <i>internal error</i> meaning an internal JSON-RPC error. The value is {@value
         * #CODE_INTERNAL_ERROR}.
         */
        public static final long CODE_INTERNAL_ERROR = -32603L;

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The minimum value for codes reserved for implementation-defined server errors. The value is {@value
         * #MIN_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR}.
         */
        public static final long MIN_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32099L;

        /**
         * The maximum value for codes reserved for implementation-defined server errors. The value is {@value
         * #MAX_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR}.
         */
        public static final long MAX_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32000L;

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new instance of specified object type whose {@value #PROPERTY_NAME_CODE} property, {@value
         * #PROPERTY_NAME_MESSAGE} property, and {@value #PROPERTY_NAME_DATA} property set with specified values.
         *
         * @param clazz   the class of the object to create.
         * @param code    the value for {@value #PROPERTY_NAME_CODE} property.
         * @param message the value for {@value #PROPERTY_NAME_MESSAGE} property.
         * @param data    the value for {@value #PROPERTY_NAME_DATA} property.
         * @param <T>     object type parameter
         * @param <U>     data type parameter
         * @return a new instance of specified object type.
         */
        public static <T extends ErrorObject<U>, U> T of(final Class<? extends T> clazz, final long code,
                                                         final String message, final U data) {
            try {
                final Constructor<? extends T> constructor = clazz.getConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                final T instance = constructor.newInstance();
                instance.setCode(code);
                instance.setMessage(message);
                instance.setData(data);
                return instance;
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException("failed to create an instance of " + clazz, roe);
            }
        }

        /**
         * Creates a new instance whose {@value #PROPERTY_NAME_CODE} property, {@value #PROPERTY_NAME_MESSAGE} property,
         * and {@value #PROPERTY_NAME_DATA} property set with specified values.
         *
         * @param code    the value for {@value #PROPERTY_NAME_CODE} property.
         * @param message the value for {@value #PROPERTY_NAME_MESSAGE} property.
         * @param data    the value for {@value #PROPERTY_NAME_DATA} property.
         * @param <T>     data type parameter
         * @return a new instance.
         */
        public static <T> ErrorObject<T> of(final long code, final String message, final T data) {
            @SuppressWarnings({"unchecked"})
            final Class<ErrorObject<T>> clazz = (Class<ErrorObject<T>>) (Class<?>) ErrorObject.class;
            return of(clazz, code, message, data);
        }

        /**
         * Creates a new instance.
         */
        public ErrorObject() {
            super();
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Returns a string representation of the object.
         *
         * @return a string representation of the object.
         */
        @Override
        public String toString() {
            return super.toString() + "{"
                   + "code=" + code
                   + ",message=" + message
                   + ",data=" + data
                   + "}";
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Indicates whether some other object is "equal to" this one.
         *
         * @param obj the reference object with which to compare
         * @return {@code true} if this object is the same as the {@code obj} argument; {@code false} otherwise.
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorObject)) {
                return false;
            }
            final ErrorObject<?> that = (ErrorObject<?>) obj;
            return getCode() == that.getCode()
                   && Objects.equals(getMessage(), that.getMessage())
                   && Objects.equals(getData(), that.getData());
        }

        /**
         * Returns a hash code value for the object.
         *
         * @return a hash code value for this object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(getCode(), getMessage(), getData());
        }

        // -------------------------------------------------------------------------------------------------------- code

        /**
         * Returns the current value of {@value #PROPERTY_NAME_CODE} property.
         *
         * @return the current value of {@value #PROPERTY_NAME_CODE} property.
         */
        public long getCode() {
            return code;
        }

        /**
         * Replaces the current value of {@value #PROPERTY_NAME_CODE} property with specified value.
         *
         * @param code new value for {@value #PROPERTY_NAME_CODE} property.
         */
        public void setCode(final long code) {
            this.code = code;
        }

        // ----------------------------------------------------------------------------------------------------- message

        /**
         * Returns the current value of {@value #PROPERTY_NAME_MESSAGE} property.
         *
         * @return the current value of {@value #PROPERTY_NAME_MESSAGE} property.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Replaces the current value of {@value #PROPERTY_NAME_MESSAGE} property with specified value.
         *
         * @param message new value for {@value #PROPERTY_NAME_MESSAGE} property.
         */
        public void setMessage(final String message) {
            this.message = message;
        }

        // -------------------------------------------------------------------------------------------------------- data

        /**
         * Returns the current value of {@value #PROPERTY_NAME_DATA} property.
         *
         * @return the current value of {@value #PROPERTY_NAME_DATA} property.
         */
        public DataType getData() {
            return data;
        }

        /**
         * Replaces the current value of {@value #PROPERTY_NAME_DATA} property with specified value.
         *
         * @param data new value for {@value #PROPERTY_NAME_DATA} property.
         */
        public void setData(final DataType data) {
            this.data = data;
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * An attribute for {@value #PROPERTY_NAME_CODE} property.
         */
        private long code = CODE_INTERNAL_ERROR;

        /**
         * An attribute for {@value #PROPERTY_NAME_MESSAGE} property.
         */
        @NotNull
        private String message = "";

        /**
         * An attribute for {@value #PROPERTY_NAME_DATA} property.
         */
        @Valid
        private DataType data;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified class whose properties are set with given values.
     *
     * @param clazz  the class of the object to create
     * @param result a value for {@value #PROPERTY_NAME_RESULT} property.
     * @param error  a value for {@value #PROPERTY_NAME_ERROR} property.
     * @param id     a value for {@value #PROPERTY_NAME_ID} property.
     * @param <T>    object type parameter
     * @param <U>    result type parameter
     * @param <V>    error type parameter
     * @param <W>    id type parameter
     * @return a new instance of specified class.
     */
    public static <T extends ResponseObject<U, V, W>, U, V extends ErrorObject<?>, W> T of(
            final Class<? extends T> clazz, final U result, final V error, final W id) {
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isSynthetic()) {
                constructor.setAccessible(true);
            }
            final T instance = constructor.newInstance();
            instance.setResult(result);
            instance.setError(error);
            instance.setId(id);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    @Deprecated
    public static <T extends ResponseObject<U, V, W>, U, V extends ErrorObject<?>, W> T ofResult(
            final Class<? extends T> clazz, final U result, final W id) {
        return of(clazz, result, null, id);
    }

    @Deprecated
    public static <T extends ResponseObject<U, V, W>, U, V extends ErrorObject<?>, W> T ofError(
            final Class<? extends T> clazz, final V error, final W id) {
        return of(clazz, null, error, id);
    }

    /**
     * Creates a new instance whose properties are set with given values.
     *
     * @param result a value for {@value #PROPERTY_NAME_RESULT} property.
     * @param error  a value for {@value #PROPERTY_NAME_ERROR} property.
     * @param id     a value for {@value #PROPERTY_NAME_ID} property.
     * @param <U>    result type parameter
     * @param <V>    error type parameter
     * @param <W>    id type parameter
     * @return a new instance of specified class.
     */
    public static <U, V extends ErrorObject<?>, W> ResponseObject<U, V, W> of(final U result, final V error,
                                                                              final W id) {
        @SuppressWarnings({"unchecked"})
        final Class<ResponseObject<U, V, W>> clazz = (Class<ResponseObject<U, V, W>>) (Class<?>) ResponseObject.class;
        return of(clazz, result, error, id);
    }

    @Deprecated
    public static <U, V extends ErrorObject<?>, W> ResponseObject<U, V, W> ofResult(final U result, final W id) {
        return of(result, null, id);
    }

    @Deprecated
    public static <U, V extends ErrorObject<?>, W> ResponseObject<U, V, W> ofError(final V error, final W id) {
        return of(null, error, id);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public ResponseObject() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "result=" + result
               + ",resultSemanticallyNull=" + isResultSemanticallyNull()
               + ",error=" + error
               + ",errorSemanticallyNull=" + isErrorSemanticallyNull()
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResponseObject)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final ResponseObject<?, ?, ?> that = (ResponseObject<?, ?, ?>) obj;
        return Objects.equals(getResult(), that.getResult()) && Objects.equals(getError(), that.getError());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResult(), getError());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Checks either {@value #PROPERTY_NAME_RESULT} property or {@value #PROPERTY_NAME_ERROR} property is set
     * exclusively. The {@code isEitherResultOrErrorExclusivelyNull()} method of {@code ResponseObject} class returns
     * the value of following expression.
     * <blockquote><pre>{@code isResultSemanticallyNull() ^ isErrorSemanticallyNull()}</pre></blockquote>
     *
     * @return {@code true} if either {@value #PROPERTY_NAME_RESULT} property or {@value #PROPERTY_NAME_ERROR} property
     * is set exclusively; {@code false} otherwise.
     * @see #isResultSemanticallyNull()
     * @see #isErrorSemanticallyNull()
     */
    @AssertTrue
    protected boolean isEitherResultOrErrorExclusivelyNull() {
        return isResultSemanticallyNull() ^ isErrorSemanticallyNull();
    }

    // ---------------------------------------------------------------------------------------------------------- result

    /**
     * Returns the current value of {@value #PROPERTY_NAME_RESULT} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_RESULT} property.
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_RESULT} property with specified value.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     * @see #setResultExclusively(Object)
     */
    public void setResult(final ResultType result) {
        this.result = result;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_RESULT} property with specified value <i>exclusively</i>.
     * This method invokes {@link #setResult(Object)} with specified value and, if {@link #getResult()} method returns a
     * non-{@code null} value, invokes {@link #setError(ErrorObject)} method with {@code null}.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     * @see #setResult(Object)
     */
    public void setResultExclusively(final ResultType result) {
        setResult(result);
        if (getResult() != null) {
            setError(null);
        }
    }

    /**
     * Checks whether the current value of {@value #PROPERTY_NAME_RESULT} property is <i>semantically</i> {@code null}
     * or not.
     * <p>
     * The {@code isResultSemanticallyNull()} method of {@code ResponseObject} class returns the value of following
     * expression.
     * <blockquote><pre>{@code getResult() == null}</pre></blockquote>
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_RESULT} property is <i>semantically</i>
     * {@code null}; {@code false} otherwise.
     */
    protected boolean isResultSemanticallyNull() {
        return getResult() == null;
    }

    // ----------------------------------------------------------------------------------------------------------- error

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ERROR} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_ERROR} property.
     */
    public ErrorType getError() {
        return error;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ERROR} property with specified value.
     *
     * @param error new value for {@value #PROPERTY_NAME_ERROR} property.
     * @see #setErrorExclusively(ErrorObject)
     */
    public void setError(final ErrorType error) {
        this.error = error;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ERROR} property with specified value <i>exclusively</i>.
     * This method invokes {@link #setError(ErrorObject)} with specified argument and, if {@link #getError()} method
     * returns a non-{@code null} value, invokes {@link #setResult(Object)} method with {@code null}.
     *
     * @param error new value for {@value #PROPERTY_NAME_ERROR} property.
     * @see #setError(ErrorObject)
     */
    public void setErrorExclusively(final ErrorType error) {
        setError(error);
        if (getError() != null) {
            setResult(null);
        }
    }

    /**
     * Checks whether the current value of {@value #PROPERTY_NAME_ERROR} property is <i>semantically</i> {@code null} or
     * not.
     * <p>
     * The {@code isErrorSemanticallyNull()} method of {@code ResponseObject} class returns the value of following
     * expression.
     * <blockquote><pre>{@code getError() == null}</pre></blockquote>
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_ERROR} property is <i>semantically</i> {@code
     * null}; {@code false} otherwise.
     */
    protected boolean isErrorSemanticallyNull() {
        return getError() == null;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_RESULT} property.
     */
    @Valid
    private ResultType result;

    /**
     * An attribute for {@value #PROPERTY_NAME_ERROR} property.
     */
    @Valid
    private ErrorType error;
}

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

/**
 * A class for representing <a href="https://www.jsonrpc.org/specification#response_object">Response Object</a>s.
 *
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <IdType>     id type parameter.
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<ResultType, ErrorType extends ResponseObject.ErrorObject<?>, IdType>
        extends JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code result}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code error}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A class for representing <a href="https://www.jsonrpc.org/specification#error_object">Error Object</a>s.
     *
     * @param <DataType> data type parameter.
     * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Object (JSON-RPC 2.0
     * Specification)</a>
     */
    public static class ErrorObject<DataType> {

        /**
         * The name for {@code code} property.
         */
        public static final String PROPERTY_NAME_CODE = "code";

        /**
         * The name for {@code message} property.
         */
        public static final String PROPERTY_NAME_MESSAGE = "message";

        /**
         * The name for {@code data} property.
         */
        public static final String PROPERTY_NAME_DATA = "data";

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

        /**
         * The code value for <i>parse error</i> meaning an invalid json received by the server or an error occurred on
         * the server while parsing the JSON text. The value is {@value #CODE_PARSE_ERROR}.
         */
        public static final long CODE_PARSE_ERROR = -32700L;

        /**
         * The code value for <i>invalid calculatorRequest</i> meaning the JSON sent is not a valid calculatorRequest
         * object. The value is {@value #CODE_INVALID_REQUEST}.
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
         * An abstract class for builder classes of subclasses of {@link ErrorObject}.
         *
         * @param <T>        builder type parameter
         * @param <U>        error object type parameter
         * @param <DataType> data type parameter
         */
        protected static abstract class AbstractBuilder<
                T extends AbstractBuilder<T, U, DataType>, U extends ErrorObject<DataType>, DataType>
                extends _AbstractBuilder<T, U> {

            /**
             * Creates a new instance for specified error object class.
             *
             * @param objectClass the error object class.
             */
            protected AbstractBuilder(final Class<? extends U> objectClass) {
                super(objectClass);
            }

            /**
             * {@inheritDoc}
             *
             * @return {@inheritDoc}
             */
            @Override
            public U build() {
                final U instance = super.build();
                instance.setData(data);
                return instance;
            }

            /**
             * Sets {@code data} attribute and return this builder instance.
             *
             * @param data new value for {@value #PROPERTY_NAME_DATA} attribute.
             * @return this builder instance.
             */
            @SuppressWarnings({"unchecked"})
            public T data(final DataType data) {
                this.data = data;
                return (T) this;
            }

            private DataType data;
        }

        public static class Builder<DataType>
                extends AbstractBuilder<Builder<DataType>, ErrorObject<DataType>, DataType> {

            @SuppressWarnings({"unchecked"})
            private Builder() {
                super((Class<ErrorObject<DataType>>) (Class<?>) ErrorObject.class);
            }
        }

        public static <DataType> Builder<DataType> builder() {
            return new Builder<>();
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
         * Replaces the current value of {@value #PROPERTY_NAME_CODE} property with given.
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
         * Replaces the current value of {@value #PROPERTY_NAME_MESSAGE} property.
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
         * Replaces the current value of {@value #PROPERTY_NAME_DATA} property.
         *
         * @param data new value for {@value #PROPERTY_NAME_DATA} property.
         */
        public void setData(final DataType data) {
            this.data = data;
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The attribute for {@value #PROPERTY_NAME_CODE} property.
         */
        private long code;

        /**
         * The attribute for {@value #PROPERTY_NAME_MESSAGE} property.
         */
        @NotNull
        private String message;

        /**
         * The attribute for {@value #PROPERTY_NAME_DATA} property.
         */
        @Valid
        private DataType data;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An abstract class for defining builders of specific subclass of {@link ResponseObject}.
     *
     * @param <T>          builder type parameter .
     * @param <U>          response object type parameter.
     * @param <ResultType> result type parameter.
     * @param <ErrorType>  error type parameter.
     * @param <IdType>     id type parameter.
     */
    protected static abstract class AbstractBuilder<
            T extends AbstractBuilder<T, U, ResultType, ErrorType, IdType>,
            U extends ResponseObject<ResultType, ErrorType, IdType>,
            ResultType,
            ErrorType extends ErrorObject<?>,
            IdType>
            extends JsonrpcObject.AbstractBuilder<T, U, IdType> {

        /**
         * Creates a new instance for specified response object class.
         *
         * @param objectClass the response object class to build.
         */
        protected AbstractBuilder(final Class<? extends U> objectClass) {
            super(objectClass);
        }

        /**
         * Sets {@value #PROPERTY_NAME_RESULT} property with specified value and returns this builder instance.
         *
         * @param result the value for {@value #PROPERTY_NAME_RESULT} property.
         * @return this builder instance.
         */
        @SuppressWarnings({"unchecked"})
        public T result(final ResultType result) {
            this.result = result;
            return (T) this;
        }

        /**
         * Sets {@value #PROPERTY_NAME_ERROR} property with specified value and returns this builder instance.
         *
         * @param error the value for {@value #PROPERTY_NAME_ERROR} property.
         * @return this builder instance.
         */
        @SuppressWarnings({"unchecked"})
        public T error(final ErrorType error) {
            this.error = error;
            return (T) this;
        }

        @Override
        public U build() {
            final U instance = super.build();
            instance.setResult(result);
            instance.setError(error);
            return instance;
        }

        private ResultType result;

        private ErrorType error;
    }

    /**
     * A class for building {@link ResponseObject} of specified type parameters.
     *
     * @param <ResultType> result type parameter.
     * @param <ErrorType>  error type parameter.
     * @param <X>          id type parameter.
     */
    public static class Builder<ResultType, ErrorType extends ErrorObject<?>, X>
            extends AbstractBuilder<
            Builder<ResultType, ErrorType, X>,
            ResponseObject<ResultType, ErrorType, X>,
            ResultType,
            ErrorType, X> {

        /**
         * Creates a new instance.
         */
        @SuppressWarnings({"unchecked"})
        private Builder() {
            super((Class<? extends ResponseObject<ResultType, ErrorType, X>>) (Class<?>) ResponseObject.class);
        }
    }

    public static <ResultType, ErrorType extends ErrorObject<?>, IdType> Builder<ResultType, ErrorType, IdType> builder() {
        return new Builder<>();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "result=" + result
               + ",error=" + error
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Checks if the {@value #PROPERTY_NAME_RESULT} property and the {@value #PROPERTY_NAME_ERROR} property are
     * exclusive.
     *
     * @return {@code true} if {@value #PROPERTY_NAME_RESULT} and {@value #PROPERTY_NAME_ERROR} set exclusively, {@code
     * false} otherwise.
     */
    @AssertTrue(message = "result and error should be set exclusively")
    private boolean isResultAndErrorExclusive() {
        return (getResult() != null) ^ (getError() != null);
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
     * Replaces the current value of {@value #PROPERTY_NAME_RESULT} property with given.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     * @see #setResultExclusively(Object)
     */
    public void setResult(final ResultType result) {
        this.result = result;
    }

    /**
     * Sets {@value #PROPERTY_NAME_RESULT} property with specified value <i>exclusively</i>. This method invokes {@link
     * #setResult(Object)} with specified value and, if {@link #getResult()} method returns {@code null}, invokes {@link
     * #setError(ErrorObject)} method with {@code null}.
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
     * Replaces the current value of {@value #PROPERTY_NAME_ERROR} property with given.
     *
     * @param error new value for {@value #PROPERTY_NAME_ERROR} property.
     * @see #setErrorExclusively(ErrorObject)
     */
    public void setError(final ErrorType error) {
        this.error = error;
    }

    /**
     * Sets {@value #PROPERTY_NAME_ERROR} property with specified value <i>exclusively</i>. This method invokes {@link
     * #setError(ErrorObject)} with specified argument and, if {@link #getError()} method returns {@code null}, invokes
     * {@link #setResult(Object)} method with {@code null}.
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

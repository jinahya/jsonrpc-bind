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
     * A class for binding {@value #PROPERTY_NAME_ERROR} property of response objects.
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
         * @param obj the reference object which which to compare
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
        private long code;

        /**
         * An attribute for {@value #PROPERTY_NAME_MESSAGE} property.
         */
        @NotNull
        private String message;

        /**
         * An attribute for {@value #PROPERTY_NAME_DATA} property.
         */
        @Valid
        private DataType data;
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
     * Checks whether the {@value #PROPERTY_NAME_RESULT} property and the {@value #PROPERTY_NAME_ERROR} are exclusively
     * set to each other.
     *
     * @return {@code true} if {@value #PROPERTY_NAME_RESULT} property and {@value #PROPERTY_NAME_ERROR} property set
     * exclusively, {@code false} otherwise.
     * @see #isResultSemanticallyNull()
     * @see #isErrorSemanticallyNull()
     */
    @AssertTrue(message = "result and error should be set exclusively")
    private boolean isResultAndErrorExclusive() {
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
     * This method invokes {@link #setResult(Object)} with specified value and, if {@link #getResult()} method returns
     * {@code null}, invokes {@link #setError(ErrorObject)} method with {@code null}.
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
     * Checks whether the current value of {@value #PROPERTY_NAME_RESULT} property is semantically {@code null}.
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_RESULT} property is semantically {@code
     * null}; {@code false} otherwise.
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
     * returns {@code null}, invokes {@link #setResult(Object)} method with {@code null}.
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
     * Checks whether the current value of {@value #PROPERTY_NAME_ERROR} property is semantically {@code null}.
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_ERROR} property is semantically {@code null};
     * {@code false} otherwise.
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

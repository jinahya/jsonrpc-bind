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
 * @param <ResultType> {@value PROPERTY_NAME_RESULT} type parameter
 * @param <ErrorType>  {@value PROPERTY_NAME_ERROR} type parameter
 * @param <IdType>     {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#response_object">Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<ResultType, ErrorType extends ResponseObject.ErrorObject<?>, IdType>
        extends JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A class for binding {@value com.github.jinahya.jsonrpc.bind.v2.ResponseObject#PROPERTY_NAME_ERROR} property of
     * response objects.
     *
     * @param <DataType> {@value PROPERTY_NAME_DATA} type parameter
     * @see <a href="https://www.jsonrpc.org/specification#error_object">Error Object (JSON-RPC 2.0
     * Specification)</a>
     */
    public static class ErrorObject<DataType> {

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The property name for {@code $.error.code} of response objects. The value is {@value}.
         */
        public static final String PROPERTY_NAME_CODE = "code";

        /**
         * The property name for {@code $.error.message} of response objects. The value is {@value}.
         */
        public static final String PROPERTY_NAME_MESSAGE = "message";

        /**
         * The property name for {@code $.error.data} of response objects. The value is {@value}.
         */
        public static final String PROPERTY_NAME_DATA = "data";

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The minimum value for codes reserved for pre-defined errors. The value is {@value}.
         */
        public static final int MIN_CODE_PREDEFINED_ERROR = -32768;

        /**
         * The maximum value for codes reserved for pre-defined errors. The value is {@value}.
         */
        public static final int MAX_CODE_PREDEFINED_ERROR = -32000;

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The code value for <i>parse error</i> meaning an invalid json received by the server or an error occurred on
         * the server while parsing the JSON text. The value is {@value}.
         */
        public static final int CODE_PARSE_ERROR = -32700;

        /**
         * The code value for <i>invalid request</i> meaning the JSON sent is not a valid request object. The value is
         * {@value}.
         */
        public static final int CODE_INVALID_REQUEST = -32600;

        /**
         * The code value for <i>method not found</i> meaning the method does not exist nor available. The value is
         * {@value}.
         */
        public static final int CODE_METHOD_NOT_FOUND = -32601;

        /**
         * The code value for <i>invalid params</i> meaning invalid parameter(s). The value is {@value}.
         */
        public static final int CODE_INVALID_PARAMS = -32602;

        /**
         * The code value for <i>internal error</i> meaning an internal JSON-RPC error. The value is {@value}.
         */
        public static final int CODE_INTERNAL_ERROR = -32603;

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The minimum value for codes reserved for implementation-defined server errors. The value is {@value}.
         */
        public static final int MIN_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32099;

        /**
         * The maximum value for codes reserved for implementation-defined server errors. The value is {@value}.
         */
        public static final int MAX_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32000;

        // -------------------------------------------------------------------------------------------------------------

//        /**
//         * Creates a new instance of specified class whose properties are set with specified values.
//         *
//         * @param clazz   the class of the object to create.
//         * @param code    a value for {@value #PROPERTY_NAME_CODE} property.
//         * @param message a value for {@value #PROPERTY_NAME_MESSAGE} property.
//         * @param data    a value for {@value #PROPERTY_NAME_DATA} property.
//         * @param <T>     object type parameter
//         * @param <U>     {@value #PROPERTY_NAME_DATA} type parameter
//         * @return a new instance of specified object type.
//         */
//        static <T extends ErrorObject<? super U>, U> T of(final Class<? extends T> clazz, final Integer code,
//                                                          final String message, final U data) {
//            try {
//                final Constructor<? extends T> constructor = requireNonNull(clazz, "clazz is null").getConstructor();
//                if (!constructor.isAccessible()) {
//                    constructor.setAccessible(true);
//                }
//                final T instance = constructor.newInstance();
//                instance.setCode(code);
//                instance.setMessage(message);
//                instance.setData(data);
//                return instance;
//            } catch (final ReflectiveOperationException roe) {
//                throw new RuntimeException("failed to create an instance of " + clazz, roe);
//            }
//        }

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
            return Objects.equals(code, that.getCode()) &&
                   Objects.equals(message, that.getMessage()) &&
                   Objects.equals(data, that.getData());
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
         * Returns the current value of {@value #PROPERTY_NAME_CODE} property. Note that the value of the {@value
         * #PROPERTY_NAME_CODE} property must be {@link NotNull} in a Bean-Validation context.
         *
         * @return the current value of {@value #PROPERTY_NAME_CODE} property.
         */
        public Integer getCode() {
            return code;
        }

        /**
         * Replaces the current value of {@value #PROPERTY_NAME_CODE} property with specified value.
         *
         * @param code new value for {@value #PROPERTY_NAME_CODE} property.
         */
        public void setCode(final Integer code) {
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
        @NotNull
        private Integer code;

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
     * The property name for {@code $.result}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------

//    /**
//     * Creates a new instance of specified class whose properties are set with specified values.
//     *
//     * @param clazz   the class of the object to create.
//     * @param jsonrpc a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_JSONRPC}
//     *                property.
//     * @param result  a value for {@value #PROPERTY_NAME_RESULT} property.
//     * @param error   a value for {@value #PROPERTY_NAME_ERROR} property.
//     * @param id      a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} property.
//     * @param <T>     object type parameter
//     * @param <U>     {@value #PROPERTY_NAME_RESULT} type parameter
//     * @param <V>     {@value #PROPERTY_NAME_ERROR} type parameter
//     * @param <W>     {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} type parameter
//     * @return a new instance of specified class.
//     */
//    static <T extends ResponseObject<? super U, ? super V, ? super W>, U, V extends ErrorObject<?>, W> T of(
//            final Class<? extends T> clazz, final String jsonrpc, final U result, final V error, final W id) {
//        final T instance = of(clazz, jsonrpc, id);
//        instance.setResult(result);
//        instance.setError(error);
//        instance.setId(id);
//        return instance;
//    }

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
               + ",resultNull=" + isResultNull()
               + ",error=" + error
               + ",errorNull=" + isErrorNull()
               + "}";
    }

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

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    /**
     * Indicates either {@value #PROPERTY_NAME_RESULT} property or {@value #PROPERTY_NAME_ERROR} property is set
     * exclusively. The {@code isResultErrorExclusive()} method of {@code ResponseObject} class returns the value of
     * following expression.
     * <blockquote><pre>{@code isResultNull() ^ isErrorNull()}</pre></blockquote>
     *
     * @return {@code true} if either {@value #PROPERTY_NAME_RESULT} property or {@value #PROPERTY_NAME_ERROR} property
     * is set exclusively; {@code false} otherwise.
     * @see #isResultNull()
     * @see #isErrorNull()
     */
    protected @AssertTrue boolean isResultErrorExclusive() {
        return isResultNull() ^ isErrorNull();
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
     * This method invokes {@link #setResult(Object)} with specified value and, if {@link #isResultNull()} method
     * returns {@code false}, invokes {@link #setError(ErrorObject)} method with {@code null}.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     * @see #setResult(Object)
     */
    public void setResultExclusively(final ResultType result) {
        setResult(result);
        if (!isResultNull()) {
            setError(null);
        }
    }

    /**
     * Indicates whether the current value of {@value #PROPERTY_NAME_RESULT} property is <i>semantically</i> {@code
     * null}.
     * <p>
     * The {@code isResultNull()} method of {@code ResponseObject} class returns the value of following expression.
     * <blockquote><pre>{@code getResult() == null}</pre></blockquote>
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_RESULT} property is <i>semantically</i>
     * {@code null}; {@code false} otherwise.
     */
    protected boolean isResultNull() {
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
     * This method invokes {@link #setError(ErrorObject)} with specified argument and, if {@link #isErrorNull()} method
     * returns {@code false}, invokes {@link #setResult(Object)} method with {@code null}.
     *
     * @param error new value for {@value #PROPERTY_NAME_ERROR} property.
     * @see #setError(ErrorObject)
     */
    public void setErrorExclusively(final ErrorType error) {
        setError(error);
        if (!isErrorNull()) {
            setResult(null);
        }
    }

    /**
     * Indicates whether the current value of {@value #PROPERTY_NAME_ERROR} property is <i>semantically</i> {@code
     * null}.
     * <p>
     * The {@code isErrorNull()} method of {@code ResponseObject} class returns the value of following expression.
     * <blockquote><pre>{@code getError() == null}</pre></blockquote>
     *
     * @return {@code true} if the current value of {@value #PROPERTY_NAME_ERROR} property is <i>semantically</i> {@code
     * null}; {@code false} otherwise.
     */
    protected boolean isErrorNull() {
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

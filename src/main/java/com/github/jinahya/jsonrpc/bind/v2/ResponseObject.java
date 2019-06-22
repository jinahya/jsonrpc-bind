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
         * Creates a new instance of specified type with specified data with it.
         *
         * @param objectClass object class to create.
         * @param data        value for {@value #PROPERTY_NAME_DATA} property.
         * @param <T>         object type paramter
         * @param <DataType>  data type parameter.
         * @return new instance of specified object type.
         */
        protected static <T extends ErrorObject<DataType>, DataType> T of(final Class<? extends T> objectClass,
                                                                          final DataType data) {
            try {
                final Constructor<? extends T> constructor = objectClass.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                final T instance = constructor.newInstance();
                instance.setData(data);
                return instance;
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        /**
         * Creates a new instance with specified data.
         *
         * @param data       the value for {@value #PROPERTY_NAME_DATA}.
         * @param <DataType> data type parameter.
         * @return a new instance.
         */
        @SuppressWarnings({"unchecked"})
        public static <DataType> ErrorObject<DataType> of(final DataType data) {
            return of((Class<ErrorObject<DataType>>) (Class<?>) ErrorObject.class, data);
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new instance.
         */
        public ErrorObject() {
            super();
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Returns a string representation ofError the object.
         *
         * @return a string representation ofError the object.
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
         * Returns the current value ofError {@value #PROPERTY_NAME_CODE} property.
         *
         * @return the current value ofError {@value #PROPERTY_NAME_CODE} property.
         */
        public long getCode() {
            return code;
        }

        /**
         * Replaces the current value ofError {@value #PROPERTY_NAME_CODE} property with given.
         *
         * @param code new value for {@value #PROPERTY_NAME_CODE} property.
         */
        public void setCode(final long code) {
            this.code = code;
        }

        // ----------------------------------------------------------------------------------------------------- message

        /**
         * Returns the current value ofError {@value #PROPERTY_NAME_MESSAGE} property.
         *
         * @return the current value ofError {@value #PROPERTY_NAME_MESSAGE} property.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Replaces the current value ofError {@value #PROPERTY_NAME_MESSAGE} property.
         *
         * @param message new value for {@value #PROPERTY_NAME_MESSAGE} property.
         */
        public void setMessage(final String message) {
            this.message = message;
        }

        // -------------------------------------------------------------------------------------------------------- data

        /**
         * Returns the current value ofError {@value #PROPERTY_NAME_DATA} property.
         *
         * @return the current value ofError {@value #PROPERTY_NAME_DATA} property.
         */
        public DataType getData() {
            return data;
        }

        /**
         * Replaces the current value ofError {@value #PROPERTY_NAME_DATA} property.
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
    protected static <T extends ResponseObject<U, V, W>, U, V extends ErrorObject<?>, W> T of(
            final Class<T> objectClass, final U result, final V error, final W id) {
        final T instance = JsonrpcObject.of(objectClass, id);
        instance.setResultExclusively(result);
        instance.setErrorExclusively(error);
        return instance;
    }

    @SuppressWarnings({"unchecked"})
    public static <U, V extends ErrorObject<?>, W> ResponseObject<U, V, W> of(
            final U result, final V error, final W id) {
        return of(ResponseObject.class, result, error, id);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * creates a new instance of specified object type with given result and id.
     *
     * @param objectClass object class to create.
     * @param result      the value for {@value #PROPERTY_NAME_RESULT} property.
     * @param id          the value for {@value #PROPERTY_NAME_ID} property.
     * @param <T>         object type parameter.
     * @param <U>         result type parameter.
     * @param <W>         id type parameter.
     * @return a new instance of specified object type.
     */
    protected static <T extends ResponseObject<U, ErrorObject<?>, W>, U, W> T ofResult(
            final Class<T> objectClass, final U result, final W id) {
        return of(objectClass, result, null, id);
    }

    /**
     * Creates a new instance with given result and id.
     *
     * @param result the value for {@value #PROPERTY_NAME_RESULT} property.
     * @param id     the value for {@value #PROPERTY_NAME_ID} property.
     * @param <U>    result type parameter
     * @param <W>    id type parameter
     * @return a new response object.
     */
    @SuppressWarnings({"unchecked"})
    public static <U, W> ResponseObject<U, ?, W> ofResult(final U result, final W id) {
        return ofResult(ResponseObject.class, result, id);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected static <T extends ResponseObject<Object, V, W>, V extends ErrorObject<?>, W> T ofError(
            final Class<? extends T> objectClass, final V error, final W id) {
        return of(objectClass, null, error, id);
    }

    @SuppressWarnings({"unchecked"})
    public static <V extends ErrorObject<?>, W> ResponseObject<?, V, W> ofError(final V error, final W id) {
        return ofError(ResponseObject.class, error, id);
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
     * Returns a string representation ofError the object.
     *
     * @return a string representation ofError the object.
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
     * Returns the current value ofError {@value #PROPERTY_NAME_RESULT} property.
     *
     * @return the current value ofError {@value #PROPERTY_NAME_RESULT} property.
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Replaces the current value ofError {@value #PROPERTY_NAME_RESULT} property with given.
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
     * Returns the current value ofError {@value #PROPERTY_NAME_ERROR} property.
     *
     * @return the current value ofError {@value #PROPERTY_NAME_ERROR} property.
     */
    public ErrorType getError() {
        return error;
    }

    /**
     * Replaces the current value ofError {@value #PROPERTY_NAME_ERROR} property with given.
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

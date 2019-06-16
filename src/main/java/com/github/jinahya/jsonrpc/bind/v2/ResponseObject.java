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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.util.function.Supplier;

/**
 * Represents response objects.
 *
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<IdType, ResultType, ErrorType extends ResponseObject.ErrorObject<?>>
        extends JsonrpcObject<IdType> {

    /**
     * The property name for {@code result}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code error}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    /**
     * Represents error objects.
     *
     * @param <DataType> data type parameter
     * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Objects (JSON RPC 2.0
     * Specification)</a>
     */
    public static class ErrorObject<DataType> {

        /**
         * The property name for {@code code}.
         */
        public static final String PROPERTY_NAME_CODE = "code";

        /**
         * The property name for {@code message}.
         */
        public static final String PROPERTY_NAME_MESSAGE = "message";

        /**
         * The property name for {@code data}.
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
         * The code value for <i>method not found</i> meaning the mehos does not exist nor available. The value is
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

        /**
         * A class for representing error objects of unknown data types.
         */
        public static class UnknownData extends ErrorObject<Object> {

        }

//        @Deprecated
//        public static class NoData extends ErrorObject<Void> {
//
//        }

        public static class DescriptiveData<RequestType extends RequestObject<?, ?>> extends ErrorObject<Throwable> {

            @Valid
            @Setter
            @Getter
            private RequestType request;
        }

        public static <T extends ErrorObject<DataType>, DataType> T of(final Supplier<? extends T> supplier,
                                                                       final long code, final String message,
                                                                       final DataType data) {
            final T instance = supplier.get();
            instance.setCode(code);
            instance.setMessage(message);
            instance.setData(data);
            return instance;
        }

        public static <T extends ErrorObject<DataType>, DataType> T of(final Class<? extends T> type, final long code,
                                                                       final String message, final DataType data) {
            return of(
                    () -> {
                        try {
                            final Constructor<? extends T> constructor = type.getDeclaredConstructor();
                            if (!constructor.isAccessible()) {
                                constructor.setAccessible(true);
                            }
                            return constructor.newInstance();
                        } catch (final ReflectiveOperationException roe) {
                            throw new RuntimeException(roe);
                        }
                    },
                    code,
                    message,
                    data);
        }

//        public <T extends ErrorObject<U>, U> T of(final Supplier<? extends T> supplier, final int code, final String message, final U data) {
//            final T supplied = supplier.get();
//            supplied.setCode(code);
//            supplied.setMessage(message);
//            supplied.setData(data);
//            return supplied;
//        }

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

        private long code;

        @NotNull
        private String message;

        @Valid
        private DataType data;
    }

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

    /**
     * Checks if the {@value #PROPERTY_NAME_RESULT} property and the {@value #PROPERTY_NAME_ERROR} property are
     * exclusive.
     *
     * @return {@code true} if {@value #PROPERTY_NAME_RESULT} and {@value #PROPERTY_NAME_ERROR} set exclusively, {@code
     * false} otherwise.
     */
    @JsonIgnore
    @JsonbTransient
    @AssertTrue(message = "either the result or the error should be set exclusively")
    private boolean isResultAndErrorExclusive() {
        return (getResult() != null) ^ (getError() != null);
    }

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
     */
    public void setResult(final ResultType result) {
        this.result = result;
    }

    /**
     * Sets the current value of {@value #PROPERTY_NAME_RESULT} property exclusively. This method, if given {@code
     * result} argument is not {@code null}, sets {@value #PROPERTY_NAME_ERROR} property with {@code null}.
     *
     * @param result new value for {@value #PROPERTY_NAME_RESULT} property.
     */
    public void setResultExclusively(final ResultType result) {
        setResult(result);
        if (getResult() != null) {
            setError(null);
        }
    }

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
     */
    public void setError(final ErrorType error) {
        this.error = error;
    }

    /**
     * Sets the current value of {@value #PROPERTY_NAME_ERROR} property exclusively. This method, if given {@code error}
     * argument is not {@code null}, sets {@value #PROPERTY_NAME_RESULT} property with  {@code null}.
     *
     * @param error new value for {@value #PROPERTY_NAME_ERROR} property.
     */
    public void setErrorExclusively(final ErrorType error) {
        setError(error);
        if (getError() != null) {
            setResult(null);
        }
    }

    @Valid
    private ResultType result;

    @Valid
    private ErrorType error;
}

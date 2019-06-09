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

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * Represents response objects.
 *
 * @param <T> result type parameter
 * @param <U> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<T, U extends ResponseObject.ErrorObject<?>> extends JsonrpcObject {

    public static final String PROPERTY_NAME_RESULT = "result";

    public static final String PROPERTY_NAME_ERROR = "error";

    /**
     * Represents error objects.
     *
     * @param <T> data type parameter
     * @see <a href="https://www.jsonrpc.org/specification#error_object">5.1 Error Objects (JSON RPC 2.0
     * Specification)</a>
     */
    public static class ErrorObject<T> {

        /**
         * The minimum value for codes reserved for pre-defined errors. The value is {@value
         * #MIN_CODE_FOR_PREDEFINED_ERRORS}.
         */
        public static final long MIN_CODE_FOR_PREDEFINED_ERRORS = -32768L;

        /**
         * The maximum value for codes reserved for pre-defined errors. The value is {@value
         * #MAX_CODE_FOR_PREDEFINED_ERRORS}.
         */
        public static final long MAX_CODE_FOR_PREDEFINED_ERRORS = -32000L;

        public static final long CODE_PARSE_ERROR = -32700L;

        public static final long CODE_INVALID_REQUEST = -32600L;

        public static final long CODE_METHOD_NOT_FOUND = -32601L;

        public static final long CODE_INVALID_PARAMS = -32602L;

        public static final long CODE_INTERNAL_ERROR = -32603L;

        /**
         * The minimum value for codes reserved for implementation-defined server errors. The value is {@value
         * #MIN_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}.
         */
        public static final long MIN_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS = -32099L;

        /**
         * The maximum value for codes reserved for implementation-defined server errors. The value is {@value
         * #MAX_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}.
         */
        public static final long MAX_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS = -32000L;

        /**
         * A class for representing error objects of unknown data types.
         */
        public static class UnknownData extends ErrorObject<Object> {

        }

        @Deprecated
        public static class NoData extends ErrorObject<Void> {

        }

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
         * current value of {@code code} attribute is between {@value #MIN_CODE_FOR_PREDEFINED_ERRORS} and {@value
         * #MAX_CODE_FOR_PREDEFINED_ERRORS} (both inclusive) or not.
         *
         * @return {@code true} if the current value of {@code code} attribute is for pre-defined errors.
         */
        @JsonIgnore
        @JsonbTransient
        public boolean isCodeForPredefinedErrors() {
            return code >= MIN_CODE_FOR_PREDEFINED_ERRORS && code <= MAX_CODE_FOR_PREDEFINED_ERRORS;
        }

        /**
         * Checks the current value of {@code code} attribute is for implementation-defined server errors. This method
         * checks whether the current value of {@code code} attribute is between {@value
         * #MIN_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS} and {@value #MAX_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS}
         * (both inclusive) or not.
         *
         * @return {@code true} if the current value of {@code code} attribute is for implementation-defined server
         * errors.
         */
        @JsonIgnore
        @JsonbTransient
        public boolean isCodeForImplementationDefinedServerErrors() {
            return code >= MIN_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS &&
                   code <= MAX_CODE_FOR_IMPLEMENTATION_DEFINED_SERVER_ERRORS;
        }

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

        private long code;

        @NotNull
        private String message;

        private T data;
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
     * Checks if the {@code result} attribute and the {@code error} attribute are exclusive.
     *
     * @return {@code true} if {@code result} and {@code error} set exclusively, {@code false} otherwise.
     */
    @JsonIgnore
    @JsonbTransient
    @AssertTrue(message = "result and error should be set exclusively")
    private boolean isResultAndErrorExclusive() {
        return (getResult() != null) ^ (getError() != null);
    }

    /**
     * Returns the current value of {@code result} attribute.
     *
     * @return the current value of {@code result} attribute
     */
    public T getResult() {
        return result;
    }

    /**
     * Replaces the current value of {@code result} attribute with given.
     *
     * @param result new value for {@code result} attribute
     */
    public void setResult(final T result) {
        this.result = result;
    }

    public void setResultExclusively(final T result) {
        setResult(result);
        if (getResult() != null) {
            setError(null);
        }
    }

    /**
     * Returns the current value of {@code error} attribute.
     *
     * @return the current value of {@code error} attribute
     */
    public U getError() {
        return error;
    }

    /**
     * Replaces the current value of {@code error} attribute with given.
     *
     * @param error new value for {@code error} attribute
     */
    public void setError(final U error) {
        this.error = error;
    }

    public void setErrorExclusively(final U error) {
        setError(error);
        if (getError() != null) {
            setResult(null);
        }
    }

    @Valid
    private T result;

    @Valid
    private U error;
}

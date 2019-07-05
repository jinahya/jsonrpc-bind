package com.github.jinahya.jsonrpc.bind.v1;

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

/**
 * A class for binding responses.
 *
 * @param <ResultType> {@value #PROPERTY_NAME_RESULT} type parameter
 * @param <ErrorType>  {@value #PROPERTY_NAME_ERROR} type parameter
 * @param <IdType>     {@value com.github.jinahya.jsonrpc.bind.v1.Identifiable#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Response<ResultType, ErrorType, IdType> extends Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.result}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * A property name for {@code $.error}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Response() {
        super();
    }

    // ---------------------------------------------------------------------------------------------------------- result

    /**
     * Returns the current value of {@value #PROPERTY_NAME_RESULT} property.
     *
     * @return the current value fo {@value #PROPERTY_NAME_RESULT} property.
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_RESULT} property with specified value.
     *
     * @param result new value for {@value #PROPERTY_NAME_ERROR} property.
     */
    public void setResult(final ResultType result) {
        this.result = result;
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
     */
    public void setError(final ErrorType error) {
        this.error = error;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@link #PROPERTY_NAME_RESULT} property.
     */
    private ResultType result;

    /**
     * An attribute for {@link #PROPERTY_NAME_ERROR} property.
     */
    private ErrorType error;
}

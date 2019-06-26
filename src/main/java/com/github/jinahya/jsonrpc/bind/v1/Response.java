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

import javax.validation.constraints.AssertFalse;

/**
 * A class for binding responses.
 *
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <IdType>     id type parameter
 */
public class Response<ResultType, ErrorType, IdType> extends Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.result}.
     */
    public static final String PROPERTY_NAME_RESULT = "result";

    /**
     * A property name for {@code $.error}.
     */
    public static final String PROPERTY_NAME_ERROR = "error";

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * {@inheritDoc} The {@code isIdSemanticallyNull()} method of {@code Response} class annotated with {@link
     * AssertFalse}.
     *
     * @return {@inheritDoc}
     */
    @AssertFalse
    @Override
    protected boolean isIdSemanticallyNull() {
        return super.isIdSemanticallyNull();
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
     * Attribute for {@link #PROPERTY_NAME_RESULT} property.
     */
    private ResultType result;

    /**
     * Attribute for {@link #PROPERTY_NAME_ERROR} property.
     */
    private ErrorType error;
}

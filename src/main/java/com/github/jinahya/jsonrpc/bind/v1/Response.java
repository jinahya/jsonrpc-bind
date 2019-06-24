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
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @AssertFalse
    @Override
    protected boolean isIdSemanticallyNull() {
        return super.isIdSemanticallyNull();
    }

    // ---------------------------------------------------------------------------------------------------------- result
    public ResultType getResult() {
        return result;
    }

    public void setResult(final ResultType result) {
        this.result = result;
    }

    // ----------------------------------------------------------------------------------------------------------- error
    public ErrorType getError() {
        return error;
    }

    public void setError(final ErrorType error) {
        this.error = error;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ResultType result;

    private ErrorType error;
}

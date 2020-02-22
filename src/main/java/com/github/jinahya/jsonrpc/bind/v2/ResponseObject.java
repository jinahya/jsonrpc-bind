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

/**
 * A class for binding response objects.
 *
 * @param <ResultType> {@value PROPERTY_NAME_RESULT} type parameter
 * @param <ErrorType>  {@value PROPERTY_NAME_ERROR} type parameter
 * @param <IdType>     {@value JsonrpcObject#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#response_object">Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public abstract class ResponseObject<ResultType, ErrorType extends ResponseErrorObject<?>, IdType>
        extends JsonrpcObject<IdType> {

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
               + ",error=" + error
               + "}";
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

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

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

import javax.validation.constraints.AssertTrue;

/**
 * An abstract class for request objects and response objects.
 *
 * @param <IdType> {@value #PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.jsonrpc}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    /**
     * The fixed value for {@value #PROPERTY_NAME_JSONRPC} property. The value is {@value}.
     */
    public static final String PROPERTY_VALUE_JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.id}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    protected JsonrpcObject() {
        super();
        jsonrpc = PROPERTY_VALUE_JSONRPC;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether the current value of {@value #PROPERTY_NAME_ID} property is, <i>semantically</i>, either {@code
     * string}, {@code number}, or {@code null}.
     * <p>
     * The {@code isIdEitherStringNumberOfNull()} method of {@code JsonrpcObject} class returns the value of following
     * expression.
     * <blockquote><pre>{@code
     * getId() == null || getId() instanceof java.lang.String || getId() instanceof java.lang.Number
     * }</pre></blockquote>
     *
     * @return {@code true} if the value of {@value #PROPERTY_NAME_ID} property is, <i>semantically</i>, either {@code
     * string}, {@code number}, or {@code null}; {@code false} otherwise.
     */
    protected abstract @AssertTrue boolean isIdEitherStringNumberOrNull();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc=" + jsonrpc
               + ",id=" + idType
               + "}";
    }

    // --------------------------------------------------------------------------------------------------------- jsonrpc

    /**
     * Returns the current value of {@value #PROPERTY_NAME_JSONRPC} property. The default value of the property is
     * {@value #PROPERTY_VALUE_JSONRPC}({@link #PROPERTY_VALUE_JSONRPC}).
     *
     * @return the current value of {@value #PROPERTY_NAME_JSONRPC} property.
     */
    public String getJsonrpc() {
        return jsonrpc;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    protected IdType getIdType() {
        return idType;
    }

    public abstract String getIdAsString();

    public abstract Long getIdAsLong();

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with specified value.
     *
     * @param idType new value for {@value #PROPERTY_NAME_ID} property.
     */
    protected void setIdType(final IdType idType) {
        this.idType = idType;
    }

    public abstract void setId(String id);

    public abstract void setId(Long id);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_JSONRPC} property. Default value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    private final String jsonrpc;

    /**
     * An attribute for {@value #PROPERTY_NAME_ID} property. Default value is {@code null}.
     */
    private IdType idType; // may be null with notifications
}

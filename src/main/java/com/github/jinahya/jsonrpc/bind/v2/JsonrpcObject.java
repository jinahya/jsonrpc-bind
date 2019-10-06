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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

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
    public JsonrpcObject() {
        super();
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
    protected @AssertTrue boolean isIdEitherStringNumberOrNull() {
        final IdType id = getId();
        return id == null || id instanceof String || id instanceof Number;
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
               + "jsonrpc=" + jsonrpc
               + ",id=" + id
               + "}";
    }

//    /**
//     * Indicates whether some other object is "equal to" this one.
//     *
//     * @param obj the reference object with which to compare.
//     * @return {@code true} if this object is the same as the {@code obj} argument; {@code false} otherwise.
//     */
//    @Override
//    public boolean equals(final Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (!(obj instanceof JsonrpcObject)) {
//            return false;
//        }
//        final JsonrpcObject<?> that = (JsonrpcObject<?>) obj;
//        return Objects.equals(getJsonrpc(), that.getJsonrpc()) && Objects.equals(getId(), that.getId());
//    }
//
//    /**
//     * Returns a hash code value for the object.
//     *
//     * @return a hash code value for this object.
//     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(getJsonrpc(), getId());
//    }

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

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_JSONRPC} property with specified value.
     *
     * @param jsonrpc new value for {@value #PROPERTY_NAME_JSONRPC} property.
     */
    public void setJsonrpc(final String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    public IdType getId() {
        return id;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with specified value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    public void setId(final IdType id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_JSONRPC} property. Default value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    @Pattern(regexp = PROPERTY_VALUE_JSONRPC)
    @NotNull
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;

    /**
     * An attribute for {@value #PROPERTY_NAME_ID} property. Default value is {@code null}.
     */
    private IdType id; // may be null with notifications
}

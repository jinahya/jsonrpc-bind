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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * An abstract class for request objects and response objects.
 *
 * @param <IdType> id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.jsonrpc}.
     */
    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    /**
     * The fixed value for {@value #PROPERTY_NAME_JSONRPC} property. The value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    public static final String PROPERTY_VALUE_JSONRPC = "2.0";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.id}.
     */
    public static final String PROPERTY_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified type whose properties are set with given values.
     *
     * @param clazz the class of object to create.
     * @param id    a value for {@value #PROPERTY_NAME_ID} property.
     * @param <T>   object type parameter
     * @param <U>   id type parameter.
     * @return a new instance.
     */
    static <T extends JsonrpcObject<? super U>, U> T of(final Class<? extends T> clazz, final U id) {
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            final T instance = constructor.newInstance();
            instance.setId(id);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public JsonrpcObject() {
        super();
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
        if (!(obj instanceof JsonrpcObject)) {
            return false;
        }
        final JsonrpcObject<?> that = (JsonrpcObject<?>) obj;
        return Objects.equals(getJsonrpc(), that.getJsonrpc()) && Objects.equals(getId(), that.getId());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getJsonrpc(), getId());
    }

    // --------------------------------------------------------------------------------------------------------- jsonrpc

    /**
     * Returns the current value of {@value #PROPERTY_NAME_JSONRPC} property. The default value of the property is
     * {@value #PROPERTY_VALUE_JSONRPC}.
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

    /**
     * Copies this object's current value of {@value #PROPERTY_NAME_ID} property to specified object.
     *
     * @param object the object whose {@value #PROPERTY_NAME_ID} property is copied from this object.
     * @see #copyIdFrom(JsonrpcObject)
     */
    public void copyIdTo(final JsonrpcObject<? super IdType> object) {
        object.setId(getId());
    }

    /**
     * Copies specified object's current value of {@value #PROPERTY_NAME_ID} property to this object.
     *
     * @param object the object whose current value of {@value #PROPERTY_NAME_ID} property is copied to this object
     * @see #copyIdTo(JsonrpcObject)
     */
    public void copyIdFrom(final JsonrpcObject<? extends IdType> object) {
        object.copyIdTo(this);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_JSONRPC} property. Default value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    @Pattern(regexp = PROPERTY_VALUE_JSONRPC)
    @NotNull
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;

    /**
     * An attribute for {@value #PROPERTY_NAME_ID} property.
     */
    @Valid
    private IdType id;
}

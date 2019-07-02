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
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * A class for binding request objects.
 *
 * @param <ParamsType> {@value #PROPERTY_NAME_PARAMS} type parameter
 * @param <IdType>     {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#request_object">Request Object (JSON-RPC 2.0 Specification)</a>
 */
public class RequestObject<ParamsType, IdType> extends JsonrpcObject<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.method}. The value is {@value #PROPERTY_NAME_METHOD}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params}. The value is {@value #PROPERTY_NAME_PARAMS}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified type whose properties are set with specified values.
     *
     * @param clazz   the class of the object to create
     * @param jsonrpc a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_VALUE_JSONRPC}
     *                property.
     * @param method  a value for {@value #PROPERTY_NAME_METHOD} property.
     * @param params  a value for {@value #PROPERTY_NAME_PARAMS} property.
     * @param id      a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} property.
     * @param <T>     object type parameter
     * @param <U>     params type parameter
     * @param <V>     id type parameter
     * @return a new instance of specified object class.
     */
    public static <T extends RequestObject<? super U, ? super V>, U, V> T of(
            final Class<? extends T> clazz, final String jsonrpc, final String method, final U params, final V id) {
        final T instance = of(clazz, jsonrpc, id);
        instance.setMethod(method);
        instance.setParams(params);
        return instance;
    }

//    /**
//     * Creates a new instance whose properties are set with specified values.
//     *
//     * @param jsonrpc a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_VALUE_JSONRPC}
//     *                property.
//     * @param method  a value for {@value #PROPERTY_NAME_METHOD} property.
//     * @param params  a value for {@value #PROPERTY_NAME_PARAMS} property.
//     * @param id      a value for {@value com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject#PROPERTY_NAME_ID} property.
//     * @param <U>     params type parameter
//     * @param <V>     id type parameter
//     * @return a new instance.
//     */
//    @SuppressWarnings({"unchecked"})
//    public static <U, V> RequestObject<? super U, ? super V> of(final String jsonrpc, final String method,
//                                                                final U params, final V id) {
//        return of(RequestObject.class, jsonrpc, method, params, id);
//    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public RequestObject() {
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
               + "method=" + method
               + ",params=" + params
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RequestObject)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final RequestObject<?, ?> that = (RequestObject<?, ?>) obj;
        return Objects.equals(getMethod(), that.getMethod()) && Objects.equals(getParams(), that.getParams());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMethod(), getParams());
    }

    // ---------------------------------------------------------------------------------------------------------- method

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property. The default value of the property is {@code
     * null}.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace the current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    // ---------------------------------------------------------------------------------------------------------- params

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property. The default value of the property is {@code
     * null}.
     *
     * @return the current value of {@value #PROPERTY_NAME_PARAMS} property.
     */
    public ParamsType getParams() {
        return params;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    public void setParams(final ParamsType params) {
        this.params = params;
    }

    /**
     * Indicates the value of {@value #PROPERTY_NAME_PARAMS} property is a structured value.
     *
     * @return {@code true} if {@value #PROPERTY_NAME_PARAMS} property is a structured value; {@code false} otherwise.
     */
    @AssertTrue
    protected boolean isParamsStructured() {
        final ParamsType params = getParams();
        if (params == null) {
            return true;
        }
        if (params.getClass().isPrimitive()) {
            return false;
        }
        if (params.getClass().isAnnotation()) {
            return false;
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_METHOD} property.
     */
    @NotBlank
    private String method;

    /**
     * An attribute for {@value #PROPERTY_NAME_PARAMS} property.
     */
    @Valid
    private ParamsType params;
}

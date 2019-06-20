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
import javax.validation.constraints.NotBlank;

/**
 * Represents calculatorRequest objects.
 *
 * @param <ParamsType> {@value #PROPERTY_NAME_PARAMS} type parameter
 * @param <IdType>     {@value #PROPERTY_NAME_ID} type parameter.
 * @see <a href="https://www.jsonrpc.org/specification#request_object">4. Request Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class RequestObject<ParamsType, IdType> extends JsonrpcObject<IdType> {

    /**
     * The property name for {@code method}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code params}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    /**
     * An abstract class for defining builders of specific subclass of {@link RequestObject}.
     *
     * @param <T> builder type parameter.
     * @param <U> request object type parameter.
     * @param <V> {@value #PROPERTY_NAME_PARAMS} type parameter.
     * @param <W> {@value #PROPERTY_NAME_ID} type parameter.
     */
    protected abstract static class AbstractBuilder<
            T extends AbstractBuilder<T, U, V, W>,
            U extends RequestObject<V, W>,
            V,
            W>
            extends JsonrpcObjectBuilder<T, U, W> {

        /**
         * Creates a new instance for specified request object class.
         *
         * @param objectClass the request object class.
         */
        public AbstractBuilder(final Class<? extends U> objectClass) {
            super(objectClass);
        }

        /**
         * Sets {@value #PROPERTY_NAME_PARAMS} property with specified value and returns this builder instance.
         *
         * @param params the value for {@value #PROPERTY_NAME_PARAMS} property.
         * @return this builder instance.
         */
        @SuppressWarnings({"unchecked"})
        public T params(final V params) {
            this.params = params;
            return (T) this;
        }

        /**
         * {@inheritDoc}
         *
         * @param id the value for {@value #PROPERTY_NAME_ID} property.
         * @return {@inheritDoc}
         */
        @Override
        public T id(final W id) {
            return super.id(id);
        }

        /**
         * Builds this builder and returns the built value.
         *
         * @return the built value.
         */
        @Override
        public U build() {
            final U instance = super.build();
            instance.setParams(params);
            return instance;
        }

        private V params;
    }

    /**
     * A class for building an instance of {@link RequestObject}.
     *
     * @param <T> {@value #PROPERTY_NAME_PARAMS} type parameter.
     * @param <U> {@value #PROPERTY_NAME_ID} type parameter.
     */
    public static class Builder<T, U> extends AbstractBuilder<Builder<T, U>, RequestObject<T, U>, T, U> {

        /**
         * Creates a new instance.
         */
        @SuppressWarnings({"unchecked"})
        public Builder() {
            super((Class<? extends RequestObject<T, U>>) (Class<?>) RequestObject.class);
        }

        /**
         * {@inheritDoc}
         *
         * @param params the value for {@value #PROPERTY_NAME_PARAMS} property.
         * @return {@inheritDoc}
         */
        @Override
        public Builder<T, U> params(final T params) {
            return super.params(params);
        }

        /**
         * {@inheritDoc}
         *
         * @param id the value for {@value #PROPERTY_NAME_ID} property.
         * @return {@inheritDoc}
         */
        @Override
        public Builder<T, U> id(final U id) {
            return super.id(id);
        }

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public RequestObject<T, U> build() {
            return super.build();
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "method=" + method
               + ",params=" + params
               + "}";
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace the current value of {@value #PROPERTY_NAME_METHOD} property with given.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_PARAMS} property.
     */
    public ParamsType getParams() {
        return params;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with given.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    public void setParams(final ParamsType params) {
        this.params = params;
    }

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

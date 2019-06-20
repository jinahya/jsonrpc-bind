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

    static class RequestObjectBuilder<
            BuilderType extends RequestObjectBuilder<BuilderType, ObjectType, ParamsType, IdType>,
            ObjectType extends RequestObject<ParamsType, IdType>,
            ParamsType,
            IdType>
            extends JsonrpcObjectBuilder<BuilderType, ObjectType, IdType> {

        RequestObjectBuilder(final Class<? extends ObjectType> objectClass) {
            super(objectClass);
        }

        @SuppressWarnings({"unchecked"})
        public BuilderType params(final ParamsType params) {
            this.params = params;
            return (BuilderType) this;
        }

        @Override
        public ObjectType build() {
            final ObjectType instance = super.build();
            instance.setParams(params);
            return instance;
        }

        private ParamsType params;
    }

    public static class Builder<ParamsType, IdType>
            extends RequestObjectBuilder<Builder<ParamsType, IdType>, RequestObject<ParamsType, IdType>,
            ParamsType,
            IdType> {

        @SuppressWarnings({"unchecked"})
        public Builder() {
            super((Class<? extends RequestObject<ParamsType, IdType>>) (Class<?>) RequestObject.class);
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

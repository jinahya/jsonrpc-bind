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

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * An abstract class for request objects and response objects.
 *
 * @param <IdType> {@value #PROPERTY_NAME_ID} type parameter
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class JsonrpcObject<IdType> {

    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    public static final String PROPERTY_NAME_ID = "id";

    /**
     * The fixed value for {@value #PROPERTY_NAME_JSONRPC} attribute. The value is {@value #PROPERTY_VALUE_JSONRPC}.
     */
    public static final String PROPERTY_VALUE_JSONRPC = "2.0";

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
     * Returns the current value of {@value #PROPERTY_NAME_JSONRPC} attribute.
     *
     * @return the current value of {@value #PROPERTY_NAME_JSONRPC} attribute.
     */
    public String getJsonrpc() {
        return jsonrpc;
    }

    /**
     * Replaces the current value of {@code jsonrpc} attribute with given.
     *
     * @param jsonrpc new value for {@code jsonrpc} attribute.
     */
    public void setJsonrpc(final String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    /**
     * Returns the current value of {@code id} attribute.
     *
     * @return the current value of {@code id} attribute.
     */
    public Object getId() {
        return id;
    }

    /**
     * Replaces the current value of {@code id} attribute with given.
     *
     * @param id new value for {@code id} attribute.
     */
    public void setId(final IdType id) {
        this.id = id;
    }

    @Pattern(regexp = PROPERTY_VALUE_JSONRPC)
    @NotNull
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;

    @Valid
    private IdType id;
}

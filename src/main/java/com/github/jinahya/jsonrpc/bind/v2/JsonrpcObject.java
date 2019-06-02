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

/**
 * An abstract class for request objects and response objects.
 */
public abstract class JsonrpcObject {

    public static final String NAME_JSONRPC = "jsonrpc";

    public static final String NAME_ID = "id";

    /**
     * The fixed value for {@code jsonrpc} attribute. The value is {@value #JSONRPC}.
     */
    public static final String JSONRPC = "2.0";

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
               "jsonrpc=" + jsonrpc +
               ",id=" + id +
               "}";
    }

    @AssertTrue
    private boolean isIdAnInstanceOfStringOrNumber() {
        return id == null || id instanceof String || id instanceof Number;
    }

    /**
     * Returns the current value of {@code jsonrpc} attribute which is always {@value #JSONRPC}.
     *
     * @return the current value of {@code jsonrpc} attribute.
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
    public void setId(final Object id) {
        this.id = id;
        if (this.id != null && this.id instanceof Number && !(this.id instanceof Long)) {
            this.id = ((Number) this.id).longValue();
        }
    }

    @Pattern(regexp = JSONRPC)
    @NotNull
    private String jsonrpc = JSONRPC;

    private Object id;
}

package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import jakarta.validation.constraints.NotBlank;

/**
 * An abstract class implements {@link JsonrpcRequestMessage} interface.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractJsonrpcRequestMessage
        extends AbstractJsonrpcMessage
        implements JsonrpcRequestMessage {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + PROPERTY_NAME_METHOD + "=" + method
               + "}";
    }

    // ---------------------------------------------------------------------------------------------------------- method

    /**
     * Returns current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    @Override
    public @NotBlank String getMethod() {
        return method;
    }

    /**
     * Replaces current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    @Override
    public void setMethod(final String method) {
        this.method = method;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String method;
}

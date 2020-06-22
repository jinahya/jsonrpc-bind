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

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * An interface JSO N-RPC 2.0 request messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.jsonrpc.org/specification#request_object">Request Object (JSON-RPC 2.0)</a>
 */
@SuppressWarnings({"java:S1214"})
public interface JsonrpcRequestMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.method} part. The value is {@value}.
     */
    String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params} part. The value is {@value}.
     */
    String PROPERTY_NAME_PARAMS = "params";

    /**
     * Returns current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    @NotBlank
    String getMethod();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    void setMethod(String method);

    /**
     * Indicates this message has a value for {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return {@code} true if this message has a value for {@value #PROPERTY_NAME_PARAMS} property; {@code false}
     * otherwise.
     */
    boolean hasParams();

    /**
     * Returns current value of {@value #PROPERTY_NAME_PARAMS} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    <T> List<T> getParamsAsArray(final Class<T> elementClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsArray(List<?> params);

    /**
     * Returns current value of {@value #PROPERTY_NAME_PARAMS} property as an instance of specified object type.
     *
     * @param objectClass the object type.
     * @param <T>         object type parameter
     * @return an instance of {@code objectClass}.
     */
    <T> T getParamsAsObject(final Class<T> objectClass);

    /**
     * Replaces current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsObject(Object params);
}

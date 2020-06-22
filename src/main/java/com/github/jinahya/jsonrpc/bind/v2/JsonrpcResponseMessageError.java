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

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * An interface for {@code $.error} part of {@link JsonrpcResponseMessage}.
 *
 * @author Jin Kwon &lt;onacit_at_wemakeprice.com&gt;
 */
public interface JsonrpcResponseMessageError extends JsonrpcObject {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.error.code} part. The value is {@value}.
     */
    String PROPERTY_NAME_CODE = "code";

    /**
     * The property name for {@code $.error.message} part. The value is {@value}.
     */
    String PROPERTY_NAME_MESSAGE = "message";

    /**
     * The property name for {@code $.error.data} part. The value is {@value}.
     */
    String PROPERTY_NAME_DATA = "data";

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns current value of {@value #PROPERTY_NAME_CODE} property.
     *
     * @return current value of {@value #PROPERTY_NAME_CODE} property
     */
    int getCode();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_CODE} property with specified value.
     *
     * @param code new value for {@value #PROPERTY_NAME_CODE} property.
     */
    void setCode(int code);

    // --------------------------------------------------------------------------------------------------------- message
    @NotNull
    String getMessage();

    void setMessage(String message);

    // ------------------------------------------------------------------------------------------------------------ data
    boolean hasData();

    default boolean isDataContextuallyValid() {
        return true;
    }

    /**
     * Reads current value of {@value #PROPERTY_NAME_DATA} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    <T> List<T> getDataAsArray(Class<T> elementClass);

    void setDataAsArray(List<?> data);

    <T> T getDataAsObject(Class<T> objectClass);

    void setDataAsObject(Object data);
}

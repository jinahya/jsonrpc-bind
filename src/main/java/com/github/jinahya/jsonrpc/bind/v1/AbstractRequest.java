package com.github.jinahya.jsonrpc.bind.v1;

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

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * An abstract class for requests and notification.
 *
 * @param <ParamType> params element type parameter
 * @param <IdType>    id type parameter
 */
public abstract class AbstractRequest<ParamType, IdType> extends Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.params}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    // -----------------------------------------------------------------------------------------------------------------
    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public List<ParamType> getParams() {
        return params;
    }

    public void setParams(final List<ParamType> params) {
        this.params = params;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String method;

    private List<ParamType> params;
}

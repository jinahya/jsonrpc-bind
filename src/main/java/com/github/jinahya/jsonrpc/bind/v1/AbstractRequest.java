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
import java.util.Objects;

/**
 * An abstract class for requests and notifications.
 *
 * @param <ParamType> params element type parameter
 * @param <IdType>    id type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractRequest<ParamType, IdType> extends Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.method}. The value is {@value #PROPERTY_NAME_METHOD}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * A property name for {@code $.params}. The value is {@value #PROPERTY_NAME_PARAMS}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public AbstractRequest() {
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
        if (!(obj instanceof AbstractRequest)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final AbstractRequest<?, ?> that = (AbstractRequest<?, ?>) obj;
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_PARAMS} property.
     */
    public List<ParamType> getParams() {
        return params;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    public void setParams(final List<ParamType> params) {
        this.params = params;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String method;

    private List<ParamType> params;
}

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

import javax.validation.constraints.AssertTrue;

/**
 * A class for binding notifications which each {@value #PROPERTY_NAME_ID} property should be <i>semantically</i> {@code
 * null}.
 *
 * @param <ParamType> {@value com.github.jinahya.jsonrpc.bind.v1.AbstractRequest#PROPERTY_NAME_PARAMS} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Notification<ParamType> extends AbstractRequest<ParamType, Void> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Notification() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * {@inheritDoc} The {@link #setId(Void)} method of {@code Notification} class does nothing.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} parameter.
     */
    @Override
    public void setId(final Void id) {
        // empty; does nothing.
    }

    /**
     * {@inheritDoc} The {@code isIdNull()} method of {@code Notification} class annotated with {@link AssertTrue}.
     *
     * @return {@inheritDoc}
     */
    @Override
    protected @AssertTrue boolean isIdNull() {
        return super.isIdNull();
    }
}

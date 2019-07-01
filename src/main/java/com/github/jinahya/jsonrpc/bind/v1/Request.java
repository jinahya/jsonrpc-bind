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

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;

/**
 * A class for binding requests.
 *
 * @param <ParamType> {@value com.github.jinahya.jsonrpc.bind.v1.AbstractRequest#PROPERTY_NAME_PARAMS} element type
 *                    parameter
 * @param <IdType>    {@value com.github.jinahya.jsonrpc.bind.v1.Identifiable#PROPERTY_NAME_ID} type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class Request<ParamType, IdType> extends AbstractRequest<ParamType, IdType> {

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * {@inheritDoc}
     * <p>
     * The {@code getId()} method of {@code Request} class is annotated with {@link NotNull}.
     *
     * @return {@inheritDoc}
     */
    @Override
    public @NotNull IdType getId() {
        return super.getId();
    }

    /**
     * {@inheritDoc} The {@code isIdSemanticallyNull()} method of {@code Request} class annotated with {@link
     * AssertFalse}.
     *
     * @return {@inheritDoc}
     */
    @Override
    protected @AssertFalse boolean isIdSemanticallyNull() {
        return super.isIdSemanticallyNull();
    }
}

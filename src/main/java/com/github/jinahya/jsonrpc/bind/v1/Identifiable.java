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

/**
 * An abstract class for binding identifiable objects.
 *
 * @param <IdType> id type parameter.
 */
public abstract class Identifiable<IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A property name for {@code $.id}.
     */
    public static final String PROPERTY_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public IdType getId() {
        return id;
    }

    public void setId(final IdType id) {
        this.id = id;
    }

    /**
     * Indicates whether the current value of {@value PROPERTY_NAME_ID} property is <i>semantically</i> {@code null}.
     *
     * @return {@code true} if the current value of {@value PROPERTY_NAME_ID} property is <i>semantically</i> {@code
     * null}; {@code false} otherwise.
     */
    protected boolean isIdSemanticallyNull() {
        return getId() == null;
    }

    public <T extends Identifiable<? super IdType>> void copyIdTo(final T object) {
        object.setId(getId());
    }

    public <T extends Identifiable<? extends IdType>> void copyIdFrom(final T object) {
        object.copyIdTo(this);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private IdType id;
}

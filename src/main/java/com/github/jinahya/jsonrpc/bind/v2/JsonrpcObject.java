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

import jakarta.validation.constraints.AssertTrue;

import java.beans.Transient;

/**
 * A base interface for JSON-RPC 2.0 objects.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface JsonrpcObject {

    /**
     * Indicates this object is contextually valid.
     *
     * @return {@code true} if this object is contextually valid; {@code false} otherwise.
     * @apiNote This method supposed to be overridden when any implementation specified features required to be added.
     * @implSpec The default implementation returns {@code true}.
     */
    @Transient
    @AssertTrue
    default boolean isContextuallyValid() {
        return true;
    }

//    /**
//     * Returns a JSON representation of the object.
//     *
//     * @return a JSON representation of the object.
//     */
//    String toJson();
}

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

import com.github.jinahya.jsonrpc.bind.GsonUtils;
import com.github.jinahya.jsonrpc.bind.JacksonUtils;
import com.github.jinahya.jsonrpc.bind.JsonbUtils;
import com.github.jinahya.jsonrpc.bind.MoshiUtils;

import java.io.IOException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

abstract class IdentifiableTest<_ObjectType extends Identifiable<IdType>, IdType> {

    IdentifiableTest(final Class<? extends _ObjectType> objectClass, final Class<? extends IdType> idClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
        this.idClass = requireNonNull(idClass, "idClass is null");
    }

//    protected void withResource(final String name, final BiConsumer<? super _ObjectType, ? super String> consumer)
//            throws IOException {
//        JsonbUtils.withResource(name, objectClass, consumer);
//        JacksonUtils.withResource(name, objectClass, consumer);
//        GsonUtils.withResource(name, objectClass, consumer);
//        MoshiUtils.withResource(name, objectClass, consumer);
//    }

    protected void withResource(final String name, final Consumer<? super _ObjectType> consumer) throws IOException {
        consumer.accept(JsonbUtils.withResource(name, objectClass));
        consumer.accept(JacksonUtils.readValue(name, objectClass));
        consumer.accept(GsonUtils.withResource(name, objectClass));
        consumer.accept(MoshiUtils.withResource(name, objectClass));
    }

    protected final Class<? extends _ObjectType> objectClass;

    protected final Class<? extends IdType> idClass;
}

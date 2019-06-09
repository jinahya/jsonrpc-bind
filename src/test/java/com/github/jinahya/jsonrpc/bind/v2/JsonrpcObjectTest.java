package com.github.jinahya.jsonrpc.bind.v2;

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

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link JsonrpcObject}.
 *
 * @param <ObjectType> subclass type parameter
 * @param <IdType>     id type parameter
 */
abstract class JsonrpcObjectTest<ObjectType extends JsonrpcObject<IdType>, IdType> {

    JsonrpcObjectTest(final Class<? extends ObjectType> objectClass, final Class<? extends IdType> idClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
        this.idClass = requireNonNull(idClass, "idClass is null");
    }

    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        consumer.accept(JsonbUtils.fromResource(name, objectClass));
        consumer.accept(JacksonUtils.readResource(name, objectClass));
    }

    protected <U> void acceptValueFromResource(final String name, final Supplier<? extends U> supplier,
                                               final BiConsumer<? super ObjectType, ? super U> consumer)
            throws IOException {
        acceptValueFromResource(name, v -> {
            consumer.accept(v, supplier.get());
        });
    }

    protected final Class<? extends ObjectType> objectClass;

    protected final Class<? extends IdType> idClass;
}

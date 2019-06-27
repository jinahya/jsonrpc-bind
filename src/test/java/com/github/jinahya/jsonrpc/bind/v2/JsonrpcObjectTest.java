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

import com.github.jinahya.jsonrpc.bind.GsonTests;
import com.github.jinahya.jsonrpc.bind.JacksonTests;
import com.github.jinahya.jsonrpc.bind.JsonbTests;
import com.github.jinahya.jsonrpc.bind.MoshiTests;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.github.jinahya.jsonrpc.bind.v2.Reflections.newInstance;
import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link JsonrpcObject}.
 *
 * @param <ObjectType> subclass type parameter
 * @param <IdType>     id type parameter
 */
abstract class JsonrpcObjectTest<ObjectType extends JsonrpcObject<IdType>, IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     *
     * @param objectClass a class of target object to test.
     * @param idClass     a class of {@value JsonrpcObject#PROPERTY_NAME_ID} property of {@code objectClass}.
     */
    JsonrpcObjectTest(final Class<ObjectType> objectClass, final Class<IdType> idClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
        this.idClass = requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Reads a value of specified type from the resource of specified name and accepts to specified consumer.
     *
     * @param name     the resource name
     * @param consumer the consumer to be accepted with read value.
     * @throws IOException if an I/O error occurs.
     */
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        consumer.accept(JsonbTests.fromResource(name, objectClass));
        consumer.accept(JacksonTests.readValueFromResource(name, objectClass));
        consumer.accept(GsonTests.fromResource(name, objectClass));
        consumer.accept(MoshiTests.fromResource(name, objectClass));
    }

    /**
     * Reads a value of specified type from the resource named as given and accepts to specfiied consumer along with an
     * argument supplied by specified supplier.
     *
     * @param name     the name of the resource to read.
     * @param consumer the consumer.
     * @param supplier the supplier for the second argument.
     * @param <U>      second argument type parameter.
     * @throws IOException if an I/O error occurs.
     */
    protected <U> void acceptValueFromResource(final String name,
                                               final BiConsumer<? super ObjectType, ? super U> consumer,
                                               final Supplier<? extends U> supplier)
            throws IOException {
        acceptValueFromResource(name, v -> {
            consumer.accept(v, supplier.get());
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of {@link #objectClass}.
     *
     * @return a new instance of {@link #objectClass}.
     */
    protected ObjectType objectInstance() {
        return newInstance(objectClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target object class to test.
     */
    protected final Class<ObjectType> objectClass;

    /**
     * The class of {@value JsonrpcObject#PROPERTY_NAME_ID} property of {@link #objectClass}.
     */
    protected final Class<IdType> idClass;
}

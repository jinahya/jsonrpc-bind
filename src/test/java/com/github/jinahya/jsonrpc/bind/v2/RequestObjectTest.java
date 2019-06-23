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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Types;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.function.Consumer;

import static com.github.jinahya.jsonrpc.bind.GsonTests.GSON;
import static com.github.jinahya.jsonrpc.bind.JacksonTests.OBJECT_MAPPER;
import static com.github.jinahya.jsonrpc.bind.JsonbTests.JSONB;
import static com.github.jinahya.jsonrpc.bind.MoshiTests.MOSHI;
import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses ofError {@link RequestObject}.
 *
 * @param <ObjectType> calculatorRequest object type parameter
 * @param <ParamsType> calculatorRequest object params type parameter
 */
@Slf4j
public abstract class RequestObjectTest<ObjectType extends RequestObject<ParamsType, IdType>, ParamsType, IdType>
        extends JsonrpcObjectTest<ObjectType, IdType> {

    // -----------------------------------------------------------------------------------------------------------------
    public RequestObjectTest(final Class<ObjectType> requestClass, final Class<ParamsType> paramsClass,
                             final Class<IdType> idClass) {
        super(requestClass, idClass);
        this.paramsClass = requireNonNull(paramsClass, "paramsClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    @Override
    protected void acceptValueFromResource(String name, Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            {
                final RequestObject<ParamsType, IdType> of = RequestObject.of(v.getParams(), v.getId());
                log.debug("of: {}", of);
                log.debug("of.jsonb: {}", JSONB.toJson(of));
                try {
                    log.debug("of.jackson: {}", OBJECT_MAPPER.writeValueAsString(of));
                } catch (final JsonProcessingException jpe) {
                    throw new RuntimeException(jpe);
                }
                log.debug("of.gson: {}", GSON.toJson(of));
                {
                    final Type type = Types.newParameterizedType(RequestObject.class, paramsClass, idClass);
                    final JsonAdapter<RequestObject<ParamsType, IdType>> adapter
                            = MOSHI.adapter(type);
                    log.debug("of.moshi: {}", adapter.toJson(of));
                }
            }
            {
                final ObjectType of = RequestObject.of(objectClass, v.getParams(), v.getId());
                log.debug("of: {}", of);
                log.debug("of.jsonb: {}", JSONB.toJson(of));
                try {
                    log.debug("of.jackson: {}", OBJECT_MAPPER.writeValueAsString(of));
                } catch (final JsonProcessingException jpe) {
                    throw new RuntimeException(jpe);
                }
                log.debug("of.gson: {}", GSON.toJson(of));
                log.debug("of.moshi: {}", MOSHI.adapter(objectClass).toJson(of));
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ParamsType> paramsClass;
}

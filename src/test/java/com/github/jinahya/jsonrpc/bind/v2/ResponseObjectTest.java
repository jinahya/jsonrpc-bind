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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.function.Consumer;

import static com.github.jinahya.jsonrpc.bind.GsonUtils.GSON;
import static com.github.jinahya.jsonrpc.bind.JacksonUtils.OBJECT_MAPPER;
import static com.github.jinahya.jsonrpc.bind.JsonbUtils.JSONB;
import static com.github.jinahya.jsonrpc.bind.MoshiUtils.MOSHI;
import static com.github.jinahya.jsonrpc.bind.v2.ResponseObject.of;
import static com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ofError;
import static com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ofResult;
import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses ofError {@link ResponseObject}.
 *
 * @param <ObjectType> response object type parameter
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <IdType>     id type parameter
 */
@Slf4j
public abstract class ResponseObjectTest<
        ObjectType extends ResponseObject<ResultType, ErrorType, IdType>, ResultType,
        ErrorType extends ResponseObject.ErrorObject<?>, IdType>
        extends JsonrpcObjectTest<ObjectType, IdType> {

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Creates a new instance for specified response object class and its type parameter classes.
     *
     * @param responseClass the response object class to test.
     * @param resultClass   a class for {@code ResultType} type parameter.
     * @param errorClass    a class for {@code ErrorType} type parameter.
     * @param idClass       a class for {@code IdType} type parameter.
     */
    public ResponseObjectTest(final Class<ObjectType> responseClass, final Class<ResultType> resultClass,
                              final Class<ErrorType> errorClass, final Class<IdType> idClass) {
        super(responseClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void acceptValueFromResource(final String name, Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            v.setResultExclusively(v.getResult());
            v.setErrorExclusively(v.getError());
            {
                final ObjectType of = of(objectClass, v.getResult(), v.getError(), v.getId());
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
            {
                final ResponseObject<ResultType, ErrorType, IdType> of
                        = of(objectClass, v.getResult(), v.getError(), v.getId());
                log.debug("of: {}", of);
                log.debug("of.jsonb: {}", JSONB.toJson(of));
                try {
                    log.debug("of.jackson: {}", OBJECT_MAPPER.writeValueAsString(of));
                } catch (final JsonProcessingException jpe) {
                    throw new RuntimeException(jpe);
                }
                log.debug("of.gson: {}", GSON.toJson(of));
                @SuppressWarnings({"unchecked"})
                final JsonAdapter<ResponseObject<ResultType, ErrorType, IdType>> adapter =
                        MOSHI.adapter((Class<ResponseObject<ResultType, ErrorType, IdType>>) objectClass);
                log.debug("of.moshi: {}", adapter.toJson(of));
            }
            {
                final ResponseObject<ResultType, ?, IdType> of = ofResult(v.getResult(), v.getId());
                log.debug("ofResult: {}", of);
                log.debug("ofResult jsonb: {}", JSONB.toJson(of));
                try {
                    log.debug("ofResult jackson: {}", OBJECT_MAPPER.writeValueAsString(of));
                } catch (final JsonProcessingException jpe) {
                    throw new RuntimeException(jpe);
                }
                log.debug("ofResult gson: {}", GSON.toJson(of));
                @SuppressWarnings({"unchecked"}) final JsonAdapter<ResponseObject<ResultType, ?, IdType>> adapter =
                        MOSHI.adapter((Class<ResponseObject<ResultType, ?, IdType>>) objectClass);
                log.debug("ofResult moshi: {}", adapter.toJson(of));
            }
            {
                final ResponseObject<?, ResponseObject.ErrorObject<?>, IdType> of = ofError(v.getError(), v.getId());
                log.debug("ofError: {}", of);
                log.debug("ofError.jsonb: {}", JSONB.toJson(of));
                try {
                    log.debug("ofError.jackson: {}", OBJECT_MAPPER.writeValueAsString(of));
                } catch (final JsonProcessingException jpe) {
                    throw new RuntimeException(jpe);
                }
                log.debug("ofError.gson: {}", GSON.toJson(of));
                @SuppressWarnings({"unchecked"})
                final JsonAdapter<ResponseObject<?, ResponseObject.ErrorObject<?>, IdType>> adapter =
                        MOSHI.adapter((Class<ResponseObject<?, ResponseObject.ErrorObject<?>, IdType>>) objectClass);
                log.debug("ofError.moshi: {}", adapter.toJson(of));
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ResultType> resultClass;

    protected final Class<ErrorType> errorClass;
}

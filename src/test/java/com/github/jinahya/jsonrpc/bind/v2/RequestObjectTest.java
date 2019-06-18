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

import lombok.extern.slf4j.Slf4j;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link RequestObject}.
 *
 * @param <ObjectType> calculatorRequest object type parameter
 * @param <ParamsType> calculatorRequest object params type parameter
 */
@Slf4j
public abstract class RequestObjectTest<ObjectType extends RequestObject<ParamsType, IdType>, IdType, ParamsType>
        extends AbstractRequestObjectTest<ObjectType, IdType> {

    public RequestObjectTest(final Class<? extends ObjectType> requestClass, final Class<? extends IdType> idClass,
                             final Class<? extends ParamsType> paramsClass) {
        super(requestClass, idClass);
        this.paramsClass = requireNonNull(paramsClass, "paramsClass is null");
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, consumer);
        try (InputStream resourceStream = RequestObjectTest.class.getResourceAsStream(name)) {
            try (JsonReader reader = Json.createReader(resourceStream)) {
                final JsonObject requestObject = reader.readObject();
                log.debug("requestObject: {}", requestObject);
                final String methodValue = requestObject.getString(RequestObject.PROPERTY_NAME_METHOD);
                log.debug("methodValue: " + methodValue);
                final JsonValue paramsJsonValue = requestObject.get(RequestObject.PROPERTY_NAME_PARAMS);
                log.debug("paramsJsonValue: {}", paramsJsonValue);
                if (paramsJsonValue != null && paramsClass != Void.class) {
                    final ParamsType paramsValue = JsonbUtils.applyJsonb(
                            v -> v.fromJson(paramsJsonValue.toString(), paramsClass));
                    log.debug("paramsValue: {}", paramsValue);
                }
                final JsonValue idValue = requestObject.get(JsonrpcObject.PROPERTY_NAME_ID);
                log.debug("idValue: {}", idValue);
                if (idValue != null) {
                    if (idValue.getValueType() == JsonValue.ValueType.STRING) {
                        log.debug("id as String: {}", ((JsonString) idValue).getString());
                    } else if (idValue.getValueType() == JsonValue.ValueType.NUMBER) {
                        log.debug("id as Number: {}", ((JsonNumber) idValue).bigIntegerValueExact());
                    }
                }
            }
        }
    }

    protected final Class<? extends ParamsType> paramsClass;
}

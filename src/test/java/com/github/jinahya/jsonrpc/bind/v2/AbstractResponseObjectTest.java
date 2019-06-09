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
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
 *
 * @param <ObjectType> response object type parameter
 * @param <IdType>     id type parameter
 */
@Slf4j
public abstract class AbstractResponseObjectTest<ObjectType extends ResponseObject<IdType, ?, ?>, IdType>
        extends JsonrpcObjectTest<ObjectType, IdType> {

    public AbstractResponseObjectTest(final Class<? extends ObjectType> responseClass,
                                      final Class<? extends IdType> idClass) {
        super(responseClass, idClass);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            ofNullable(v.getError()).ifPresent(e -> {
                final boolean codeForPredefinedErrors = e.isCodeForPredefinedErrors();
                final boolean codeForImplementationDefinedServerErrors = e.isCodeForImplementationDefinedServerErrors();
            });
            consumer.accept(v);
        });
        try (InputStream resourceStream = getClass().getResourceAsStream(name)) {
            try (JsonReader reader = Json.createReader(resourceStream)) {
                final JsonObject responseObject = reader.readObject();
                log.debug("responseObject: {}", responseObject);
                final JsonValue resultValue = responseObject.get(ResponseObject.PROPERTY_NAME_RESULT);
                log.debug("resultValue: {}", resultValue);
            }
        }
    }
}

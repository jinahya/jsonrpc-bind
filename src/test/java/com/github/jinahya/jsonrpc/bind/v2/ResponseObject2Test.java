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

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
 *
 * @param <T> response object type parameter
 * @param <U> result type parameter
 * @param <V> error type parameter
 * @param <W> error data type parameter
 */
@Slf4j
public abstract class ResponseObject2Test<T extends ResponseObject<U, V>, U, V extends ResponseObject.ErrorObject<W>, W>
        extends JsonrpcObjectTest<T> {

    public ResponseObject2Test(final Class<? extends T> responseClass, final Class<? extends U> resultClass,
                               final Class<? extends V> errorClass, final Class<? extends W> errorDataClass) {
        super(responseClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
        this.errorDataClass = requireNonNull(errorDataClass, "errorDataClass is null");
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super T> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            ofNullable(v.getError()).ifPresent(e -> {
                final boolean codeReservedForPredefinedErrors = e.isCodeReservedForPredefinedErrors();
                final boolean codeReservedForImplementationDefinedServerErrors
                        = e.isCodeReservedForImplementationDefinedServerErrors();
            });
            consumer.accept(v);
        });
        try (InputStream resourceStream = ResponseObject2Test.class.getResourceAsStream(name)) {
            try (JsonReader reader = Json.createReader(resourceStream)) {
                final JsonObject responseObject = reader.readObject();
                log.debug("responseObject: {}", responseObject);
                final JsonValue resultValue = responseObject.get(ResponseObject.NAME_RESULT);
                log.debug("resultValue: {}", resultValue);
            }
        }
    }

    protected final Class<? extends U> resultClass;

    protected final Class<? extends V> errorClass;

    protected final Class<? extends W> errorDataClass;
}

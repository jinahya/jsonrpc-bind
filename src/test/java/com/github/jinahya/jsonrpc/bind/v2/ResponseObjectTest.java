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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
 *
 * @param <ObjectType> response object type parameter
 * @param <IdType>     id type parameter
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <DataType>   data type parameter
 */
@Slf4j
public abstract class ResponseObjectTest<
        ObjectType extends ResponseObject<ResultType, ErrorType, IdType>,
        IdType,
        ResultType,
        ErrorType extends ResponseObject.ErrorObject<DataType>,
        DataType>
        extends AbstractResponseObjectTest<ObjectType, IdType> {

    public ResponseObjectTest(final Class<? extends ObjectType> responseClass, final Class<? extends IdType> idClass,
                              final Class<? extends ResultType> resultClass,
                              final Class<? extends ErrorType> errorClass, final Class<? extends DataType> dataClass) {
        super(responseClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
        this.dataClass = requireNonNull(dataClass, "dataClass is null");
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            ofNullable(v.getResult()).ifPresent(r -> {
                try {
                    v.setError(errorClass.getConstructor().newInstance());
                    assertNotNull(v.getError());
                    v.setResult(r);
                    assertNotNull(v.getError());
                    v.setResultExclusively(r);
                    assertNull(v.getError());
                } catch (final ReflectiveOperationException roe) {
                    roe.printStackTrace();
                }
            });
            ofNullable(v.getError()).ifPresent(e -> {
                try {
                    v.setResult(resultClass.getConstructor().newInstance());
                    assertNotNull(v.getResult());
                    v.setError(e);
                    assertNotNull(v.getResult());
                    v.setErrorExclusively(e);
                    assertNull(v.getResult());
                } catch (final ReflectiveOperationException roe) {
                    roe.printStackTrace();
                }
            });
            consumer.accept(v);
        });
        try (InputStream resourceStream = ResponseObjectTest.class.getResourceAsStream(name)) {
            try (JsonReader reader = Json.createReader(resourceStream)) {
                final JsonObject responseObject = reader.readObject();
                log.debug("responseObject: {}", responseObject);
                final JsonValue resultValue = responseObject.get(ResponseObject.PROPERTY_NAME_RESULT);
                log.debug("resultValue: {}", resultValue);
            }
        }
    }

    protected final Class<? extends ResultType> resultClass;

    protected final Class<? extends ErrorType> errorClass;

    protected final Class<? extends DataType> dataClass;
}

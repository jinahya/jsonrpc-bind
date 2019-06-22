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

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
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

    public ResponseObjectTest(final Class<? extends ObjectType> responseClass,
                              final Class<? extends ResultType> resultClass,
                              final Class<? extends ErrorType> errorClass, final Class<? extends IdType> idClass) {
        super(responseClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
    }

    @Override
    protected void acceptValueFromResource(String name, Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            v.setResultExclusively(v.getResult());
            v.setErrorExclusively(v.getError());
        });
    }

    @Test
    void build() {
        final ResponseObject<ResultType, ErrorType, IdType> built
                = ResponseObject.<ResultType, ErrorType, IdType>builder().build();
        log.debug("built: {}", built);
    }

    protected final Class<? extends ResultType> resultClass;

    protected final Class<? extends ErrorType> errorClass;
}

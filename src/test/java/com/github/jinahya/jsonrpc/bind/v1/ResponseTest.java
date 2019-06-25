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

import java.io.IOException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ResponseTest<
        ObjectType extends Response<ResultType, ErrorType, IdType>,
        ResultType,
        ErrorType,
        IdType>
        extends IdentifiableTest<ObjectType, IdType> {

    // -----------------------------------------------------------------------------------------------------------------
    public ResponseTest(final Class<? extends ObjectType> responseClass,
                        final Class<? extends ResultType> resultClass, final Class<? extends ErrorType> errorClass,
                        final Class<? extends IdType> idClass) {
        super(responseClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            {
                consumer.accept(v);
            }
            {
                final ObjectType obj = objectInstance();
                obj.setResult(v.getResult());
                obj.setError(v.getError());
                obj.setId(v.getId());
                assertEquals(obj, v);
                assertEquals(obj.hashCode(), v.hashCode());
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<? extends ResultType> resultClass;

    protected final Class<? extends ErrorType> errorClass;
}

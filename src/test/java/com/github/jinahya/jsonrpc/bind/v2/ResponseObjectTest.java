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

import java.io.IOException;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

@Slf4j
public abstract class ResponseObjectTest<T extends ResponseObject<?, ?>> extends JsonrpcObjectTest<ResponseObject> {

    public ResponseObjectTest(final Class<? extends T> responseClass) {
        super(responseClass);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ResponseObject> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            ofNullable(v.getError()).ifPresent(e -> {
                final boolean codeReservedForPredefinedErrors = e.isCodeReservedForPredefinedErrors();
                final boolean codeReservedForImplementationDefinedServerErrors
                        = e.isCodeReservedForImplementationDefinedServerErrors();
            });
            consumer.accept(v);
        });
    }
}

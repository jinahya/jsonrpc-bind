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

/**
 * An abstract class for testing subclasses of {@link Request}.
 *
 * @param <ObjectType> request type parameter
 * @param <ParamType>  params element type parameter
 * @param <IdType>     id type parameter
 */
public abstract class RequestTest<ObjectType extends Request<ParamType, IdType>, ParamType, IdType>
        extends AbstractRequestTest<ObjectType, ParamType, IdType> {

    // -----------------------------------------------------------------------------------------------------------------
    public RequestTest(final Class<? extends ObjectType> requestClass, final Class<? extends ParamType> paramClass,
                       final Class<? extends IdType> idClass) {
        super(requestClass, paramClass, idClass);
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
                final Request<Object, IdType> request = new Request<>();
                request.copyIdFrom(v);
                final Response<Object, Object, IdType> response = new Response<>();
                response.copyIdFrom(v);
            }
        });
    }
}

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

/**
 * An abstract class for testing subclasses of {@link RequestObject}.
 *
 * @param <ObjectType> request object type parameter
 */
@Slf4j
public abstract class RequestObjectTest<ObjectType extends RequestObject<?, ?>> extends JsonrpcObjectTest<ObjectType> {

    // -----------------------------------------------------------------------------------------------------------------
    public static class NoParams<IdType> extends RequestObject<Void, IdType> {

        @Override
        public void setParams(final Void params) {
            //super.setParams(params);
        }
    }

//    // -----------------------------------------------------------------------------------------------------------------
//    protected static class NoId<ParamsType> extends RequestObject<ParamsType, Void> {
//
//        @Override
//        public void setId(final Void id) {
//            //super.setId(id);
//        }
//    }

    // -----------------------------------------------------------------------------------------------------------------
    public RequestObjectTest(final Class<? extends ObjectType> requestClass) {
        super(requestClass);
    }
}

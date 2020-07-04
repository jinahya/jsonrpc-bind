package com.github.jinahya.jsonrpc.bind.v2.spi;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessage;

import java.util.ServiceLoader;

import static java.util.Objects.requireNonNull;

abstract class JsonrpcMessageServiceTest<T extends JsonrpcMessageService<U>, U extends JsonrpcMessage> {

    JsonrpcMessageServiceTest(final Class<T> serviceClass, final Class<U> messageClass) {
        super();
        this.serviceClass = requireNonNull(serviceClass, "serviceClass is null");
        this.messageClass = requireNonNull(messageClass, "messageClass is null");
    }

    T load() {
        return ServiceLoader.load(serviceClass).iterator().next();
    }

    final Class<T> serviceClass;

    final Class<U> messageClass;
}
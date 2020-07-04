package com.github.jinahya.jsonrpc.bind.v2;

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

import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcRequestMessageService;
import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcResponseMessageService;

import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * A helper clsss for SPI.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JsonrpcMessageServiceHelper {

    // -----------------------------------------------------------------------------------------------------------------
    private static ServiceLoader<JsonrpcRequestMessageService> jsonrpcRequestMessageServiceLoader;

    private static ServiceLoader<JsonrpcRequestMessageService> jsonrpcRequestMessageServiceLoader() {
        if (jsonrpcRequestMessageServiceLoader == null) {
            jsonrpcRequestMessageServiceLoader = load(JsonrpcRequestMessageService.class);
        }
        return jsonrpcRequestMessageServiceLoader;
    }

    static JsonrpcRequestMessageService loadJsonrpcRequestMessageService(final boolean reuse, final boolean reload) {
        if (reuse) {
            final ServiceLoader<JsonrpcRequestMessageService> loader = jsonrpcRequestMessageServiceLoader();
            if (reload) {
                synchronized (loader) {
                    loader.reload();
                }
            }
            synchronized (loader) {
                return loader.iterator().next();
            }
        }
        return load(JsonrpcRequestMessageService.class).iterator().next();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static ServiceLoader<JsonrpcResponseMessageService> jsonrpcResponseMessageServiceLoader;

    private static ServiceLoader<JsonrpcResponseMessageService> jsonrpcResponseMessageServiceLoader() {
        if (jsonrpcResponseMessageServiceLoader == null) {
            jsonrpcResponseMessageServiceLoader = load(JsonrpcResponseMessageService.class);
        }
        return jsonrpcResponseMessageServiceLoader;
    }

    static JsonrpcResponseMessageService loadJsonrpcResponseMessageService(final boolean reuse, final boolean reload) {
        if (reuse) {
            final ServiceLoader<JsonrpcResponseMessageService> loader = jsonrpcResponseMessageServiceLoader();
            if (reload) {
                synchronized (loader) {
                    loader.reload();
                }
            }
            synchronized (loader) {
                return loader.iterator().next();
            }
        }
        return load(JsonrpcResponseMessageService.class).iterator().next();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonrpcMessageServiceHelper() {
        throw new AssertionError("instantiation is not allowed");
    }
}

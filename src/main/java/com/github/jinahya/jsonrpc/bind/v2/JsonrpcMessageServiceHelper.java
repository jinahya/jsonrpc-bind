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
 * A helper class for SPI.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class JsonrpcMessageServiceHelper {

    // -----------------------------------------------------------------------------------------------------------------
    private static ServiceLoader<JsonrpcRequestMessageService> requestMessageServiceLoader;

    private static ServiceLoader<JsonrpcRequestMessageService> requestMessageServiceLoader() {
        if (requestMessageServiceLoader == null) {
            requestMessageServiceLoader = load(JsonrpcRequestMessageService.class);
        }
        return requestMessageServiceLoader;
    }

    static JsonrpcRequestMessageService requestMessageService(final boolean reuse, final boolean reload) {
        if (reuse) {
            final ServiceLoader<JsonrpcRequestMessageService> loader = requestMessageServiceLoader();
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
    private static ServiceLoader<JsonrpcResponseMessageService> responseMessageServiceLoader;

    private static ServiceLoader<JsonrpcResponseMessageService> responseMessageServiceLoader() {
        if (responseMessageServiceLoader == null) {
            responseMessageServiceLoader = load(JsonrpcResponseMessageService.class);
        }
        return responseMessageServiceLoader;
    }

    static JsonrpcResponseMessageService responseMessageService(final boolean reuse, final boolean reload) {
        if (reuse) {
            final ServiceLoader<JsonrpcResponseMessageService> loader = responseMessageServiceLoader();
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
//    private static Constructor<? extends JsonrpcRequestMessage> requestMessageConstructor;
//
//    private static Constructor<? extends JsonrpcRequestMessage> requestMessageConstructor() {
//        if (requestMessageConstructor == null) {
//            final Class<? extends JsonrpcRequestMessage> requestMessageClass
//                    = requestMessageService(false, false).getImplementationClass();
//            try {
//                requestMessageConstructor = requestMessageClass.getDeclaredConstructor();
//            } catch (final NoSuchMethodException nsme) {
//                throw new JsonrpcBindException(nsme);
//            }
//            if (!requestMessageConstructor.isAccessible()) {
//                requestMessageConstructor.setAccessible(true);
//            }
//        }
//        return requestMessageConstructor;
//    }
//
//    private static MethodHandle unreflectedRequestMessageConstructor;
//
//    private static MethodHandle unreflectedRequestMessageConstructor() {
//        if (unreflectedRequestMessageConstructor == null) {
//            try {
//                unreflectedRequestMessageConstructor = lookup().unreflectConstructor(requestMessageConstructor());
//            } catch (final IllegalAccessException iae) {
//                throw new JsonrpcBindException(iae);
//            }
//        }
//        return unreflectedRequestMessageConstructor;
//    }

    static JsonrpcRequestMessage requestMessageInstance() {
        return load(JsonrpcRequestMessage.class).iterator().next();
    }

    // -----------------------------------------------------------------------------------------------------------------
//    private static Constructor<? extends JsonrpcResponseMessage> responseMessageConstructor;
//
//    private static Constructor<? extends JsonrpcResponseMessage> responseMessageConstructor() {
//        if (responseMessageConstructor == null) {
//            final Class<? extends JsonrpcResponseMessage> responseMessageClass
//                    = responseMessageService(false, false).getImplementationClass();
//            try {
//                responseMessageConstructor = responseMessageClass.getDeclaredConstructor();
//            } catch (final NoSuchMethodException nsme) {
//                throw new JsonrpcBindException(nsme);
//            }
//            if (!responseMessageConstructor.isAccessible()) {
//                responseMessageConstructor.setAccessible(true);
//            }
//        }
//        return responseMessageConstructor;
//    }
//
//    private static MethodHandle unreflectedResponseMessageConstructor;
//
//    private static MethodHandle unreflectedResponseMessageConstructor() {
//        if (unreflectedResponseMessageConstructor == null) {
//            try {
//                unreflectedResponseMessageConstructor = lookup().unreflectConstructor(responseMessageConstructor());
//            } catch (final IllegalAccessException iae) {
//                throw new JsonrpcBindException(iae);
//            }
//        }
//        return unreflectedResponseMessageConstructor;
//    }

    static JsonrpcResponseMessage responseMessageInstance() {
        return load(JsonrpcResponseMessage.class).iterator().next();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonrpcMessageServiceHelper() {
        throw new AssertionError("instantiation is not allowed");
    }
}

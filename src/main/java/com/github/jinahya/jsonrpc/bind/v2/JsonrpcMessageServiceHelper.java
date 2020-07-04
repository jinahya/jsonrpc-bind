package com.github.jinahya.jsonrpc.bind.v2;

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

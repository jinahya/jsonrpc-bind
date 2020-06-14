package com.github.jinahya.jsonrpc.bind;

public class JsonrpcBindException extends RuntimeException {

    public JsonrpcBindException(final String message) {
        super(message);
    }

    public JsonrpcBindException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JsonrpcBindException(final Throwable cause) {
        super(cause);
    }
}

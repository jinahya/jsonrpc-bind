package com.github.jinahya.jsonrpc.bind;

/**
 * An unchecked exception for JSONRPC Binding.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class JsonrpcBindException extends RuntimeException {

    /**
     * Creates a new instance with specified message.
     *
     * @param message the message.
     */
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

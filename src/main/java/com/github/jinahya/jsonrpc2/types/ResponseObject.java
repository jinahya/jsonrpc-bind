package com.github.jinahya.jsonrpc2.types;

/**
 * .
 *
 * @param <T> self type parameter
 * @param <U> result type parameter
 * @param <V> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<T extends ResponseObject<T, U, V>, U, V extends ErrorObject<V, ?>> extends JsonRpcObject<T> {

    // -----------------------------------------------------------------------------------------------------------------
    public U getResult() {
        return result;
    }

    public void setResult(final U result) {
        this.result = result;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public V getError() {
        return error;
    }

    public void setError(final V error) {
        this.error = error;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    // -----------------------------------------------------------------------------------------------------------------
    private U result;

    private V error;
}

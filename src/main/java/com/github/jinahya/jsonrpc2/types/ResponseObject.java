package com.github.jinahya.jsonrpc2.types;

import javax.validation.constraints.AssertTrue;
import java.util.Objects;

/**
 * .
 *
 * @param <T> self type parameter
 * @param <U> result type parameter
 * @param <V> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<T extends ResponseObject<T, U, V>, U, V extends ErrorObject<V, ?>>
        extends JsonrpcObject<T> {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "result=" + result
               + ",error=" + error
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseObject)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final ResponseObject<?, ?, ?> that = (ResponseObject<?, ?, ?>) o;
        return Objects.equals(getResult(), that.getResult())
               && Objects.equals(getError(), that.getError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResult(), getError());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Checks if the {@code result} attribute and the {@code error} attribute are exclusive.
     *
     * @return
     */
    @AssertTrue(message = "either result or error can be set")
    public boolean isResultAndErrorAreExclusive() {
        return (getResult() != null) ^ (getError() != null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public U getResult() {
        return result;
    }

    public void setResult(final U result) {
        this.result = result;
    }

    @SuppressWarnings({"unchecked"})
    public T result(final U result) {
        setResult(result);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public V getError() {
        return error;
    }

    public void setError(final V error) {
        this.error = error;
    }

    @SuppressWarnings({"unchecked"})
    public T error(final V error) {
        setError(error);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private U result;

    private V error;
}

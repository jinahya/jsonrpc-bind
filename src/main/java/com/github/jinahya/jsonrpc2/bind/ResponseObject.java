package com.github.jinahya.jsonrpc2.bind;

import javax.validation.constraints.AssertTrue;
import java.util.Objects;

/**
 * .
 *
 * @param <U> result type parameter
 * @param <V> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<U, V extends ErrorObject<?>> extends JsonrpcObject {

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
        final ResponseObject<?, ?> that = (ResponseObject<?, ?>) o;
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

    // -----------------------------------------------------------------------------------------------------------------
    public V getError() {
        return error;
    }

    public void setError(final V error) {
        this.error = error;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private U result;

    private V error;
}

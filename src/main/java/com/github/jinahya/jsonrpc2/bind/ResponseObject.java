package com.github.jinahya.jsonrpc2.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.AssertTrue;
import java.util.Objects;

/**
 * Represents response objects.
 *
 * @param <T> result type parameter
 * @param <U> error type parameter
 * @see <a href="https://www.jsonrpc.org/specification#response_object">5. Response Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class ResponseObject<T, U extends ErrorObject<?>> extends JsonrpcObject {

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
     * @return {@code true} if {@code result} and {@code error} set exclusively, {@code false} otherwise.
     */
    //@JsonIgnore
    //@JsonbTransient
    @AssertTrue(message = "result and error should be set exclusively")
    private boolean isResultAndErrorSetExclusively() {
        return (getResult() != null) ^ (getError() != null);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@code result} attribute.
     *
     * @return current value of {@code result} attribute
     */
    public T getResult() {
        return result;
    }

    /**
     * Replaces value of {@code result} attribute with given.
     *
     * @param result new value for {@code result} attribute
     */
    public void setResult(final T result) {
        this.result = result;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns current value of {@code error} attribute.
     *
     * @return current value of {@code error} attribute
     */
    public U getError() {
        return error;
    }

    /**
     * Replaces value of {@code error} attribute with given.
     *
     * @param error new value for {@code error} attribute
     */
    public void setError(final U error) {
        this.error = error;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private T result;

    private U error;
}

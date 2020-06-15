package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.constraints.AssertFalse;
import java.util.List;

public interface JsonrpcResponseMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.result}. The value is {@value}.
     */
    String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error}. The value is {@value}.
     */
    String PROPERTY_NAME_ERROR = "error";

    @AssertFalse
    default boolean isBothResultAndErrorSet() {
        return hasResult() && hasError();
    }

    boolean hasResult();

    default boolean isResultContextuallyValid() {
        return true;
    }

    <T> List<T> getResultAsArray(Class<T> elementClass);

    void setResultAsArray(List<?> result);

    <T> T getResultAsObject(Class<T> objectClass);

    void setResultAsObject(Object result);

    boolean hasError();

    default boolean isErrorContextuallyValid() {
        return true;
    }

    <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz);

    void setErrorAs(JsonrpcResponseMessageError error);
}

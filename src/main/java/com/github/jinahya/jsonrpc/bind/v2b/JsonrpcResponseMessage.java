package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.AssertFalse;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

public interface JsonrpcResponseMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.result}. The value is {@value}.
     */
    String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error}. The value is {@value}.
     */
    String PROPERTY_NAME_ERROR = "error";

    // -----------------------------------------------------------------------------------------------------------------
    @AssertFalse
    default boolean isBothResultAndErrorSet() {
        return hasResult() && hasError();
    }

    // ---------------------------------------------------------------------------------------------------------- result
    boolean hasResult();

    default boolean isResultContextuallyValid() {
        return true;
    }

    default Boolean getResultAsBoolean() {
        return getResultAsBoolean(false);
    }

    Boolean getResultAsBoolean(boolean lenient);

    void setResultAsBoolean(Boolean result);

    default String getResultAsString() {
        return getResultAsString(false);
    }

    String getResultAsString(boolean lenient);

    void setResultAsString(String result);

    default BigDecimal getResultAsNumber() {
        return getResultAsNumber(false);
    }

    BigDecimal getResultAsNumber(boolean lenient);

    void setResultAsNumber(BigDecimal result);

    default <T> List<T> getResultAsList(Class<T> elementClass) {
        return getResultAsList(requireNonNull(elementClass, "elementClass is null"), false);
    }

    <T> List<T> getResultAsList(Class<T> elementClass, boolean lenient);

    void setResultAsList(List<?> result);

    default <T> T getResultAsObject(Class<T> objectClass) {
        return getResultAsObject(requireNonNull(objectClass, "objectClass is null"), false);
    }

    <T> T getResultAsObject(Class<T> objectClass, boolean lenient);

    void setResultAsObject(Object result);

    // ----------------------------------------------------------------------------------------------------------- error
    boolean hasError();

    default boolean isErrorContextuallyValid() {
        return true;
    }

    <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz);

    void setErrorAs(JsonrpcResponseMessageError error);
}

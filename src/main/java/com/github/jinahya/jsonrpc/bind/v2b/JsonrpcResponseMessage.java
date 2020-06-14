package com.github.jinahya.jsonrpc.bind.v2b;

import com.github.jinahya.jsonrpc.bind.JsonrpcBindException;

import javax.validation.constraints.AssertFalse;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

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

    default Long getResultAsLong() {
        if (!hasResult()) {
            return null;
        }
        return getResultAsLong(false);
    }

    default Long getResultAsLong(final boolean lenient) {
        if (!hasResult()) {
            return null;
        }
        if (lenient) {
            return ofNullable(getResultAsNumber(true)).map(BigDecimal::longValueExact).orElse(null);
        }
        throw new JsonrpcBindException("unable to bind result as a long");
    }

    default void setResultAsLong(final Long result) {
        setResultAsNumber(ofNullable(result).map(BigDecimal::valueOf).orElse(null));
    }

    default Integer getResultAsInteger() {
        if (!hasResult()) {
            return null;
        }
        return getResultAsInteger(false);
    }

    default Integer getResultAsInteger(final boolean lenient) {
        if (!hasResult()) {
            return null;
        }
        if (lenient) {
            return ofNullable(getResultAsLong(true)).map(Math::toIntExact).orElse(null);
        }
        throw new JsonrpcBindException("unable to bind result as an integer");
    }

    default void setResultAsInteger(final Integer result) {
        setResultAsLong(ofNullable(result).map(Integer::longValue).orElse(null));
    }

    default <T> List<T> getResultAsArray(Class<T> elementClass) {
        return getResultAsArray(requireNonNull(elementClass, "elementClass is null"), false);
    }

    <T> List<T> getResultAsArray(Class<T> elementClass, boolean lenient);

    void setResultAsArray(List<?> result);

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

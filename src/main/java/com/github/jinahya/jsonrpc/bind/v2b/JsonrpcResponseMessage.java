package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.AssertFalse;
import java.math.BigDecimal;
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

    // -----------------------------------------------------------------------------------------------------------------
    @AssertFalse
    default boolean isHasResultAndHasError() {
        return hasResult() && hasError();
    }

    // ---------------------------------------------------------------------------------------------------------- result
    boolean hasResult();

    default boolean isResultContextuallyValid() {
        return true;
    }

    Boolean getResultAsBoolean();

    void setResultAsBoolean(Boolean result);

    String getResultAsString();
    
    void setResultAsString(String result);

    BigDecimal getResultAsNumber();

    void setResultAsNumber(BigDecimal result);

    <T> List<T> getResultAsList(Class<T> elementClass);

    void setResultAsList(List<?> result);

    <T> T getResultAsObject(Class<T> objectClass);

    void setResultAsObject(Object result);

    // ----------------------------------------------------------------------------------------------------------- error
    boolean hasError();

    default boolean isErrorContextuallyValid() {
        return true;
    }

    <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz);

    void setErrorAs(JsonrpcResponseMessageError error);
}

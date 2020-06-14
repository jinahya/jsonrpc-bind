package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

public interface JsonrpcResponseMessageError extends JsonrpcObject {

    // -----------------------------------------------------------------------------------------------------------------
    String PROPERTY_NAME_CODE = "code";

    String PROPERTY_NAME_MESSAGE = "message";

    String PROPERTY_NAME_DATA = "data";

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns the current value of {@value #PROPERTY_NAME_CODE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_CODE} property
     */
    int getCode();

    void setCode(int code);

    // --------------------------------------------------------------------------------------------------------- message
    @NotNull
    String getMessage();

    void setMessage(String message);

    // ------------------------------------------------------------------------------------------------------------ data
    boolean hasData();

    default boolean isDataContextuallyValid() {
        return true;
    }

    default Boolean getDataAsBoolean() {
        return getDataAsBoolean(false);
    }

    Boolean getDataAsBoolean(boolean lenient);

    void setDataAsBoolean(Boolean data);

    default String getDataAsString() {
        return getDataAsString(false);
    }

    String getDataAsString(boolean lenient);

    void setDataAsString(String data);

    default BigDecimal getDataAsNumber() {
        return getDataAsNumber(false);
    }

    BigDecimal getDataAsNumber(boolean lenient);

    void setDataAsNumber(BigDecimal data);

    default <T> List<T> getDataAsArray(Class<T> elementClass) {
        return getDataAsArray(requireNonNull(elementClass, "elementClass is null"), false);
    }

    <T> List<T> getDataAsArray(Class<T> elementClass, boolean lenient);

    void setDataAsArray(List<?> data);

    default <T> T getDataAsObject(Class<T> objectClass) {
        return getDataAsObject(requireNonNull(objectClass, "objectClass is null"), false);
    }

    <T> T getDataAsObject(Class<T> objectClass, boolean linent);

    void setDataAsObject(Object data);
}

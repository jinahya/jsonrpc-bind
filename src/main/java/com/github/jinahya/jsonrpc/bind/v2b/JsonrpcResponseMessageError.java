package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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

    Boolean getDataAsBoolean();

    void setDataAsBoolean(Boolean data);

    String getDataAsString();

    void setDataAsString(String data);

    BigDecimal getDataAsNumber();

    void setDataAsNumber(BigDecimal data);

    <T> List<T> getDataAsList(Class<?> elementClass);

    void setDataAsList(List<?> data);

    <T> T getDataAsObject(Class<T> objectClass);

    void setDataAsObject(Object data);
}

package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;
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

    default boolean isDataContextuallyValid() {
        return true;
    }

    /**
     * Reads the current value of {@value #PROPERTY_NAME_DATA} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    <T> List<T> getDataAsArray(Class<T> elementClass);

    void setDataAsArray(List<?> data);

    <T> T getDataAsObject(Class<T> objectClass);

    void setDataAsObject(Object data);
}

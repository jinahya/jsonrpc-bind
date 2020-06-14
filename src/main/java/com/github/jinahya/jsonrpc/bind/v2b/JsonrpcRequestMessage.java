package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface JsonrpcRequestMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.method}. The value is {@value}.
     */
    String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params}. The value is {@value}.
     */
    String PROPERTY_NAME_PARAMS = "params";

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    @NotBlank
    String getMethod();

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    void setMethod(String method);

    boolean hasParams();

    /**
     * Reads the current value of {@value #PROPERTY_NAME_PARAMS} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    <T> List<T> getParamsAsArray(final Class<T> elementClass);

    void setParamsAsArray(List<?> params);

    <T> T getParamsAsObject(final Class<T> objectClass);

    void setParamsAsObject(Object params);
}

package com.github.jinahya.jsonrpc.bind.v2;

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

    /**
     * Indicates this message has a value for {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return {@code} true if this message has a value for {@value #PROPERTY_NAME_PARAMS} property; {@code false}
     * otherwise.
     */
    boolean hasParams();

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property as a list of specified element type.
     *
     * @param elementClass the element type.
     * @param <T>          element type parameter
     * @return a list of {@code elementClass}.
     */
    <T> List<T> getParamsAsArray(final Class<T> elementClass);

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsArray(List<?> params);

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property as an instance of specified object type.
     *
     * @param objectClass the object type.
     * @param <T>         object type parameter
     * @return an instance of {@code objectClass}.
     */
    <T> T getParamsAsObject(final Class<T> objectClass);

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with specified value.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    void setParamsAsObject(Object params);
}

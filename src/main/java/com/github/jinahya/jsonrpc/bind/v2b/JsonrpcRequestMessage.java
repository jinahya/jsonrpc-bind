package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static java.util.Objects.requireNonNull;

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

    default <T> List<T> getParamsAsArray(final Class<T> elementClass) {
        return getParamsAsArray(requireNonNull(elementClass, "elementClass is null"), false);
    }

    <T> List<T> getParamsAsArray(Class<T> elementClass, boolean lenient);

    void setParamsAsArray(List<?> params);

    default <T> T getParamsAsObject(final Class<T> objectClass) {
        return getParamsAsObject(requireNonNull(objectClass, "objectClass is null"), false);
    }

    <T> T getParamsAsObject(Class<T> objectClass, boolean lenient);

    void setParamsAsObject(Object params);
}

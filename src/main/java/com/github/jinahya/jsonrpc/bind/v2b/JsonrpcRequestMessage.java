package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public abstract class JsonrpcRequestMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.method}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params}. The value is {@value}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    // ---------------------------------------------------------------------------------------------------------- method
    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    // ---------------------------------------------------------------------------------------------------------- params
    @SuppressWarnings({"unchecked"})
    public <T> T[] getParamsAsArray(final Class<T> elementClass) {
        requireNonNull(elementClass, "elementClass is null");
        return (T[]) ofNullable(getParamsAsList(elementClass)).map(v -> v.toArray(new Object[0])).orElse(null);
    }

    public <T> void setParamsAsArray(final T[] params) {
        setParamsAsList(ofNullable(params).map(Arrays::asList).orElse(null));
    }

    public abstract <T> List<T> getParamsAsList(Class<T> elementClass);

    public abstract <T> void setParamsAsList(List<? extends T> params);

    public abstract <T> T getParamsAsObject(Class<T> clazz);

    public abstract <T> void setParamsAsObject(T params);

    @NotBlank
    private String method;
}

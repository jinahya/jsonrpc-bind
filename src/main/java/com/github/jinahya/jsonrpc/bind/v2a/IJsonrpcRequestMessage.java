package com.github.jinahya.jsonrpc.bind.v2a;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public interface IJsonrpcRequestMessage extends IJsonrpcMessage {

    /**
     * The property name for {@code $.method}. The value is {@value}.
     */
    String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code $.params}. The value is {@value}.
     */
    String PROPERTY_NAME_PARAMS = "params";

    // ---------------------------------------------------------------------------------------------------------- method
    @NotBlank
    String getMethod();

    void setMethod(String method);

    // ---------------------------------------------------------------------------------------------------------- params
    default boolean[] getParamsAsBooleanArray(final boolean[] defaultValue) {
        final List<Boolean> list = getParamsAsList(Boolean.class);
        if (list == null) {
            return defaultValue;
        }
        final boolean[] array = new boolean[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    default boolean[] getParamsAsBooleanArray() {
        return getParamsAsBooleanArray(null);
    }

    default void setParamsAsBooleanArray(final boolean[] params) {
        if (params == null) {
            setParamsAsObject(null);
            return;
        }
        final List<Boolean> list = new ArrayList<>(params.length);
        for (final boolean param : params) {
            list.add(param);
        }
        setParamsAsList(list);
    }

//    int[] getParamsAsIntArray();
//
//    void setParamsAsIntArray(int[] params);
//
//    long[] getParamsAsLongArray();
//
//    void setParamsAsLongArray(long[] params);
//
//    double[] getParamsAsDoubleArray();
//
//    void setParamsAsDoubleArray(double[] params);

    <T> List<T> getParamsAsList(final Class<T> elementClass);

    <T> void setParamsAsList(final List<T> params);

    default <T extends Number> List<T> getParamsAsNumbers(final Class<T> elementClass) {
        return getParamsAsList(requireNonNull(elementClass, "elementClass is null"));
    }

    default <T extends Number> void setParamsAsNumbers(final List<? extends T> params) {
        setParamsAsList(requireNonNull(params, "params is null"));
    }

    <T> T getParamsAsObject(Class<T> objectClass);

    void setParamsAsObject(Object params);
}

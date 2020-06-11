package com.github.jinahya.jsonrpc.bind.v2a;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public interface JsonrpcRequestObject extends JsonrpcObject {

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
    @NotNull
    int[] getParamsAsInts();

    void setParamsAsInts(int[] params);

    @NotNull
    long[] getParamsAsLongs();

    void setParamsAsLongs(long[] params);

    @NotNull
    double[] getParamsAsDoubles();

    void setParamsAsDoubles(double[] params);

//    void getParamsAsPositional(List<?> params);
//
//    void setParamsAsPositional(List<?> params);

    <T> List<T> getParamsAsPositional(final Class<T> elementClass);

    <T> void setParamsAsPositional(final List<T> params);

    default List<BigDecimal> getParamsAsNumbers() {
        return getParamsAsPositional(BigDecimal.class);
    }

    default void setParamsAsNumbers(final List<? extends BigDecimal> params) {
        setParamsAsPositional(params);
    }

    <T> T getParamsAsNamed(Class<T> clazz);

    void setParamsAsNamed(Object params);
}

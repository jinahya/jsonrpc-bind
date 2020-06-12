package com.github.jinahya.jsonrpc.bind.v2b;

import java.util.List;

public abstract class JsonrpcResponseMessage extends JsonrpcMessage {

    /**
     * The property name for {@code $.result}. The value is {@value}.
     */
    String PROPERTY_NAME_RESULT = "result";

    /**
     * The property name for {@code $.error}. The value is {@value}.
     */
    String PROPERTY_NAME_ERROR = "error";

//    /**
//     * The property name for {@code $.error.code} of response objects. The value is {@value}.
//     */
//    public static final String PROPERTY_NAME_CODE = "code";
//
//    /**
//     * The property name for {@code $.error.message} of response objects. The value is {@value}.
//     */
//    public static final String PROPERTY_NAME_MESSAGE = "message";
//
//    /**
//     * The property name for {@code $.error.data} of response objects. The value is {@value}.
//     */
//    public static final String PROPERTY_NAME_DATA = "data";

    // ---------------------------------------------------------------------------------------------------------- result
    public abstract boolean hasResult();

//    boolean getResultAsBoolean();
//
//    void setResultAsBoolean(boolean resultAsBoolean);
//
//    int getResultAsInt();
//
//    void setResultAsInt();
//
//    long getResultAsLong();
//
//    void setResultAsLong();
//
//    double getResultAsDouble();
//
//    void setResultAsDouble();
//
//    <T> T getResultAsObject(Class<T> clazz);
//
//    void setResultAsObject(Object result);

    public <T> T[] getResultAsArray(final Class<T> elementClass) {
    }

    public <T> void setResultAsArray(final T[] result) {
    }

    public <T> L
    ist<T> getResultAsList(final Class<T> elementClass) {
    }

    // ----------------------------------------------------------------------------------------------------------- error
    public abstract boolean hasError();

    public abstract <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz);

    public abstract void setErrorAs(JsonrpcResponseMessageError value);
}

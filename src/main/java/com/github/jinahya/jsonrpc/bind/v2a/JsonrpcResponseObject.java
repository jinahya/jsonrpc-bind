package com.github.jinahya.jsonrpc.bind.v2a;

public interface JsonrpcResponseObject extends JsonrpcObject {

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
    boolean hasResult();

    boolean getResultAsBoolean();

    void setResultAsBoolean(boolean resultAsBoolean);

    int getResultAsInt();

    void setResultAsInt();

    long getResultAsLong();

    void setResultAsLong();

    double getResultAsDouble();

    void setResultAsDouble();

    <T> T getResultAsNamed(Class<T> clazz);

    void setResultAsNamed(Object resultAsNamed);

    // ----------------------------------------------------------------------------------------------------------- error
    boolean hasError();

    boolean getErrorAsBoolean();

    void setErrorAsBoolean(boolean errorAsBoolean);

    int getErrorAsInt();

    void setErrorAsInt();

    long getErrorAsLong();

    void setErrorAsLong();

    double getErrorAsDouble();

    void setErrorAsDouble();

    <T> T getErrorAsNamed(Class<T> clazz);

    void setErrorAsNamed(Object errorAsNamed);
}

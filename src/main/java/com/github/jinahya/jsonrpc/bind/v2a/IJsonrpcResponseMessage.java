package com.github.jinahya.jsonrpc.bind.v2a;

public interface IJsonrpcResponseMessage extends IJsonrpcMessage {

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

    // ----------------------------------------------------------------------------------------------------------- error
    boolean hasError();

    <T extends IJsonrpcResponseErrorObject> T getError(Class<T> clazz);

    void setError(IJsonrpcResponseErrorObject value);
}

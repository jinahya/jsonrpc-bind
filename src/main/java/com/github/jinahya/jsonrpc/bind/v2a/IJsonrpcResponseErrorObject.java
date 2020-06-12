package com.github.jinahya.jsonrpc.bind.v2a;

public interface IJsonrpcResponseErrorObject extends IJsonrpcObject {

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

    // -----------------------------------------------------------------------------------------------------------------
    String getMessage();

    void setMessage(String message);

    // ------------------------------------------------------------------------------------------------------------ data
    // TODO: 2020/06/12 define some!!!
}

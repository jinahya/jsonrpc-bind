package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotBlank;

public abstract class AbstractJsonrpcRequestMessage extends AbstractJsonrpcMessage implements JsonrpcRequestMessage {

    @Override
    public String toString() {
        return super.toString() + "{"
               + "method=" + method
               + "}";
    }

    // ---------------------------------------------------------------------------------------------------------- method

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    @Override
    public @NotBlank String getMethod() {
        return method;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_METHOD} property with specified value.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    @Override
    public void setMethod(final String method) {
        this.method = method;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String method;
}

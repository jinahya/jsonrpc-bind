package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.constraints.AssertTrue;
import java.math.BigInteger;
import java.util.List;

class DummyJsonrpcResponseMessage extends AbstractJsonrpcResponseMessage {

    @Override
    public boolean hasResult() {
        return false;
    }

    @Override
    public <T> List<T> getResultAsArray(Class<T> elementClass) {
        return null;
    }

    @Override
    public void setResultAsArray(List<?> result) {

    }

    @Override
    public <T> T getResultAsObject(Class<T> objectClass) {
        return null;
    }

    @Override
    public void setResultAsObject(Object result) {

    }

    @Override
    public boolean hasError() {
        return false;
    }

    @Override
    public <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz) {
        return null;
    }

    @Override
    public void setErrorAs(JsonrpcResponseMessageError error) {

    }

    @Override
    public JsonrpcResponseMessageError getErrorAsDefaultType() {
        return null;
    }

    @Override
    public boolean hasId() {
        return false;
    }

    @Override
    public @AssertTrue boolean isIdContextuallyValid() {
        return false;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void setIdAsString(String id) {

    }

    @Override
    public BigInteger getIdAsNumber() {
        return null;
    }

    @Override
    public void setIdAsNumber(BigInteger id) {

    }

    @Override
    public String toJson() {
        return null;
    }
}

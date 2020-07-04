package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.constraints.AssertTrue;
import java.math.BigInteger;
import java.util.List;

class DummyJsonrpcRequestMessage
        extends AbstractJsonrpcRequestMessage {

    @Override
    public boolean hasParams() {
        return false;
    }

    @Override
    public @AssertTrue boolean isParamsContextuallyValid() {
        return false;
    }

    @Override
    public <T> List<T> getParamsAsArray(Class<T> elementClass) {
        return null;
    }

    @Override
    public void setParamsAsArray(List<?> params) {

    }

    @Override
    public <T> T getParamsAsObject(Class<T> objectClass) {
        return null;
    }

    @Override
    public void setParamsAsObject(Object params) {

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

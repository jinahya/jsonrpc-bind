package com.github.jinahya.jsonrpc.bind.v2;

import java.util.List;

class DummyJsonrpcResponseMessageError extends AbstractJsonrpcResponseMessageError {

    @Override
    public boolean hasData() {
        return false;
    }

    @Override
    public <T> List<T> getDataAsArray(Class<T> elementClass) {
        return null;
    }

    @Override
    public void setDataAsArray(List<?> data) {

    }

    @Override
    public <T> T getDataAsObject(Class<T> objectClass) {
        return null;
    }

    @Override
    public void setDataAsObject(Object data) {

    }

    @Override
    public String toJson() {
        return null;
    }
}

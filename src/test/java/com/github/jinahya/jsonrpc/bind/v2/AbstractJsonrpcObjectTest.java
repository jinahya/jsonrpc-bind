package com.github.jinahya.jsonrpc.bind.v2;

import static java.util.Objects.requireNonNull;

abstract class AbstractJsonrpcObjectTest<T extends AbstractJsonrpcObject> {

    protected AbstractJsonrpcObjectTest(final Class<T> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
    }

    protected final Class<T> objectClass;
}
package com.github.jinahya.jsonrpc2.types;

import java.util.Objects;

public abstract class JsonrpcObjectTest<T extends JsonrpcObject<T>> {

    // -----------------------------------------------------------------------------------------------------------------
    public JsonrpcObjectTest(final Class<T> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> typeClass;
}

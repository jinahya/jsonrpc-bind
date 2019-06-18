package com.github.jinahya.jsonrpc.bind.v1;

import static java.util.Objects.requireNonNull;

abstract class _RequestTest<T extends _Request<ParamType, IdType>, ParamType, IdType> extends _ObjectTest<T, IdType> {

    _RequestTest(final Class<? extends T> objectClass, final Class<? extends ParamType> paramClass,
                 final Class<? extends IdType> idClass) {
        super(objectClass, idClass);
        this.paramClass = requireNonNull(paramClass, "paramClass is null");
    }

    protected final Class<? extends ParamType> paramClass;
}

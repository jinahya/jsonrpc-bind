package com.github.jinahya.jsonrpc.bind.v1;

import static java.util.Objects.requireNonNull;

abstract class _ObjectTest<T extends _Object<IdType>, IdType> {

    _ObjectTest(final Class<? extends T> objectClass, final Class<? extends IdType> idClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
        this.idClass = requireNonNull(idClass, "idClass is null");
    }

    protected final Class<? extends T> objectClass;

    protected final Class<? extends IdType> idClass;
}

package com.github.jinahya.jsonrpc.bind.v1;

public abstract class RequestTest<T extends Request<ParamType, IdType>, ParamType, IdType>
        extends _RequestTest<T, ParamType, IdType> {

    public RequestTest(final Class<? extends T> objectClass, final Class<? extends ParamType> paramClass,
                       final Class<? extends IdType> idClass) {
        super(objectClass, paramClass, idClass);
    }
}

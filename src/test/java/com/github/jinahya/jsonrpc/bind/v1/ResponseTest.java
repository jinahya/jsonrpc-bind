package com.github.jinahya.jsonrpc.bind.v1;

import static java.util.Objects.requireNonNull;

public abstract class ResponseTest<T extends Response<ResultType, ErrorType, IdType>, ResultType, ErrorType, IdType>
        extends _ObjectTest<T, IdType> {

    public ResponseTest(final Class<? extends T> objectClass, final Class<? extends ResultType> resultClass,
                        final Class<? extends ErrorType> errorClass, final Class<? extends IdType> idClass) {
        super(objectClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
    }

    protected final Class<? extends ResultType> resultClass;

    protected final Class<? extends ErrorType> errorClass;
}

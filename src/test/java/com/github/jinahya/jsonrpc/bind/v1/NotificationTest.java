package com.github.jinahya.jsonrpc.bind.v1;

public abstract class NotificationTest<T extends Notification<ParamType>, ParamType>
        extends _RequestTest<T, ParamType, Void> {

    public NotificationTest(final Class<? extends T> objectClass, final Class<? extends ParamType> paramClass) {
        super(objectClass, paramClass, Void.class);
    }
}

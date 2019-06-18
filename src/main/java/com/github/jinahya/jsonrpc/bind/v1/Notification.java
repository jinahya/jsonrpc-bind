package com.github.jinahya.jsonrpc.bind.v1;

public class Notification<ParamType> extends AbstractRequest<ParamType, Void> {

    @Override
    public void setId(final Void id) {
        if (id == null) {
            super.setId(id);
            return;
        }
        throw new IllegalArgumentException("id is not null");
    }
}

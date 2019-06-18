package com.github.jinahya.jsonrpc.bind.v1;

public class Notification<ParamType> extends AbstractRequest<ParamType, Void> {

    @Override
    public void setId(final Void id) {
        // does nothing
    }
}

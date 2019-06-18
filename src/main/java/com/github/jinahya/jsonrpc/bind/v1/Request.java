package com.github.jinahya.jsonrpc.bind.v1;

import javax.validation.constraints.NotNull;

public class Request<ParamType, IdType> extends _Request<ParamType, IdType> {

    @NotNull
    @Override
    public IdType getId() {
        return super.getId();
    }
}

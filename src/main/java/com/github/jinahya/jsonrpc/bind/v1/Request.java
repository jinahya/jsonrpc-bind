package com.github.jinahya.jsonrpc.bind.v1;

import javax.validation.constraints.NotNull;

public class Request<ParamType, IdType> extends AbstractRequest<ParamType, IdType> {

    @NotNull
    @Override
    public IdType getId() {
        return super.getId();
    }
}

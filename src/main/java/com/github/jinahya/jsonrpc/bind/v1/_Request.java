package com.github.jinahya.jsonrpc.bind.v1;

import javax.validation.constraints.NotBlank;
import java.util.List;

abstract class _Request<ParamType, IdType> extends _Object<IdType> {

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public List<ParamType> getParams() {
        return params;
    }

    public void setParams(final List<ParamType> params) {
        this.params = params;
    }

    @NotBlank
    private String method;

    private List<ParamType> params;
}

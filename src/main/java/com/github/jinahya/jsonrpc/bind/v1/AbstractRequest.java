package com.github.jinahya.jsonrpc.bind.v1;

import javax.validation.constraints.NotBlank;
import java.util.List;

abstract class AbstractRequest<ParamType, IdType> extends AbstractJsonrpc<IdType> {

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

package com.github.jinahya.jsonrpc.bind.v1;

public class Response<ResultType, ErrorType, IdType> extends AbstractJsonrpc<IdType> {

    public ResultType getResult() {
        return result;
    }

    public void setResult(final ResultType result) {
        this.result = result;
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(final ErrorType error) {
        this.error = error;
    }

    private ResultType result;

    private ErrorType error;
}

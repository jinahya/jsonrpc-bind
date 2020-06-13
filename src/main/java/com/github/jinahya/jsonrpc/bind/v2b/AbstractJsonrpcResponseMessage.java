package com.github.jinahya.jsonrpc.bind.v2b;

public abstract class AbstractJsonrpcResponseMessage extends AbstractJsonrpcMessage implements JsonrpcResponseMessage {

    @Override
    public String toString() {
        return super.toString() + "{"
               + "}";
    }
}

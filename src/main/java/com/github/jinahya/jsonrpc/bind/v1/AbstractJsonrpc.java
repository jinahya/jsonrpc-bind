package com.github.jinahya.jsonrpc.bind.v1;

abstract class AbstractJsonrpc<IdType> {

    public IdType getId() {
        return id;
    }

    public void setId(final IdType id) {
        this.id = id;
    }

    private IdType id;
}

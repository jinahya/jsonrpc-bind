package com.github.jinahya.jsonrpc.bind.v1;

import java.io.Serializable;

abstract class AbstractObject<IdType> implements Serializable {

    public IdType getId() {
        return id;
    }

    public void setId(final IdType id) {
        this.id = id;
    }

    private IdType id;
}

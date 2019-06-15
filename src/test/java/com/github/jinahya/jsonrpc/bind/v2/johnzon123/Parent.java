package com.github.jinahya.jsonrpc.bind.v2.johnzon123;

import lombok.Getter;
import lombok.Setter;

public abstract class Parent<T extends Options> {

    @Override
    public String toString() {
        return "Parent{" +
               "options=" + options +
               '}';
    }

    @Setter
    @Getter
    private T options;
}

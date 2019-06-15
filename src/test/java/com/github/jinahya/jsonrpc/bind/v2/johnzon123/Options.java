package com.github.jinahya.jsonrpc.bind.v2.johnzon123;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

public abstract class Options {

    @Override
    public String toString() {
        return "Options{" +
               "name='" + name + '\'' +
               ", hidden='" + hidden + '\'' +
               '}';
    }

    @Setter
    @Getter
    String name = "name";

    @JsonbProperty
    public String getExposed() {
        return hidden;
    }

    public void setExposed(final String exposed) {
        this.hidden = exposed;
    }

    @JsonbTransient
    private String hidden = "hidden";
}

package com.github.jinahya.jsonrpc.bind.v2.johnzon123;

import javax.json.bind.annotation.JsonbTypeAdapter;

public class Child extends Parent<SomeOptions> {

    @JsonbTypeAdapter(SomeOptionsAdapter.class)
    @Override
    public SomeOptions getOptions() {
        return super.getOptions();
    }

    // https://issues.apache.org/jira/browse/JOHNZON-213
    @JsonbTypeAdapter(SomeOptionsAdapter.class)
    @Override
    public void setOptions(SomeOptions options) {
        super.setOptions(options);
    }
}

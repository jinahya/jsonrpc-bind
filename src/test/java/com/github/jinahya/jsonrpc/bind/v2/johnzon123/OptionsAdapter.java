package com.github.jinahya.jsonrpc.bind.v2.johnzon123;

import lombok.extern.slf4j.Slf4j;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.adapter.JsonbAdapter;

import static java.util.Objects.requireNonNull;

@Slf4j
public abstract class OptionsAdapter<T extends Options> implements JsonbAdapter<T, String> {

    static final Jsonb JSONB = JsonbBuilder.create();

    public OptionsAdapter(final Class<? extends T> optionsClass) {
        super();
        this.optionsClass = requireNonNull(optionsClass, "optionsClass is null");
    }

    @Override
    public String adaptToJson(final T t) throws Exception {
        log.debug("adapting {} to json...", t);
        return JSONB.toJson(t);
    }

    @Override
    public T adaptFromJson(final String s) throws Exception {
        log.debug("adapting from {}", s);
        return JSONB.fromJson(s, optionsClass);
    }

    protected final Class<? extends T> optionsClass;
}

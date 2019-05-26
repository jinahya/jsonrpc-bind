package com.github.jinahya.jsonrpc2.bind.gson;

import com.github.jinahya.jsonrpc2.bind.JsonrpcObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public abstract class JsonrpcObjectDeserializer<T extends JsonrpcObject> implements JsonDeserializer<T> {

    @Override
    public T deserialize(final JsonElement json, final Type typeOfT,
                         final JsonDeserializationContext context)
            throws JsonParseException {
        final T deserialized = context.deserialize(json, typeOfT);
        log.debug("deserialized: {}", deserialized);
        final Object id = deserialized.getId();
        log.debug("id: {}", id);
        if (id instanceof Double) {
            log.debug("is is an instance of Double");
            deserialized.setId(((Double) id).longValue());
        }
        return deserialized;
    }
}

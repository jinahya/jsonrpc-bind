package com.github.jinahya.jsonrpc2.bind.gson;

import com.github.jinahya.jsonrpc2.bind.JsonrpcObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class JsonrpcObjectTypeAdapter implements JsonSerializer<JsonrpcObject>, JsonDeserializer<JsonrpcObject> {

    @Override
    public JsonElement serialize(final JsonrpcObject src, final Type typeOfSrc,
                                 final JsonSerializationContext context) {
        final JsonElement serialized = context.serialize(src, typeOfSrc);
        final JsonObject object = serialized.getAsJsonObject();
        final JsonElement id = object.remove("id");
        if (id != null) {
            object.add("id", new JsonPrimitive(id.getAsLong()));
        }
        return object;
    }

    @Override
    public JsonrpcObject deserialize(final JsonElement json, final Type typeOfT,
                                     final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonrpcObject deserialized = context.deserialize(json, typeOfT);
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

package com.github.jinahya.jsonrpc2.bind.gson;

import com.github.jinahya.jsonrpc2.bind.JsonrpcObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public abstract class JsonrpcObjectSerializer<T extends JsonrpcObject> implements JsonSerializer<T> {
//public class JsonrpcObjectSerializer implements JsonSerializer<JsonrpcObject> {

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
}

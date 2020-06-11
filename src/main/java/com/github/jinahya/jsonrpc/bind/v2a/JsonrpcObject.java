package com.github.jinahya.jsonrpc.bind.v2a;

public interface JsonrpcObject {

    String getJsonrpc();

    void setJsonrpc(String jsonrpc);

    String getIdAsString();

    void setIdAsString(String idAsString);

    Long getIdAsLong();

    void setIdAsLong(Long idAsLong);
}

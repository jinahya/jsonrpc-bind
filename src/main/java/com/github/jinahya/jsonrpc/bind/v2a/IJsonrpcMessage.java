package com.github.jinahya.jsonrpc.bind.v2a;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

public interface IJsonrpcMessage extends IJsonrpcObject {

    String PROPERTY_NAME_JSONRPC = "jsonrpc";

    String PROPERTY_NAME_ID = "id";

    String PROPERTY_VALUE_JSONRPC = "2.0";

    // --------------------------------------------------------------------------------------------------------- jsonrpc
    @Pattern(regexp = "2\\.0")
    @NotNull
    String getJsonrpc();

    void setJsonrpc(String jsonrpc);

    // -------------------------------------------------------------------------------------------------------------- id
    String getIdAsString();

    void setIdAsString(String id);

    default Integer getIdAsInteger() {
        return ofNullable(getIdAsBigInteger()).map(BigInteger::intValueExact).orElse(null);
    }

    default void setIdAsInteger(final Integer id) {
        setIdAsBigInteger(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    default Long getIdAsLong() {
        return ofNullable(getIdAsBigInteger()).map(BigInteger::longValueExact).orElse(null);
    }

    default void setIdAsLong(final Long id) {
        setIdAsBigInteger(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    BigInteger getIdAsBigInteger();

    void setIdAsBigInteger(BigInteger id);
}

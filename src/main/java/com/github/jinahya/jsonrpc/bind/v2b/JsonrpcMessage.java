package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

public interface JsonrpcMessage extends JsonrpcObject {

    String PROPERTY_NAME_JSONRPC = "jsonrpc";

    String PROPERTY_NAME_ID = "id";

    String PROPERTY_VALUE_JSONRPC = "2.0";

    // --------------------------------------------------------------------------------------------------------- jsonrpc
    @Pattern(regexp = "2\\.0")
    @NotNull
    String getJsonrpc();

    void setJsonrpc(String jsonrpc);

    // -------------------------------------------------------------------------------------------------------------- id
    boolean hasId();

    @AssertTrue
    boolean isIdContextuallyValid();

    String getIdAsString();

    void setIdAsString(String id);

    BigInteger getIdAsNumber();

    void setIdAsNumber(final BigInteger id);

    default Long getIdAsLong() {
        return ofNullable(getIdAsNumber()).map(BigInteger::longValueExact).orElse(null);
    }

    default void setIdAsLong(final Long id) {
        setIdAsNumber(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    default Integer getIdAsInteger() {
        return ofNullable(getIdAsLong()).map(Math::toIntExact).orElse(null);
    }

    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}

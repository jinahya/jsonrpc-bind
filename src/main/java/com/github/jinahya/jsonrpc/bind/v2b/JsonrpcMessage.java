package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

public interface JsonrpcMessage extends JsonrpcObject {

    String PROPERTY_NAME_JSONRPC = "jsonrpc";

    String PROPERTY_PATTERN_REGEXP_JSONRPC = "2\\.0";

    String PROPERTY_VALUE_JSONRPC = PROPERTY_PATTERN_REGEXP_JSONRPC.replaceAll("\\\\.", ".");

    String PROPERTY_NAME_ID = "id";

    @Pattern(regexp = PROPERTY_PATTERN_REGEXP_JSONRPC)
    @NotNull
    String getJsonrpc();

    void setJsonrpc(String jsonrpc);

    boolean hasId();

    @AssertTrue
    boolean isIdContextuallyValid();

    String getIdAsString();

    void setIdAsString(String id);

    BigDecimal getIdAsNumber();

    void setIdAsNumber(final BigDecimal id);

    default BigInteger getIdAsBigInteger() {
        return ofNullable(getIdAsNumber()).map(BigDecimal::toBigIntegerExact).orElse(null);
    }

    default void setIdAsBigInteger(final BigInteger id) {
        setIdAsNumber(ofNullable(id).map(BigDecimal::new).orElse(null));
    }

    default Long getIdAsLong() {
        return ofNullable(getIdAsBigInteger()).map(BigInteger::longValueExact).orElse(null);
    }

    default void setIdAsLong(final Long id) {
        setIdAsBigInteger(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    default Integer getIdAsInteger() {
        return ofNullable(getIdAsLong()).map(Math::toIntExact).orElse(null);
    }

    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}

package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    default String getIdAsString() {
        return getIdAsString(false);
    }

    String getIdAsString(boolean lenient);

    void setIdAsString(String id);

    default BigInteger getIdAsNumber() {
        return getIdAsNumber(false);
    }

    BigInteger getIdAsNumber(boolean lenient);

    void setIdAsNumber(final BigInteger id);

    default Long getIdAsLong() {
        return getIdAsLong(false);
    }

    default Long getIdAsLong(final boolean lenient) {
        return ofNullable(getIdAsNumber(lenient)).map(BigInteger::longValueExact).orElse(null);
    }

    default void setIdAsLong(final Long id) {
        setIdAsNumber(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    default Integer getIdAsInteger() {
        return getIdAsInteger(false);
    }

    default Integer getIdAsInteger(final boolean lenient) {
        return ofNullable(getIdAsLong(lenient)).map(Math::toIntExact).orElse(null);
    }

    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}

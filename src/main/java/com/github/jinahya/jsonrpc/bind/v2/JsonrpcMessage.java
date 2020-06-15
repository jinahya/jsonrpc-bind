package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

public interface JsonrpcMessage extends JsonrpcObject {

    /**
     * The property name for JSONRPC version. The value is {@value}.
     */
    String PROPERTY_NAME_JSONRPC = "jsonrpc";

    String PROPERTY_PATTERN_REGEXP_JSONRPC = "2\\.0";

    String PROPERTY_VALUE_JSONRPC = PROPERTY_PATTERN_REGEXP_JSONRPC.replaceAll("\\\\.", ".");

    /**
     * The property name for identifying messages. The value is {@value}.
     */
    String PROPERTY_NAME_ID = "id";

    /**
     * Returns the current value of {@value #PROPERTY_NAME_JSONRPC} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_JSONRPC} property.
     */
    @Pattern(regexp = PROPERTY_PATTERN_REGEXP_JSONRPC)
    @NotNull
    String getJsonrpc();

    /**
     * Replaces current value of {@value #PROPERTY_NAME_JSONRPC} property with specified value.
     *
     * @param jsonrpc new value for {@value #PROPERTY_NAME_JSONRPC} property.
     */
    void setJsonrpc(String jsonrpc);

    /**
     * Indicates whether this message has a value for {@value #PROPERTY_NAME_ID} property.
     *
     * @return {@code} true if set; {@code false} otherwise.
     */
    boolean hasId();

    /**
     * Indicates whether the {@value #PROPERTY_NAME_ID} property is contextually valid.
     *
     * @return {@code true} if valid; @{@code false} otherwise.
     */
    @AssertTrue
    boolean isIdContextuallyValid();

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property as a string.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    String getIdAsString();

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    void setIdAsString(String id);

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property as a big integer value.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    BigInteger getIdAsNumber();

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    void setIdAsNumber(final BigInteger id);

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property as a long value.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    default Long getIdAsLong() {
        return ofNullable(getIdAsNumber()).map(BigInteger::longValueExact).orElse(null);
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    default void setIdAsLong(final Long id) {
        setIdAsNumber(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_ID} property as a long value.
     *
     * @return the current value of {@value #PROPERTY_NAME_ID} property.
     */
    default Integer getIdAsInteger() {
        return ofNullable(getIdAsLong()).map(Math::toIntExact).orElse(null);
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@value #PROPERTY_NAME_ID} property.
     */
    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}

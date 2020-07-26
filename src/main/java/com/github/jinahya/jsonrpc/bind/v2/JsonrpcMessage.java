package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.beans.Transient;
import java.math.BigInteger;

import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessageConstants.PROPERTY_PATTERN_REGEXP_JSONRPC;
import static java.util.Optional.ofNullable;

/**
 * A base interface for JSON-RPC 2.0 messages.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface JsonrpcMessage extends JsonrpcObject {

    // --------------------------------------------------------------------------------------------------------- jsonrpc

    /**
     * Returns current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_JSONRPC} property.
     *
     * @return current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_JSONRPC} property.
     */
    @Pattern(regexp = PROPERTY_PATTERN_REGEXP_JSONRPC)
    @NotNull
    String getJsonrpc();

    /**
     * Replaces current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_JSONRPC} property with specified value.
     *
     * @param jsonrpc new value for {@link JsonrpcMessageConstants#PROPERTY_NAME_JSONRPC} property.
     */
    void setJsonrpc(String jsonrpc);

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Indicates whether this message has a value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property.
     *
     * @return {@code} true if this message has a value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property;
     * {@code false} otherwise.
     * @implSpec When this method returns {@code false} any {@code getIdAs...()} method should return {@code null}.
     */
    @Transient
    boolean hasId();

    /**
     * Indicates whether current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property is contextually
     * valid.
     *
     * @return {@code true} if valid; {@code false} otherwise.
     * @apiNote It's considered to be valid if {@link #hasId()} returns {@code false}.
     */
    @Transient
    @AssertTrue
    boolean isIdContextuallyValid();

    /**
     * Returns current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property as a string.
     *
     * @return current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property; {@code null} when {@link
     * #hasId()} returns {@code false}.
     */
    @Transient
    String getIdAsString();

    /**
     * Replaces current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property.
     */
    void setIdAsString(String id);

    /**
     * Returns current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property as a number.
     *
     * @return current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property; {@code null} when {@link
     * #hasId()} returns {@code false}.
     */
    @Transient
    BigInteger getIdAsNumber();

    /**
     * Replaces current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property.
     */
    void setIdAsNumber(final BigInteger id);

    /**
     * Returns current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property as a {@code Long} value.
     *
     * @return current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property; {@code null} when {@link
     * #hasId()} returns {@code false}.
     * @see #getIdAsNumber()
     * @see BigInteger#longValueExact()
     */
    @Transient
    default Long getIdAsLong() {
        return ofNullable(getIdAsNumber()).map(BigInteger::longValueExact).orElse(null);
    }

    /**
     * Replaces current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property.
     * @see BigInteger#valueOf(long)
     * @see #setIdAsNumber(BigInteger)
     */
    default void setIdAsLong(final Long id) {
        setIdAsNumber(ofNullable(id).map(BigInteger::valueOf).orElse(null));
    }

    /**
     * Returns current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property as an {@code Integer} value.
     *
     * @return current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property; {@code null} when {@link
     * #hasId()} returns {@code false}.
     * @see #getIdAsLong()
     * @see StrictMath#toIntExact(long)
     */
    @Transient
    default Integer getIdAsInteger() {
        return ofNullable(getIdAsLong()).map(StrictMath::toIntExact).orElse(null);
    }

    /**
     * Replaces current value of {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property with given value.
     *
     * @param id new value for {@link JsonrpcMessageConstants#PROPERTY_NAME_ID} property.
     * @see Integer#longValue()
     * @see #setIdAsLong(Long)
     */
    default void setIdAsInteger(final Integer id) {
        setIdAsLong(ofNullable(id).map(Integer::longValue).orElse(null));
    }
}

package com.github.jinahya.jsonrpc.bind.v2b;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

import static java.util.Optional.ofNullable;

public class JsonrpcMessage implements JsonrpcObject {

    public static final String PROPERTY_NAME_JSONRPC = "jsonrpc";

    public static final String PROPERTY_NAME_ID = "id";

    public static final String PROPERTY_VALUE_JSONRPC = "2.0";

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(final String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(final BigInteger id) {
        this.id = ofNullable(id)
                .map(BigInteger::toByteArray)
                .map(BigInteger::new)
                .orElse(null);
    }

    public int getIdAsInt() {
        return ofNullable(getId())
                .map(BigInteger::intValueExact)
                .orElseThrow(() -> new IllegalStateException("id is currently not set"));
    }

    public void setIdAsInt(final int id) {
        setId(BigInteger.valueOf(id));
    }

    public long getIdAsLong() {
        return ofNullable(getId())
                .map(BigInteger::longValueExact)
                .orElseThrow(() -> new IllegalStateException("id is currently not set"));
    }

    public void setIdAsLong(final long id) {
        setId(BigInteger.valueOf(id));
    }

    @Pattern(regexp = "2\\.0")
    @NotNull
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;

    private BigInteger id;
}

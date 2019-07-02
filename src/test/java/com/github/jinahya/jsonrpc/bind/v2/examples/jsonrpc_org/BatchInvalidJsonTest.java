package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObjectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc.bind.JacksonTests.applyObjectMapper;
import static com.github.jinahya.jsonrpc.bind.JsonbTests.JSONB;
import static com.github.jinahya.jsonrpc.bind.JsonrpcTests.applyResourceStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class BatchInvalidJsonTest {

    // -----------------------------------------------------------------------------------------------------------------
//    public static final class VoidResponse extends ResponseObject<Void, ErrorObject<Void>, Void> {
//
//    }

    public static final class VoidError extends ErrorObject<Void> {

    }

    public static final class VoidResponse extends ResponseObject<Void, VoidError, Void> {

    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void batch_invalid_json_01_response__jackson() throws IOException {
        final TypeReference<ResponseObject<Void, ResponseObjectTest.NoData, Void>> typeReference
                = new TypeReference<ResponseObject<Void, ResponseObjectTest.NoData, Void>>() {
        };
        final ResponseObject<Void, ErrorObject<Void>, Void> responseObject = applyResourceStream(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/batch_invalid_json_01_response.json",
                s -> applyObjectMapper(m -> {
                    try {
                        return m.readValue(s, typeReference);
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                })
        );
        assertEquals(ErrorObject.CODE_PARSE_ERROR, responseObject.getError().getCode());
        assertEquals("Parse error", responseObject.getError().getMessage());
    }

    @Test
    void batch_invalid_json_01_response__jsonb() throws IOException {
        final ResponseObject<Void, VoidError, Void> responseObject = applyResourceStream(
                "/com/github/jinahya/jsonrpc/bind/v2/examples/jsonrpc_org/batch_invalid_json_01_response.json",
                s -> JSONB.fromJson(s, VoidResponse.class));
        log.debug("responseObject: {}", responseObject);
        assertEquals(ErrorObject.CODE_PARSE_ERROR, responseObject.getError().getCode());
        assertEquals("Parse error", responseObject.getError().getMessage());
    }
}

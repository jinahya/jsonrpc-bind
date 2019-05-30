package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NonExistentMethodResponseTest {

    @Test
    void jackson01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/non_existent_method_01_response.json",
                NonExistentMethodResponse.class,
                v -> {
                    final boolean errorCodeReservedForPredefinedErrors
                            = v.getError().isCodeReservedForPredefinedErrors();
                    final boolean errorCodeReservedForImplementationDefinedServerErrors
                            = v.getError().isCodeReservedForImplementationDefinedServerErrors();
                }
        );
    }

    @Test
    void jsonb01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/non_existent_method_01_response.json",
                NonExistentMethodResponse.class,
                v -> {
                    final boolean errorCodeReservedForPredefinedErrors
                            = v.getError().isCodeReservedForPredefinedErrors();
                    final boolean errorCodeReservedForImplementationDefinedServerErrors
                            = v.getError().isCodeReservedForImplementationDefinedServerErrors();
                }
        );
    }
}

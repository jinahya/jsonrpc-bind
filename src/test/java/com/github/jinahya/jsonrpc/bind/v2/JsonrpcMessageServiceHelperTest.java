package com.github.jinahya.jsonrpc.bind.v2;

import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcRequestMessageService;
import com.github.jinahya.jsonrpc.bind.v2.spi.JsonrpcResponseMessageService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessageServiceHelper.loadJsonrpcRequestMessageService;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessageServiceHelper.loadJsonrpcResponseMessageService;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JsonrpcMessageServiceHelperTest {

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                arguments(false, false),
                arguments(false, true),
                arguments(true, false),
                arguments(true, true)
        );
    }

    @MethodSource({"argumentsStream"})
    @ParameterizedTest
    void testLoadJsonrpcRequestMessageService(final boolean reuse, final boolean reload) {
        final JsonrpcRequestMessageService service = loadJsonrpcRequestMessageService(reuse, reload);
    }

    @MethodSource({"argumentsStream"})
    @ParameterizedTest
    void testLoadJsonrpcResponseMessageService(final boolean reuse, final boolean reload) {
        final JsonrpcResponseMessageService service = loadJsonrpcResponseMessageService(reuse, reload);
    }
}
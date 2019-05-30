package com.github.jinahya.jsonrpc2.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
class ResponseObjectTest {

    private static Stream<ResponseObject<Object, ErrorObject.UnknownData>> sourceResponseObjects() {
        return Stream.of(
                new ResponseObject<>()
        );
    }

    @MethodSource({"sourceResponseObjects"})
    @ParameterizedTest
    void testGetResult(final ResponseObject<?, ?> responseObject) {
        final Object result = responseObject.getResult();
    }

    @MethodSource({"sourceResponseObjects"})
    @ParameterizedTest
    void testSetResult(final ResponseObject<?, ?> responseObject) {
        responseObject.setResult(null);
    }

    @MethodSource({"sourceResponseObjects"})
    @ParameterizedTest
    void testSetResultExclusively(final ResponseObject<Object, ? super ErrorObject.UnknownData> responseObject) {
        responseObject.setError(new ErrorObject.UnknownData());
        assertNotNull(responseObject.getError());
        responseObject.setResultExclusively(new Object());
        assertNull(responseObject.getError());
    }
}

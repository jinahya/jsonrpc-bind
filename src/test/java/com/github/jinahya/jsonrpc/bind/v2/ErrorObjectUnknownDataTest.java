package com.github.jinahya.jsonrpc.bind.v2;

import org.junit.jupiter.api.Test;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorObjectUnknownDataTest {

    @Test
    void testGetCode() {
        final long code = new ErrorObject.UnknownData().getCode();
    }

    @Test
    void testSetCode() {
        new ErrorObject.UnknownData().setCode(current().nextLong());
    }

    @Test
    void testGetMessage() {
        final String message = new ErrorObject.UnknownData().getMessage();
        assertNull(message);
    }

    @Test
    void testSetMessage() {
        new ErrorObject.UnknownData().setMessage(null);
        new ErrorObject.UnknownData().setMessage("message");
    }
}

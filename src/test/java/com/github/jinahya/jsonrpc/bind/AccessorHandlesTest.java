package com.github.jinahya.jsonrpc.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class AccessorHandlesTest {

    private static class NoAccessor {

        private int age;
    }

    @Test
    void test1() {
        {
            final NoAccessor object = new NoAccessor();
            final int age = AccessorHandles.get("age", object);
            log.debug("age: {}", age);
            assertEquals(0, age);
            AccessorHandles.set("age", object, 1);
            assertEquals(1, object.age);
        }
        System.gc();
        {
            final NoAccessor object = new NoAccessor();
            final int age = AccessorHandles.get("age", object);
            log.debug("age: {}", age);
            assertEquals(0, age);
            AccessorHandles.set("age", object, 1);
            assertEquals(1, object.age);
        }
    }
}

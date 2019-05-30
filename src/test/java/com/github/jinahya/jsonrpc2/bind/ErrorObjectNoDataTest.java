package com.github.jinahya.jsonrpc2.bind;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorObjectNoDataTest {

    @Test
    void assertDataDataReturnsNull() {
        final ErrorObject<Void> errorObject = new ErrorObject.NoData();
        assertNull(errorObject.getData());
        final Void data;
        try {
            final Constructor<Void> constructor = Void.class.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            data = constructor.newInstance();
        } catch (final Exception roe) {
            return;
        }
        try {
            final Field field = ErrorObject.class.getDeclaredField("data");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(errorObject, data);
        } catch (final Exception e) {
            return;
        }
        assertNull(errorObject.getData());
    }

    @Test
    void assertSetDataDoesNothing() {
        final Void data;
        try {
            final Constructor<Void> constructor = Void.class.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            data = constructor.newInstance();
        } catch (final Exception roe) {
            return;
        }
        final ErrorObject.NoData errorObject = new ErrorObject.NoData();
        assertThrows(IllegalArgumentException.class, () -> errorObject.setData(data));
        assertNull(new ErrorObject.NoData().getData());
    }
}

package com.github.jinahya.jsonrpc.bind.v2;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class AbstractJsonrpcObjectTest<T extends AbstractJsonrpcObject> {

    AbstractJsonrpcObjectTest(final Class<T> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
    }

    @Test
    void testToString() {
        final String string = newInstance().toString();
        assertNotNull(string);
    }

    // --------------------------------------------------------------------------------------------------------------- $
    @Test
    void testIsContextuallyValid() {
        final boolean result = newInstance().isContextuallyValid();
    }

    @Test
    void testToJson() {
        final String json = newInstance().toJson();
    }

    // -----------------------------------------------------------------------------------------------------------------
    T newInstance() {
        try {
            final Constructor<T> constructor = objectClass.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    final Class<T> objectClass;
}
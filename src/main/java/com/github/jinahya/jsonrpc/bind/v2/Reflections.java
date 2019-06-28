package com.github.jinahya.jsonrpc.bind.v2;

import java.lang.reflect.Constructor;

/**
 * A class defines constants and utilities related to Reflection.
 */
class Reflections {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified class.
     *
     * @param clazz the class of newly created object.
     * @param <T>   object type parameter
     * @return a new instance of {@code clazz}.
     */
    static <T> T newInstanceOf(final Class<? extends T> clazz) {
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private Reflections() {
        super();
    }
}

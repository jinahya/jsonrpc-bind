package com.github.jinahya.jsonrpc.bind.v2;

import java.lang.reflect.Constructor;

class Reflections {

    // -----------------------------------------------------------------------------------------------------------------
    static <T> T newInstance(final Class<? extends T> clazz) {
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

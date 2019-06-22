package com.github.jinahya.jsonrpc.bind.v2;

import java.lang.reflect.Constructor;

import static java.util.Objects.requireNonNull;

class _AbstractBuilder<T extends _AbstractBuilder<T, U>, U> {

    // -----------------------------------------------------------------------------------------------------------------
    _AbstractBuilder(final Class<? extends U> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Builds the target instance and returns it.
     *
     * @return an instance of target object.
     */
    public U build() {
        try {
            final Constructor<? extends U> constructor = objectClass.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<? extends U> objectClass;
}

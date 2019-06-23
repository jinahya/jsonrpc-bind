package com.github.jinahya.jsonrpc.bind;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

@Slf4j
class AccessorHandles {

    // -----------------------------------------------------------------------------------------------------------------
    private static MethodHandles.Lookup LOOKUP;

    private static MethodHandles.Lookup lookup() {
        if (LOOKUP == null) {
            LOOKUP = MethodHandles.lookup();
        }
        return LOOKUP;
    }

    private static Map<Field, MethodHandle> SETTER_HANDLES;

    private static Map<Field, MethodHandle> setterHandles() {
        if (SETTER_HANDLES == null) {
            SETTER_HANDLES = new WeakHashMap<>();
        }
        return SETTER_HANDLES;
    }

    private static Map<Field, MethodHandle> GETTER_HANDLES;

    private static Map<Field, MethodHandle> getterHandles() {
        if (GETTER_HANDLES == null) {
            GETTER_HANDLES = new WeakHashMap<>();
        }
        return GETTER_HANDLES;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <T> T get(final Field field, final Object object) {
        final MethodHandle getter = getterHandles().computeIfAbsent(field, k -> {
            log.debug("no handle for the getter ofError {} yet", field);
            if (!k.isAccessible()) {
                k.setAccessible(true);
            }
            try {
                return lookup().unreflectGetter(k);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        });
        try {
//            return (T) getter.invoke(field.getDeclaringClass().cast(object));
            return (T) getter.invoke(object);
//            return (T) getter.invokeExact(object);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    static <T> T get(final String name, final Class<?> current, final Object object) {
        for (final Field field : current.getDeclaredFields()) {
            if (!field.getName().equals(name)) {
                continue;
            }
            return get(field, object);
        }
        final Class<?> superclass = current.getSuperclass();
        if (superclass == null) {
            throw new RuntimeException("no field named '" + name + "'");
        }
        return get(name, superclass, object);
    }

    static <T> T get(final String name, final Object object) {
        return get(name, object.getClass(), object);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static void set(final Field field, final Object object, final Object value) {
        final MethodHandle setter = setterHandles().computeIfAbsent(field, k -> {
            log.debug("no handler for the setter ofError {} yet", field);
            if (!k.isAccessible()) {
                k.setAccessible(true);
            }
            try {
                return lookup().unreflectSetter(k);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        });
        try {
            final Object result = setter.invoke(object, value);
            System.out.println("setter handle invoked: " + result);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    static void set(final String name, final Object object, final Object value, final Class<?> current) {
        for (final Field field : current.getDeclaredFields()) {
            if (!field.getName().equals(name)) {
                continue;
            }
            set(field, object, value);
            return;
        }
        final Class<?> superclass = current.getSuperclass();
        if (superclass == null) {
            throw new RuntimeException("no field named '" + name + "'");
        }
        set(name, object, value, superclass);
    }

    static void set(final String name, final Object object, final Object value) {
        set(name, object, value, object.getClass());
    }
}

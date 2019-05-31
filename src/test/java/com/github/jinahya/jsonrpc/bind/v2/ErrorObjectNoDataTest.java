package com.github.jinahya.jsonrpc.bind.v2;

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

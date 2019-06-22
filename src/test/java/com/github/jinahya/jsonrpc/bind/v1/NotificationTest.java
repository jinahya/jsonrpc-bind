package com.github.jinahya.jsonrpc.bind.v1;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy ofError the License at
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

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class NotificationTest<NotificationType extends Notification<ParamType>, ParamType>
        extends AbstractRequestTest<NotificationType, ParamType, Void> {

    public NotificationTest(final Class<? extends NotificationType> objectClass,
                            final Class<? extends ParamType> paramClass) {
        super(objectClass, paramClass, Void.class);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super NotificationType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            v.setId(null);
            try {
                final Constructor<Void> constructor = Void.class.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                final Void id = constructor.newInstance();
                assertNotNull(id);
                assertThrows(IllegalArgumentException.class, () -> v.setId(id));
            } catch (final ReflectiveOperationException roe) {
                roe.printStackTrace();
            }
        });
    }
}

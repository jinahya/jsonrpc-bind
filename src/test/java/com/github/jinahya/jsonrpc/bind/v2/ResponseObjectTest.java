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

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
 *
 * @param <ObjectType> response object type parameter
 */
@Slf4j
public abstract class ResponseObjectTest<ObjectType extends ResponseObject<?, ?, ?>>
        extends JsonrpcObjectTest<ObjectType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for specified response object class and its type parameter classes.
     *
     * @param responseClass the response object class to test.
     */
    public ResponseObjectTest(final Class<? extends ObjectType> responseClass) {
        super(responseClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated
    protected Method dataSetter() {
        if (dataSetter == null) {
            try {
                dataSetter = ResponseObject.ErrorObject.class.getMethod("setData", Object.class);
                if (!dataSetter.isAccessible()) {
                    dataSetter.setAccessible(true);
                }
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return dataSetter;
    }

    protected MethodHandle dataSetterHandler() {
        if (dataSetterHandler == null) {
            try {
                final Field field = ResponseObject.ErrorObject.class.getDeclaredField("data");
                if (!field.isEnumConstant()) {
                    field.setAccessible(true);
                }
                dataSetterHandler = MethodHandles.publicLookup().unreflectSetter(field);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return dataSetterHandler;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated
    private transient Method dataSetter;

    private transient MethodHandle dataSetterHandler;
}

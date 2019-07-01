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

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * An abstract class for testing subclasses of {@link ResponseObject}.
 *
 * @param <ObjectType> response object type parameter
 * @param <ResultType> result type parameter
 * @param <ErrorType>  error type parameter
 * @param <IdType>     id type parameter
 */
@Slf4j
public abstract class ResponseObjectTest<
        ObjectType extends ResponseObject<ResultType, ErrorType, IdType>, ResultType,
        ErrorType extends ResponseObject.ErrorObject<?>, IdType>
        extends JsonrpcObjectTest<ObjectType, IdType> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for specified response object class and its type parameter classes.
     *
     * @param responseClass the response object class to test.
     * @param resultClass   a class for {@code ResultType} type parameter.
     * @param errorClass    a class for {@code ErrorType} type parameter.
     * @param idClass       a class for {@code IdType} type parameter.
     */
    public ResponseObjectTest(final Class<ObjectType> responseClass, final Class<ResultType> resultClass,
                              final Class<ErrorType> errorClass, final Class<IdType> idClass) {
        super(responseClass, idClass);
        this.resultClass = requireNonNull(resultClass, "resultClass is null");
        this.errorClass = requireNonNull(errorClass, "errorClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            {
                consumer.accept(v);
            }
            {
                v.setResultExclusively(v.getResult());
                v.setErrorExclusively(v.getError());
            }
            {
                assertEquals(v, v);
                assertNotEquals(new Object(), v);
                final ObjectType obj = objectInstance();
                obj.setResult(v.getResult());
                obj.setError(v.getError());
                obj.setId(v.getId());
                assertEquals(obj, v);
                assertEquals(v.hashCode(), obj.hashCode());
            }
            {
                final ObjectType obj = ResponseObject.of(objectClass, v.getResult(), v.getError(), v.getId());
                assertEquals(obj, v);
                assertEquals(v.hashCode(), obj.hashCode());
            }
            {
                final ResponseObject<ResultType, ErrorType, IdType> obj
                        = ResponseObject.of(v.getResult(), v.getError(), v.getId());
                assertEquals(obj, v);
                assertEquals(v.hashCode(), obj.hashCode());
            }
            {
                final ErrorType error = v.getError();
                if (error != null) {
                    assertEquals(error, error);
                    assertNotEquals(new Object(), error);
                    final ErrorType obj = errorInstance();
                    obj.setCode(error.getCode());
                    obj.setMessage(error.getMessage());
                    try {
                        dataSetterHandler().invoke(obj, error.getData());
                    } catch (final Throwable t) {
                        throw new RuntimeException(t);
                    }
                    assertEquals(error, obj);
                    assertEquals(error.hashCode(), obj.hashCode());
                }
            }
            {
                final ErrorType error = v.getError();
                if (error != null) {
                    final ErrorObject<Object> obj
                            = ErrorObject.of(error.getCode(), error.getMessage(), error.getData());
                    assertEquals(error, obj);
                    assertEquals(error.hashCode(), obj.hashCode());
                }
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of {@link ErrorType}.
     *
     * @return a new instance of {@link ErrorType}.
     * @see #errorClass
     */
    protected ErrorType errorInstance() {
        try {
            final Constructor<? extends ErrorType> constructor = errorClass.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
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
    protected final Class<ResultType> resultClass;

    protected final Class<ErrorType> errorClass;

    @Deprecated
    private transient Method dataSetter;

    private transient MethodHandle dataSetterHandler;
}

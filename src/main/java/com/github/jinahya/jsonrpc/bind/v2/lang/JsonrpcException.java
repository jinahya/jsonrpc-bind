package com.github.jinahya.jsonrpc.bind.v2.lang;

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;
import com.github.jinahya.jsonrpc.bind.v2.miscellaneous.MappedData;
import com.github.jinahya.jsonrpc.bind.v2.miscellaneous.MappedThrowable;

import java.lang.reflect.Constructor;

public class JsonrpcException extends RuntimeException {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property for {@code $.error.code} of {@link com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject}.
     */
    public static final String PROPERTY_NAME_CODE = "code";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()}
     *                method.
     * @param cause   the cause (which is saved for later retrieval by the {@link Throwable#getCause()}  method). (A
     *                {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param code    a value for {@value #PROPERTY_NAME_CODE} property.
     */
    public JsonrpcException(final String message, final Throwable cause, final int code) {
        super(message, cause, true, true);
        this.code = code;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public <T extends ResponseObject.ErrorObject<? super MappedData>> T toErrorObject(final Class<? extends T> clazz) {
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            final T instance = constructor.newInstance();
            instance.setCode(code);
            instance.setMessage(getMessage());
            // TODO: 6/28/2019 request?
            instance.setData(MappedData.of(null, MappedThrowable.of(this)));
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------------------ code

    /**
     * Returns the current value of {@value #PROPERTY_NAME_CODE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_CODE} property.
     */
    public int getCode() {
        return code;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final int code;
}

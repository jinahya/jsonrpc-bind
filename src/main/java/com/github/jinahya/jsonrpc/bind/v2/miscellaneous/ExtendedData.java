package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcObject;
import com.github.jinahya.jsonrpc.bind.v2.RequestObject;
import com.github.jinahya.jsonrpc.bind.v2.lang.JsonrpcException;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * A class for holding an instance of {@link RequestObject} and an instance of {@link Throwable}.
 */
public class ExtendedData {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.error.data.request}.
     */
    public static final String PROPERTY_NAME_REQUEST = "request";

    /**
     * The property name for {@code $.error.data.thrown}.
     */
    public static final String PROPERTY_NAME_THROWN = "thrown";

    // -----------------------------------------------------------------------------------------------------------------
    public static <T extends ExtendedData> T of(final Class<? extends T> clazz, final JsonrpcObject<?> request,
                                                final MappedThrowable thrown) {
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            final T instance = constructor.newInstance();
            instance.setRequest(request);
            instance.setThrown(thrown);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    public static ExtendedData of(final JsonrpcObject<?> request, final MappedThrowable thrown) {
        return of(ExtendedData.class, request, thrown);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T extends ExtendedData> T of(final Class<? extends T> clazz, final JsonrpcObject<?> request,
                                                final JsonrpcException thrown) {
        return of(clazz, request, MappedThrowable.of(thrown));
    }

    public static ExtendedData of(final JsonrpcObject<?> request, final JsonrpcException thrown) {
        return of(ExtendedData.class, request, thrown);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public ExtendedData() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "request=" + request
               + ",thrown=" + thrown
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExtendedData)) {
            return false;
        }
        final ExtendedData that = (ExtendedData) obj;
        return Objects.equals(getRequest(), that.getRequest()) && Objects.equals(getThrown(), that.getThrown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), getThrown());
    }

    // --------------------------------------------------------------------------------------------------------- request
    public JsonrpcObject<?> getRequest() {
        return request;
    }

    public void setRequest(final JsonrpcObject<?> request) {
        this.request = request;
    }

    // ---------------------------------------------------------------------------------------------------------- thrown
    public MappedThrowable getThrown() {
        return thrown;
    }

    public void setThrown(final MappedThrowable thrown) {
        this.thrown = thrown;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonrpcObject<?> request;

    private MappedThrowable thrown;
}

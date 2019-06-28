package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import com.github.jinahya.jsonrpc.bind.v2.RequestObject;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * A class for holding an instance of {@link RequestObject} and an instance of {@link Throwable}.
 */
public class MappedData {

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

    /**
     * Creates a new instance of specified class whose properties are set with specified values.
     *
     * @param clazz   the class of new instance.
     * @param request a value for {@value #PROPERTY_NAME_REQUEST} property.
     * @param thrown  a value for {@value #PROPERTY_NAME_THROWN} property.
     * @param <T>     instance type parameter.
     * @return a new instance of {@code clazz}.
     * @see #of(RequestObject, MappedThrowable)
     */
    public static <T extends MappedData> T of(final Class<? extends T> clazz, final RequestObject<?, ?> request,
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

    /**
     * Creates a new instance of specified class whose properties are set with specified values.
     *
     * @param request a value for {@value #PROPERTY_NAME_REQUEST} property.
     * @param thrown  a value for {@value #PROPERTY_NAME_THROWN} property.
     * @return a new instance of {@code clazz}.
     * @see #of(RequestObject, MappedThrowable)
     */
    public static MappedData of(final RequestObject<?, ?> request, final MappedThrowable thrown) {
        return of(MappedData.class, request, thrown);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public MappedData() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a spring representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "request=" + request
               + ",thrown=" + thrown
               + "}";
    }

    /**
     * Indicates whether some other objects is "equals" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedData)) {
            return false;
        }
        final MappedData that = (MappedData) obj;
        return Objects.equals(getRequest(), that.getRequest()) && Objects.equals(getThrown(), that.getThrown());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), getThrown());
    }

    // --------------------------------------------------------------------------------------------------------- request

    /**
     * Returns the current value of {@value #PROPERTY_NAME_REQUEST} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_REQUEST} property.
     */
    public RequestObject<?, ?> getRequest() {
        return request;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_REQUEST} property with specified value.
     *
     * @param request new value for {@value #PROPERTY_NAME_REQUEST} property.
     */
    public void setRequest(final RequestObject<?, ?> request) {
        this.request = request;
    }

    // ---------------------------------------------------------------------------------------------------------- thrown

    /**
     * Returns the current value of {@value #PROPERTY_NAME_THROWN} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_THROWN} property.
     */
    public MappedThrowable getThrown() {
        return thrown;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_THROWN} property with specified value.
     *
     * @param thrown new value for {@value #PROPERTY_NAME_THROWN} property.
     */
    public void setThrown(final MappedThrowable thrown) {
        this.thrown = thrown;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private RequestObject<?, ?> request;

    private MappedThrowable thrown;
}

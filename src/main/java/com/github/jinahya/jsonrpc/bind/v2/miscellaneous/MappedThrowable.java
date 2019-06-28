package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * A class for binding {@link Throwable}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class MappedThrowable {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.error.data.thrown.message} mapped from the result of {@link
     * Throwable#getMessage()} method.
     */
    public static final String PROPERTY_NAME_MESSAGE = "message";

    /**
     * The property name for {@code $.error.data.thrown.suppressed[*]} mapped from the result of {@link
     * Throwable#getSuppressed()} method.
     */
    public static final String PROPERTY_NAME_SUPPRESSED = "suppressed";

    /**
     * The property name for {@code $.error.data.thrown.cause} mapped from the result of {@link Throwable#getCause()}
     * method.
     */
    public static final String PROPERTY_NAME_CAUSE = "cause";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified class whose perperties are set with specified values.
     *
     * @param clazz     the class of newly created object.
     * @param throwable a throwable ot map.
     * @param <T>       object type parameter
     * @return a new object of specified class
     */
    public static <T extends MappedThrowable> T of(final Class<? extends T> clazz, final Throwable throwable) {
        if (clazz == null) {
            throw new NullPointerException("clazz is null");
        }
        if (throwable == null) {
            throw new NullPointerException("throwable is null");
        }
        try {
            final Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            final T instance = constructor.newInstance();
            instance.setMessage(throwable.getMessage());
            instance.setSuppressed(stream(throwable.getSuppressed()).map(MappedThrowable::of).collect(toList()));
            ofNullable(throwable.getCause()).map(MappedThrowable::of).ifPresent(instance::setCause);
            return instance;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * Creates a new instance of specified throwable.
     *
     * @param throwable the throwable.
     * @return a new instance.
     * @see #of(Class, Throwable)
     */
    public static MappedThrowable of(final Throwable throwable) {
        return of(MappedThrowable.class, throwable);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public MappedThrowable() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "message=" + message
               + ",suppressed=" + suppressed
               + ",cause=" + cause
               + "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedThrowable)) {
            return false;
        }
        final MappedThrowable that = (MappedThrowable) obj;
        return Objects.equals(getMessage(), that.getMessage())
               && Objects.equals(getSuppressed(), that.getSuppressed())
               && Objects.equals(getCause(), that.getCause());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getSuppressed(), getCause());
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Returns the current value of {@value #PROPERTY_NAME_MESSAGE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_MESSAGE} property.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_MESSAGE} property with specified value.
     *
     * @param message new value for {@value #PROPERTY_NAME_MESSAGE} property.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    // ------------------------------------------------------------------------------------------------------ suppressed

    /**
     * Returns the current value of {@value #PROPERTY_NAME_SUPPRESSED} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_SUPPRESSED} property.
     */
    public List<MappedThrowable> getSuppressed() {
        return suppressed;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_SUPPRESSED} property with specified value.
     *
     * @param suppressed new value for {@value #PROPERTY_NAME_SUPPRESSED} property.
     */
    public void setSuppressed(final List<MappedThrowable> suppressed) {
        this.suppressed = suppressed;
    }

    // ----------------------------------------------------------------------------------------------------------- cause

    /**
     * Returns the current value of {@value #PROPERTY_NAME_CAUSE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_CAUSE} property.
     */
    public MappedThrowable getCause() {
        return cause;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_CAUSE} property with specified value.
     *
     * @param cause new value for {@value #PROPERTY_NAME_CAUSE} property.
     */
    public void setCause(final MappedThrowable cause) {
        this.cause = cause;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String message;

    private List<MappedThrowable> suppressed;

    private MappedThrowable cause;
}

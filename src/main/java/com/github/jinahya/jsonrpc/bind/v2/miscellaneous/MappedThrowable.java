package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

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
     * Creates a new instance of specified throwable.
     *
     * @param throwable the throwable.
     * @return a new instance.
     */
    public static MappedThrowable of(final Throwable throwable) {
        final MappedThrowable instance = new MappedThrowable();
        instance.type = throwable.getClass().getName();
        instance.message = throwable.getMessage();
        final Throwable[] suppressed = throwable.getSuppressed();
        if (suppressed.length > 0) {
            instance.suppressed = stream(suppressed).map(MappedThrowable::of).collect(toList());
        }
        ofNullable(throwable.getCause()).map(MappedThrowable::of).ifPresent(instance::setCause);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public MappedThrowable() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
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
    private String type;

    private String message;

    private List<MappedThrowable> suppressed;

    private MappedThrowable cause;
}

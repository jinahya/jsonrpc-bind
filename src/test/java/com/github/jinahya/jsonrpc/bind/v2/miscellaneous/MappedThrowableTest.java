package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static com.github.jinahya.jsonrpc.bind.JacksonTests.OBJECT_MAPPER;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Slf4j
class MappedThrowableTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static class Extended extends MappedThrowable {

        @Setter
        @Getter
        private String type = "extended";
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testOf() throws IOException {
        {
            final MappedThrowable instance = MappedThrowable.of(new Throwable("message"));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final MappedThrowable instance = MappedThrowable.of(new Throwable("message", new Throwable("cause")));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final Throwable throwable = new Throwable("throwable", new Throwable("cause"));
            final List<Throwable> suppressed = range(0, 3)
                    .mapToObj(i -> new Throwable("suppressed" + i, new Throwable("sub")))
                    .collect(toList());
            try {
                final Field field = Throwable.class.getDeclaredField("suppressedExceptions");
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(throwable, suppressed);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
            final MappedThrowable instance = MappedThrowable.of(throwable);
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
    }

    @Test
    void testOfWithExtendedClass() throws IOException {
        {
            final MappedThrowable instance = MappedThrowable.of(Extended.class, new Throwable("message"));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final MappedThrowable instance = MappedThrowable.of(
                    Extended.class, new Throwable("message", new Throwable("cause")));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final Throwable throwable = new Throwable("throwable", new Throwable("cause"));
            final List<Throwable> suppressed = range(0, 3)
                    .mapToObj(i -> new Throwable("suppressed" + i, new Throwable("sub")))
                    .collect(toList());
            try {
                final Field field = Throwable.class.getDeclaredField("suppressedExceptions");
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(throwable, suppressed);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
            final MappedThrowable instance = MappedThrowable.of(Extended.class, throwable);
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
    }
}

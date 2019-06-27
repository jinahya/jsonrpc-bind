package com.github.jinahya.jsonrpc.bind.v2;

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject.ErrorObject.BoundData.BoundThrowable;
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
class ResponseObjectBoundDataBoundThrowableTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static class Extended extends ResponseObject.ErrorObject.BoundData.BoundThrowable {

        @Setter
        @Getter
        private String type = "extended";
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testOf() throws IOException {
        {
            final BoundThrowable instance = BoundThrowable.of(new Throwable("message"));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final BoundThrowable instance = BoundThrowable.of(new Throwable("message", new Throwable("cause")));
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
            final BoundThrowable instance = BoundThrowable.of(throwable);
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
    }

    @Test
    void testOfWithExtendedClass() throws IOException {
        {
            final BoundThrowable instance = BoundThrowable.of(Extended.class, new Throwable("message"));
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
        {
            final BoundThrowable instance = BoundThrowable.of(
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
            final BoundThrowable instance = BoundThrowable.of(Extended.class, throwable);
            log.debug("jackson: {}", OBJECT_MAPPER.writeValueAsString(instance));
        }
    }
}

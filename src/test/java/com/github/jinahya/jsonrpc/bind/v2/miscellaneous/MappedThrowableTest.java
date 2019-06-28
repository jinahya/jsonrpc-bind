package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.github.jinahya.jsonrpc.bind.JacksonTests.OBJECT_MAPPER;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Slf4j
class MappedThrowableTest {

    // -----------------------------------------------------------------------------------------------------------------
    static Throwable randomThrowable() {
        final Throwable throwable = new Throwable("throwable", new Throwable("cause"));
        final List<Throwable> suppressed = range(0, 3)
                .mapToObj(i -> new Throwable("suppressed", new Throwable("cause")))
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
        log.debug("suppressed: {}", Arrays.toString(throwable.getSuppressed()));
        return throwable;
    }

    static Stream<Throwable> sourceThrowables() {
        return range(0, 1).mapToObj(i -> randomThrowable());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"sourceThrowables"})
    @ParameterizedTest
    void testOf(final Throwable throwable) throws IOException {
        final MappedThrowable instance = MappedThrowable.of(throwable);
        log.debug("instance: {}", instance);
        log.debug("instance.suppressed: {}", instance.getSuppressed());
        log.debug("string: {}", OBJECT_MAPPER.writeValueAsString(instance));
        log.debug("pretty: {}", OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(instance));
    }
}

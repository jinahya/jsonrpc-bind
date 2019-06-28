package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.Setter;
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
    private static class ExtendedMappedThrowable extends MappedThrowable {

        static ExtendedMappedThrowable of(final Throwable thrown, final String type) {
            final ExtendedMappedThrowable instance = of(ExtendedMappedThrowable.class, thrown);
            instance.setType(type);
            return instance;
        }

        @Setter
        @Getter
        private String type;
    }

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
        final String string = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
        log.debug("string: \n{}", string);
    }

    @MethodSource({"sourceThrowables"})
    @ParameterizedTest
    void testOfWithExtendedClass(final Throwable throwable) throws IOException {
        final MappedThrowable instance = MappedThrowable.of(ExtendedMappedThrowable.class, throwable);
        log.debug("instance: {}", instance);
        final DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        final ObjectWriter writer = OBJECT_MAPPER.writer(prettyPrinter);
        final String string = writer.writeValueAsString(instance);
        log.debug("string: \n{}", string);
    }
}

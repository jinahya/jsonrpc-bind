package com.github.jinahya.jsonrpc.bind;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc.bind.JsonbTests.JSONB;

@Slf4j
public class JsonbTest {

    public static class Some<T, U extends Some.SomeError<?>, V> {

        public static class SomeError<W> {

            @Setter
            @Getter
            private int code;

            @Setter
            @Getter
            private W data;
        }

        @Setter
        @Getter
        private T t;

        @Setter
        @Getter
        private U u;

        @Setter
        @Getter
        private V v;
    }

    public static class VoidError extends Some.SomeError<Void> {

    }

    public static class VoidSome extends Some<Void, VoidError, Void> {

    }

//    public static class VoidSome extends Some<Void, Some.SomeError<Void>, Void> {
//
//    }

    @Test
    void test() throws IOException {
        final String a = "{\"t\": null, \"u\": {\"code\":1}, \"v\": null}";
        final String b = "{\"t\": null, \"u\": null, \"v\": null}";
//        final Some<Void, Some.SomeError<Void>, Void> value = JSONB.fromJson(b, VoidSome.class);
        final Some<Void, VoidError, Void> value = JSONB.fromJson(a, VoidSome.class);
        log.debug("value: {}", value);
    }
}

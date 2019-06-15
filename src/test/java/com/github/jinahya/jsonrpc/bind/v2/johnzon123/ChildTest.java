package com.github.jinahya.jsonrpc.bind.v2.johnzon123;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.jsonrpc.bind.v2.JsonbUtils.JSONB;

@Slf4j
public class ChildTest {

    //    @Disabled
    @Test
    void test() {
        final Child child = new Child();
        child.setOptions(new SomeOptions());
        final String string = JSONB.toJson(child);
        log.debug("string: {}", string);
        final Child value = JSONB.fromJson(string, Child.class);
        log.debug("value: {}", value);
    }
}

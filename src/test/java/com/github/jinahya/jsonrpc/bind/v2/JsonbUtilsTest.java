package com.github.jinahya.jsonrpc.bind.v2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

@Slf4j
class JsonbUtilsTest {

    @Test
    void test() {
        log.debug("JSONB: {}", JsonbUtils.JSONB);
    }

    @Test
    void test1() {
        log.debug("Jsonb: {}", JsonbBuilder.create());
    }
}

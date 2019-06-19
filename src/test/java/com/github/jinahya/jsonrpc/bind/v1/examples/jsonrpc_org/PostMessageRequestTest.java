package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

import com.github.jinahya.jsonrpc.bind.v1.RequestTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class PostMessageRequestTest extends RequestTest<PostMessageRequest, String, Long> {

    PostMessageRequestTest() {
        super(PostMessageRequest.class, String.class, Long.class);
    }

    @Test
    void postMessage_01_request() throws IOException {
        withResource(
                "postMessage_01_request.json",
                v -> {
                    assertEquals("postMessage", v.getMethod());
                    assertIterableEquals(singletonList("Hello all!"), v.getParams());
                    assertEquals(99L, (long) v.getId());
                }
        );
    }

    @Test
    void postMessage_02_request() throws IOException {
        withResource(
                "postMessage_02_request.json",
                v -> {
                    assertEquals("postMessage", v.getMethod());
                    assertIterableEquals(singletonList("I have a question:"), v.getParams());
                    assertEquals(101L, (long) v.getId());
                }
        );
    }
}

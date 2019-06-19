package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

import com.github.jinahya.jsonrpc.bind.v1.ResponseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PostMessageResponseTest extends ResponseTest<PostMessageResponse, Integer, Object, Long> {

    PostMessageResponseTest() {
        super(PostMessageResponse.class, Integer.class, Object.class, Long.class);
    }

    @Override
    protected void withResource(final String name, final Consumer<? super PostMessageResponse> consumer)
            throws IOException {
        super.withResource(name, v -> {
            consumer.accept(v);
            final PostMessageRequest request = new PostMessageRequest();
            v.copyIdTo(request);
            assertEquals(v.getId(), request.getId());
        });
    }

    @Test
    void postMessage_01_response() throws IOException {
        withResource(
                "postMessage_01_response.json",
                v -> {
                    assertEquals(1, (int) v.getResult());
                    assertNull(v.getError());
                    assertEquals(99L, (long) v.getId());
                }
        );
    }

    @Test
    void postMessage_02_response() throws IOException {
        withResource(
                "postMessage_02_response.json",
                v -> {
                    assertEquals(1, (int) v.getResult());
                    assertNull(v.getError());
                    assertEquals(101L, (long) v.getId());
                }
        );
    }
}

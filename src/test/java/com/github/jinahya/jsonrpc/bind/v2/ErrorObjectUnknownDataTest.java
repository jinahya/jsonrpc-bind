package com.github.jinahya.jsonrpc.bind.v2;

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

import org.junit.jupiter.api.Test;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorObjectUnknownDataTest {

    @Test
    void testGetCode() {
        final long code = new ErrorObject.UnknownData().getCode();
    }

    @Test
    void testSetCode() {
        new ErrorObject.UnknownData().setCode(current().nextLong());
    }

    @Test
    void testGetMessage() {
        final String message = new ErrorObject.UnknownData().getMessage();
        assertNull(message);
    }

    @Test
    void testSetMessage() {
        new ErrorObject.UnknownData().setMessage(null);
        new ErrorObject.UnknownData().setMessage("message");
    }
}

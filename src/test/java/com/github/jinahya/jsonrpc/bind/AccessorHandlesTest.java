package com.github.jinahya.jsonrpc.bind;

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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class AccessorHandlesTest {

    private static class NoAccessor {

        private int age;
    }

    @Test
    void test1() {
        {
            final NoAccessor object = new NoAccessor();
            final int age = AccessorHandles.get("age", object);
            log.debug("age: {}", age);
            assertEquals(0, age);
            AccessorHandles.set("age", object, 1);
            assertEquals(1, object.age);
        }
        System.gc();
        {
            final NoAccessor object = new NoAccessor();
            final int age = AccessorHandles.get("age", object);
            log.debug("age: {}", age);
            assertEquals(0, age);
            AccessorHandles.set("age", object, 1);
            assertEquals(1, object.age);
        }
    }
}

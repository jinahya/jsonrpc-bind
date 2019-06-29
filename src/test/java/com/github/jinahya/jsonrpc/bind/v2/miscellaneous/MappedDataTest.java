package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

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

import com.github.jinahya.jsonrpc.bind.v2.RequestObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

class MappedDataTest {

    // -----------------------------------------------------------------------------------------------------------------
    private static class ExtendedMappedData extends MappedData {

        static ExtendedMappedData of(final RequestObject<?, ?> request, final MappedThrowable thrown,
                                     final String type) {
            final ExtendedMappedData instance = MappedData.of(ExtendedMappedData.class, request, thrown);
            instance.setType(type);
            return instance;
        }

        // -------------------------------------------------------------------------------------------------------------
        @Setter
        @Getter
        private String type;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testOf() {
        final MappedData instance = MappedData.of(new RequestObject<>(), MappedThrowable.of(new Throwable("thrown")));
    }
}

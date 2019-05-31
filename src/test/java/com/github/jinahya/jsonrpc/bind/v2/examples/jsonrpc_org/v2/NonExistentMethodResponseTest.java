package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

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

import com.github.jinahya.jsonrpc.bind.v2.JsonTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class NonExistentMethodResponseTest {

    @Test
    void jackson01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/non_existent_method_01_response.json",
                NonExistentMethodResponse.class,
                v -> {
                    final boolean errorCodeReservedForPredefinedErrors
                            = v.getError().isCodeReservedForPredefinedErrors();
                    final boolean errorCodeReservedForImplementationDefinedServerErrors
                            = v.getError().isCodeReservedForImplementationDefinedServerErrors();
                }
        );
    }

    @Test
    void jsonb01() throws IOException {
        JsonTests.doWithResource(
                "/examples/jsonrpc_org/v2/non_existent_method_01_response.json",
                NonExistentMethodResponse.class,
                v -> {
                    final boolean errorCodeReservedForPredefinedErrors
                            = v.getError().isCodeReservedForPredefinedErrors();
                    final boolean errorCodeReservedForImplementationDefinedServerErrors
                            = v.getError().isCodeReservedForImplementationDefinedServerErrors();
                }
        );
    }
}

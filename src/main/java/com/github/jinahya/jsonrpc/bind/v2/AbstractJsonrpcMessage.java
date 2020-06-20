package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class AbstractJsonrpcMessage extends AbstractJsonrpcObject implements JsonrpcMessage {

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jsonrpc=" + jsonrpc
               + "}";
    }

    // --------------------------------------------------------------------------------------------------------- jsonrpc
    @Override
    public @Pattern(regexp = "2\\.0") @NotNull String getJsonrpc() {
        return jsonrpc;
    }

    @Override
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jsonrpc = PROPERTY_VALUE_JSONRPC;
}

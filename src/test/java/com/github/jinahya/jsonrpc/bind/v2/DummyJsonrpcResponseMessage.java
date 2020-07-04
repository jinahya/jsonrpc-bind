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

import javax.validation.constraints.AssertTrue;
import java.math.BigInteger;
import java.util.List;

public class DummyJsonrpcResponseMessage
        extends AbstractJsonrpcResponseMessage {

    // -------------------------------------------------------------------------------------------------------- $.result
    @Override
    public boolean hasResult() {
        return false;
    }

    @Override
    public <T> List<T> getResultAsArray(Class<T> elementClass) {
        return null;
    }

    @Override
    public void setResultAsArray(List<?> result) {

    }

    @Override
    public <T> T getResultAsObject(Class<T> objectClass) {
        return null;
    }

    @Override
    public void setResultAsObject(Object result) {

    }

    // --------------------------------------------------------------------------------------------------------- $.error
    @Override
    public boolean hasError() {
        return false;
    }

    @Override
    public <T extends JsonrpcResponseMessageError> T getErrorAs(Class<T> clazz) {
        return null;
    }

    @Override
    public void setErrorAs(JsonrpcResponseMessageError error) {

    }

    @Override
    public JsonrpcResponseMessageError getErrorAsDefaultType() {
        return null;
    }

    // ------------------------------------------------------------------------------------------------------------ $.id
    @Override
    public boolean hasId() {
        return false;
    }

    @Override
    public @AssertTrue boolean isIdContextuallyValid() {
        return false;
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void setIdAsString(String id) {

    }

    @Override
    public BigInteger getIdAsNumber() {
        return null;
    }

    @Override
    public void setIdAsNumber(BigInteger id) {

    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toJson() {
        return null;
    }
}

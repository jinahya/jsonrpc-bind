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

import com.github.jinahya.jsonrpc.bind.JsonrpcBindException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static java.util.Objects.requireNonNull;

public interface JsonrpcMessageService<T extends JsonrpcMessage> {

    /**
     * Reads an instance from specified source.
     *
     * @param source the source from which the instance is read.
     * @return a new instance.
     */
    T fromJson(Object source);

    /**
     * Parse an instance from specified JSON string.
     *
     * @param string the JSON string.
     * @return a new instance.
     * @implNote Default implementation invokes {@link #fromJson(Object)} method with an instance of {@link
     * StringReader} created with {@code string}.
     */
    default T fromJsonString(final String string) {
        requireNonNull(string, "string is null");
        try (StringReader source = new StringReader(string)) {
            return fromJson(source);
        }
    }

    /**
     * Writes specified message to specified target.
     *
     * @param message the message to write.
     * @param target  the target which the {@code message} is written.
     */
    void toJson(T message, Object target);

    /**
     * Prints given message to a JSON string.
     *
     * @param message the message to print.
     * @return a JSON string.
     * @implNote The default implementation invokes {@link #toJson(JsonrpcMessage, Object)} method with given {@code
     * message} and an instance of {@link StringWriter} and returns the value written to the writer.
     */
    default String toJsonString(final T message) {
        requireNonNull(message, "message is null");
        try {
            try (StringWriter target = new StringWriter()) {
                toJson(message, target);
                return target.toString();
            }
        } catch (final IOException ioe) {
            throw new JsonrpcBindException(ioe);
        }
    }
}

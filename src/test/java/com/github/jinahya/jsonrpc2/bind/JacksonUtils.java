package com.github.jinahya.jsonrpc2.bind;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class JacksonUtils {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T readValue(final String resourceName, final Class<? extends T> valueType)
            throws IOException {
        try (InputStream resource = JacksonUtils.class.getResourceAsStream(resourceName)) {
            logger.debug("resource: {}", resource);
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource, valueType);
        }
    }
}

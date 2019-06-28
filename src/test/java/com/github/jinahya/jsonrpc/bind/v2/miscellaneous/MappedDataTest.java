package com.github.jinahya.jsonrpc.bind.v2.miscellaneous;

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
        private String type = "extended";
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testOf() {
        final MappedData instance = MappedData.of(new RequestObject<>(), MappedThrowable.of(new Throwable("thrown")));
    }
}

package com.github.jinahya.jsonrpc.bind.v2.calculator;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class BoundThrowable {

    public static BoundThrowable of(final Throwable throwable) {
        if (throwable == null) {
            throw new NullPointerException("throwable is null");
        }
        final BoundThrowable instance = new BoundThrowable();
        instance.message = throwable.getMessage();
        instance.suppressed = Arrays.stream(throwable.getSuppressed()).map(BoundThrowable::of).collect(toList());
        instance.cause = ofNullable(throwable.getCause()).map(BoundThrowable::of).orElse(null);
        return instance;
    }

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private List<BoundThrowable> suppressed;

    @Setter
    @Getter
    private BoundThrowable cause;
}

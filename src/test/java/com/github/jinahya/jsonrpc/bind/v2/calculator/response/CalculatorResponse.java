package com.github.jinahya.jsonrpc.bind.v2.calculator.response;

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;

import java.math.BigDecimal;

public class CalculatorResponse extends ResponseObject<String, BigDecimal, CalculatorResponseError> {

    public static CalculatorResponse of(BigDecimal result) {
        final CalculatorResponse instance = new CalculatorResponse();
        instance.setResult(result);
        return instance;
    }

    public static CalculatorResponse of(final CalculatorResponseError error) {
        final CalculatorResponse instance = new CalculatorResponse();
        instance.setError(error);
        return instance;
    }
}

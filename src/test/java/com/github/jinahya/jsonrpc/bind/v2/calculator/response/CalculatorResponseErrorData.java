package com.github.jinahya.jsonrpc.bind.v2.calculator.response;

import com.github.jinahya.jsonrpc.bind.v2.calculator.request.CalculatorRequest;
import lombok.Getter;
import lombok.Setter;

public class CalculatorResponseErrorData {

    public static CalculatorResponseErrorData of(final CalculatorRequest calculatorRequest,
                                                 final ArithmeticException arithmeticException) {
        final CalculatorResponseErrorData instance = new CalculatorResponseErrorData();
        instance.calculatorRequest = calculatorRequest;
        instance.arithmeticException = arithmeticException;
        return instance;
    }

    @Setter
    @Getter
    private CalculatorRequest calculatorRequest;

    @Setter
    @Getter
    private ArithmeticException arithmeticException;
}

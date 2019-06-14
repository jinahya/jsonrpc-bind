package com.github.jinahya.jsonrpc.bind.v2.calculator.response;

import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;

public class CalculatorResponseError extends ResponseObject.ErrorObject<CalculatorResponseErrorData> {

    public static CalculatorResponseError of(final long code, final String message,
                                             final CalculatorResponseErrorData data) {
        final CalculatorResponseError instance = new CalculatorResponseError();
        instance.setCode(code);
        instance.setMessage(message);
        instance.setData(data);
        return instance;
    }
}

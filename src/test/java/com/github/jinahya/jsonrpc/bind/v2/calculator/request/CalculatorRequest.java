package com.github.jinahya.jsonrpc.bind.v2.calculator.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jinahya.jsonrpc.bind.v2.RequestObject;

import javax.validation.constraints.NotNull;

public class CalculatorRequest extends RequestObject<String, JsonNode> {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String METHOD_ADD = "add";

    public static final String METHOD_SUBTRACT = "subtract";

    public static final String METHOD_MULTIPLY = "multiply";

    public static final String METHOD_DIVIDE = "divide";

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Override
    public JsonNode getParams() {
        return super.getParams();
    }
}

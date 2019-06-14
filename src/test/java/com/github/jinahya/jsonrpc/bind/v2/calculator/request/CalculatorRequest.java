package com.github.jinahya.jsonrpc.bind.v2.calculator.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jinahya.jsonrpc.bind.v2.RequestObject;

import javax.validation.constraints.NotNull;

public class CalculatorRequest extends RequestObject<String, JsonNode> {

    @NotNull
    @Override
    public JsonNode getParams() {
        return super.getParams();
    }
}

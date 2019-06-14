package com.github.jinahya.jsonrpc.bind.v2.calculator;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CalculationResult {

    @NotNull
    @Setter
    @Getter
    private BigDecimal value;
}

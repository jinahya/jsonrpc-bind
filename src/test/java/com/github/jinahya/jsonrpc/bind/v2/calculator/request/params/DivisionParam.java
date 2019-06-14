package com.github.jinahya.jsonrpc.bind.v2.calculator.request.params;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionParam implements CalculatorParams {

    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    @NotNull
    @Setter
    @Getter
    private BigDecimal dividend;

    @NotNull
    @Setter
    @Getter
    private BigDecimal divisor;

    @NotNull
    @Setter
    @Getter
    private RoundingMode roundingMode = DEFAULT_ROUNDING_MODE;
}

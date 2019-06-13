package com.github.jinahya.jsonrpc.bind.v2.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorImpl implements Calculator {

    @Override
    public BigDecimal add(final BigDecimal addend, final BigDecimal augend) {
        return addend.add(augend);
    }

    @Override
    public BigDecimal subtract(final BigDecimal minuend, final BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    @Override
    public BigDecimal multiply(final BigDecimal multiplier, final BigDecimal multiplicand) {
        return multiplier.multiply(multiplicand);
    }

    @Override
    public BigDecimal divide(final BigDecimal dividend, final BigDecimal divisor) {
        return dividend.divide(divisor);
    }

    @Override
    public BigDecimal divide(final BigDecimal dividend, final BigDecimal divisor, final RoundingMode roundingMode) {
        return dividend.divide(divisor, roundingMode);
    }
}

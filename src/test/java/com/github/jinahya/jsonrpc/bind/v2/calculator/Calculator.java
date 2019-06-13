package com.github.jinahya.jsonrpc.bind.v2.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * .
 *
 * @see <a href="https://www.factmonster.com/math-science/mathematics/terms-used-in-equations">Terms Used in
 * Equations</a>
 */
public interface Calculator {

    /**
     * @param addend
     * @param augend
     * @return
     * @see <a href="http://mathworld.wolfram.com/Addition.html">Addition (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Augend.html">Augend (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Addend.html">Addend (MathWorld)</a>
     */
    // argend + addend -> apply +addend to augend
    default BigDecimal add(final BigDecimal addend, final BigDecimal augend) {
        return addend.add(augend);
    }

    /**
     * .
     *
     * @param minuend
     * @param subtrahend
     * @return
     * @see <a href="http://mathworld.wolfram.com/Subtraction.html">Subtraction (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Minuend.html">Minuend (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Subtrahend.html">Subtrahend (MathWorld)</a>
     */
    // A - B = A + (-B) => apply +(-B) to A
    // The difference from minuend to subtrahend
    default BigDecimal subtract(final BigDecimal minuend, final BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    /**
     * Returns the value of specified multiplicand multiplied by specified multiplier.
     *
     * @param multiplier   the multiplier.
     * @param multiplicand the multiplicand.
     * @return the value of {@code multiplicand} multiplied by {@code multiplier}.
     * @see <a href="http://mathworld.wolfram.com/Multiplication.html">Multiplication (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Multiplier.html">Multiplier (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Multiplicand.html">Multiplicand (MathWorld)</a>
     */
    // multiplier times multiplicand
    default BigDecimal multiply(final BigDecimal multiplier, final BigDecimal multiplicand) {
        return multiplier.multiply(multiplicand);
    }

    /**
     * Returns {@code dividend / divisor}.
     *
     * @param dividend the dividend.
     * @param divisor  the divisor.
     * @return the result of {@code dividend / divisor}.
     * @see <a href="http://mathworld.wolfram.com/Division.html">Division (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Dividend.html">Dividend (MathWorld)</a>
     * @see <a href="http://mathworld.wolfram.com/Divisor.html">Divisor (MathWorld)</a>
     * @deprecated Use {@link #divide(BigDecimal, BigDecimal, RoundingMode)}
     */
    // the dividend is divided by the divisor to get a quotient
    @Deprecated
    default BigDecimal divide(final BigDecimal dividend, final BigDecimal divisor) {
        return dividend.divide(divisor);
    }

    /**
     * Returns {@code dividend / divisor} using specified rounding mode.
     *
     * @param dividend     the {@code dividend}.
     * @param divisor      the {@code divisor}.
     * @param roundingMode the rounding mode.
     * @return the result of {@code dividend / divisor}.
     */
    // the dividend is divided by the divisor to get a quotient
    default BigDecimal divide(final BigDecimal dividend, final BigDecimal divisor, final RoundingMode roundingMode) {
        return dividend.divide(divisor, roundingMode);
    }
}

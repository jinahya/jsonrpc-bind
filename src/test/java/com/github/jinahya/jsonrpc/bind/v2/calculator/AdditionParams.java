package com.github.jinahya.jsonrpc.bind.v2.calculator;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class AdditionParams {

    @Setter
    @Getter
    private BigDecimal addend;

    @Setter
    @Getter
    private BigDecimal augend;
}

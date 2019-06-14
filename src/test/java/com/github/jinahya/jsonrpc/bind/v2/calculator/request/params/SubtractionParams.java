package com.github.jinahya.jsonrpc.bind.v2.calculator.request.params;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SubtractionParams {

    @NotNull
    @Setter
    @Getter
    private BigDecimal minuend;

    @NotNull
    @Setter
    @Getter
    private BigDecimal subtrahend;
}

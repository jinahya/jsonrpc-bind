package com.github.jinahya.jsonrpc.bind.v2.calculator;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public interface CalculatorService {

    // -----------------------------------------------------------------------------------------------------------------
    String METHOD_ADD_NAMED = "add_named";

    String METHOD_ADD_POSITIONED = "add_positioned";

    // -----------------------------------------------------------------------------------------------------------------
    String METHOD_SUBTRACT_NAMED = "subtract_named";

    String METHOD_SUBTRACT_POSITIONED = "subtract_positioned";

    // -----------------------------------------------------------------------------------------------------------------
    String METHOD_MULTIPLY_NAMED = "multiply_named";

    String METHOD_MULTIPLY_POSITIONED = "multiply_positioned";

    // -----------------------------------------------------------------------------------------------------------------
    String METHOD_DIVIDE_NAMED = "divide_named";

    String METHOD_DIVIDE_POSITIONED = "divide_positioned";

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure(method = METHOD_ADD_NAMED)
    @NotNull BigDecimal add(@Valid @NotNull CalculatorRequestParams.AdditionParams named);

    @CalculatorProcedure(method = METHOD_ADD_POSITIONED)
    default @NotNull BigDecimal add(
            @Size(min = 2, max = 2) @NotNull List<@NotNull ? extends BigDecimal> positioned) {
        return add(CalculatorRequestParams.of(CalculatorRequestParams.AdditionParams.class, positioned));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure(method = METHOD_SUBTRACT_NAMED)
    @NotNull BigDecimal subtract(@Valid @NotNull CalculatorRequestParams.SubtractionParams named);

    @CalculatorProcedure(method = METHOD_SUBTRACT_POSITIONED)
    default @NotNull BigDecimal subtract(
            @Size(min = 2, max = 2) @NotNull List<@NotNull ? extends BigDecimal> positioned) {
        return subtract(CalculatorRequestParams.of(CalculatorRequestParams.SubtractionParams.class, positioned));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure(method = METHOD_MULTIPLY_NAMED)
    @NotNull BigDecimal multiply(@Valid @NotNull CalculatorRequestParams.MultiplicationParam named);

    @CalculatorProcedure(method = METHOD_MULTIPLY_POSITIONED)
    default @NotNull BigDecimal multiply(
            @Size(min = 2, max = 2) @NotNull List<@NotNull ? extends BigDecimal> positioned) {
        return multiply(CalculatorRequestParams.of(CalculatorRequestParams.MultiplicationParam.class, positioned));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure(method = METHOD_DIVIDE_NAMED)
    @NotNull BigDecimal divide(@Valid @NotNull CalculatorRequestParams.DivisionParam divisionParam);

    @CalculatorProcedure(method = METHOD_DIVIDE_POSITIONED)
    default @NotNull BigDecimal divide(
            @Size(min = 2, max = 2) @NotNull List<@NotNull ? extends BigDecimal> positioned) {
        return divide(CalculatorRequestParams.of(CalculatorRequestParams.DivisionParam.class, positioned));
    }
}

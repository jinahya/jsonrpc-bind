package com.github.jinahya.jsonrpc.bind.v2.calculator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.CalculatorRequest;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.AdditionParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.DivisionParam;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.MultiplicationParam;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.CalculatorParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.SubtractionParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponse;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponseError;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponseErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static com.github.jinahya.jsonrpc.bind.v2.BeanValidationUtils.requireValid;
import static com.github.jinahya.jsonrpc.bind.v2.JacksonUtils.OBJECT_MAPPER;

@RestController
@Slf4j
public class CalculatorController {

    public static final String PATH_NAME_CALL = "call";

    public static final String PATH_VALUE_CALL = "call";

    public static final String PATH_TEMPLATE_CALL = "{" + PATH_NAME_CALL + ":" + PATH_VALUE_CALL + "}";

    // -----------------------------------------------------------------------------------------------------------------
    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface CalculatorProcedure {

        String method() default "";
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PostMapping(
            value = "/" + PATH_TEMPLATE_CALL,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> call(final InputStream bodyStream) throws IOException {
        final CalculatorRequest calculatorRequest;
        try {
            calculatorRequest = requireValid(OBJECT_MAPPER.readValue(bodyStream, CalculatorRequest.class));
        } catch (final JsonParseException jpe) {
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_PARSE_ERROR,
                                    "failed to parse json; " + jpe.getMessage(),
                                    CalculatorResponseErrorData.of(null, null))));
        } catch (final ConstraintViolationException cve) {
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_INVALID_REQUEST,
                                    "invalid calculatorRequest; "
                                    + cve.getConstraintViolations().iterator().next().getMessage(),
                                    CalculatorResponseErrorData.of(null, null))));
        }
        final String requestMethod = calculatorRequest.getMethod();
        Method method = null;
        Class<?> parameterType = null;
        for (final Method m : getClass().getDeclaredMethods()) {
//            if (!m.getName().equals(requestMethod)) {
//                continue;
//            }
            final CalculatorProcedure procedure = m.getAnnotation(CalculatorProcedure.class);
            if (procedure == null) {
                continue;
            }
            String procedureMethod = procedure.method();
            if (procedureMethod.isEmpty()) {
                procedureMethod = m.getName();
            }
            if (!procedureMethod.equals(requestMethod)) {
                continue;
            }
            final Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (!CalculatorParams.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }
            if (!m.isAccessible()) {
                m.setAccessible(true);
            }
            method = m;
            parameterType = parameterTypes[0];
            break;
        }
        if (method == null) {
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_METHOD_NOT_FOUND,
                                    "unknown method: " + requestMethod,
                                    CalculatorResponseErrorData.of(calculatorRequest, null)
                            )
                    )
            );
        }
        final JsonNode requestParams = calculatorRequest.getParams();
        final Object parameter = OBJECT_MAPPER.readValue(
                OBJECT_MAPPER.writeValueAsString(requestParams), parameterType);
        final BigDecimal result;
        try {
            result = (BigDecimal) method.invoke(this, parameter);
        } catch (final InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (cause instanceof ArithmeticException) {
                return ResponseEntity.ok(
                        CalculatorResponse.of(
                                CalculatorResponseError.of(
                                        ResponseObject.ErrorObject.CODE_INTERNAL_ERROR,
                                        cause.getMessage(),
                                        CalculatorResponseErrorData.of(calculatorRequest, ((ArithmeticException) cause))
                                )
                        )
                );
            }
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_INTERNAL_ERROR,
                                    cause.getMessage(),
                                    CalculatorResponseErrorData.of(calculatorRequest, null)
                            )
                    )
            );
        } catch (final Exception e) {
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_INTERNAL_ERROR,
                                    e.getMessage(),
                                    CalculatorResponseErrorData.of(calculatorRequest, null)
                            )
                    )
            );
        }
        final String requestId = calculatorRequest.getId();
        final CalculatorResponse calculatorResponse = new CalculatorResponse();
        calculatorResponse.setResult(result);
        calculatorResponse.setId(requestId);
        return ResponseEntity.ok(calculatorResponse);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure(method = CalculatorRequest.METHOD_ADD)
    public BigDecimal add(@Valid @NotNull final AdditionParams additionParams) {
        return calculator.add(additionParams.getAddend(), additionParams.getAugend());
    }

    @CalculatorProcedure(method = CalculatorRequest.METHOD_SUBTRACT)
    public BigDecimal subtract(@Valid @NotNull final SubtractionParams subtractionParams) {
        return calculator.subtract(subtractionParams.getMinuend(), subtractionParams.getSubtrahend());
    }

    @CalculatorProcedure(method = CalculatorRequest.METHOD_MULTIPLY)
    public BigDecimal multiply(@Valid @NotNull final MultiplicationParam multiplicationParam) {
        return calculator.multiply(multiplicationParam.getMultiplier(), multiplicationParam.getMultiplicand());
    }

    @CalculatorProcedure(method = CalculatorRequest.METHOD_DIVIDE)
    public BigDecimal divide(@Valid @NotNull final DivisionParam divisionParam) {
        return calculator.divide(divisionParam.getDividend(), divisionParam.getDivisor(),
                                 divisionParam.getRoundingMode());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private Calculator calculator;
}

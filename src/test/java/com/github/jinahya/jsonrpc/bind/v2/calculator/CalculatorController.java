package com.github.jinahya.jsonrpc.bind.v2.calculator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.CalculatorRequest;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.AdditionParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.CalculationParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.DivisionParam;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.MultiplicationParam;
import com.github.jinahya.jsonrpc.bind.v2.calculator.request.params.SubtractionParams;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponse;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponseError;
import com.github.jinahya.jsonrpc.bind.v2.calculator.response.CalculatorResponseErrorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

@RestController
public class CalculatorController {

    // -----------------------------------------------------------------------------------------------------------------
    private static ObjectMapper OBJECT_MAPPER = null;

    private static ObjectMapper objectMapper() {
        if (OBJECT_MAPPER == null) {
            OBJECT_MAPPER = new ObjectMapper();
        }
        return OBJECT_MAPPER;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface CalculatorProcedure {

    }

    // -----------------------------------------------------------------------------------------------------------------
    @PostMapping(
            value = "/call",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> call(@RequestBody final InputStream bodyStream) throws IOException {
        final CalculatorRequest request;
        try {
            request = requireValid(objectMapper().readValue(bodyStream, CalculatorRequest.class));
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
                                    "invalid request; " + cve.getConstraintViolations().iterator().next().getMessage(),
                                    CalculatorResponseErrorData.of(null, null))));
        }
        final String requestMethod = request.getMethod();
        Method method = null;
        Class<?> parameterType = null;
        for (final Method m : getClass().getDeclaredMethods()) {
            if (!m.getName().equals(requestMethod)) {
                continue;
            }
            if (!m.isAnnotationPresent(CalculatorProcedure.class)) {
                continue;
            }
            final Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (!CalculationParams.class.isAssignableFrom(parameterTypes[0])) {
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
                                    CalculatorResponseErrorData.of(request, null)
                            )
                    )
            );
        }
        final JsonNode requestParams = request.getParams();
        final Object parameter = objectMapper().readValue(
                objectMapper().writeValueAsString(requestParams), parameterType);
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
                                        CalculatorResponseErrorData.of(request, ((ArithmeticException) cause))
                                )
                        )
                );
            }
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_INTERNAL_ERROR,
                                    cause.getMessage(),
                                    CalculatorResponseErrorData.of(request, null)
                            )
                    )
            );
        } catch (final Exception e) {
            return ResponseEntity.ok(
                    CalculatorResponse.of(
                            CalculatorResponseError.of(
                                    ResponseObject.ErrorObject.CODE_INTERNAL_ERROR,
                                    e.getMessage(),
                                    CalculatorResponseErrorData.of(request, null)
                            )
                    )
            );
        }
        final String requestId = request.getId();
        final CalculatorResponse response = new CalculatorResponse();
        response.setResult(result);
        response.setId(requestId);
        return ResponseEntity.ok(response);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CalculatorProcedure
    public BigDecimal add(@Valid @NotNull final AdditionParams additionParams) {
        return calculator.add(additionParams.getAddend(), additionParams.getAugend());
    }

    @CalculatorProcedure
    public BigDecimal subtract(@Valid @NotNull final SubtractionParams subtractionParams) {
        return calculator.subtract(subtractionParams.getMinuend(), subtractionParams.getSubtrahend());
    }

    @CalculatorProcedure
    public BigDecimal multiply(@Valid @NotNull final MultiplicationParam multiplicationParam) {
        return calculator.multiply(multiplicationParam.getMultiplier(), multiplicationParam.getMultiplicand());
    }

    @CalculatorProcedure
    public BigDecimal divide(@Valid @NotNull final DivisionParam divisionParam) {
        return calculator.divide(divisionParam.getDividend(), divisionParam.getDivisor(),
                                 divisionParam.getRoundingMode());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private Calculator calculator;
}

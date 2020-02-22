package com.github.jinahya.jsonrpc.bind.v2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * A class for binding {@value ResponseObject#PROPERTY_NAME_ERROR} property of response objects.
 *
 * @param <DataType> {@value PROPERTY_NAME_DATA} type parameter
 * @see <a href="https://www.jsonrpc.org/specification#error_object">Error Object (JSON-RPC 2.0
 * Specification)</a>
 */
public abstract class ResponseErrorObject<DataType> {

    // -------------------------------------------------------------------------------------------------------------

    /**
     * The property name for {@code $.error.code} of response objects. The value is {@value}.
     */
    public static final String PROPERTY_NAME_CODE = "code";

    /**
     * The property name for {@code $.error.message} of response objects. The value is {@value}.
     */
    public static final String PROPERTY_NAME_MESSAGE = "message";

    /**
     * The property name for {@code $.error.data} of response objects. The value is {@value}.
     */
    public static final String PROPERTY_NAME_DATA = "data";

    // -------------------------------------------------------------------------------------------------------------

    /**
     * The minimum value for codes reserved for pre-defined errors. The value is {@value}.
     */
    public static final int MIN_CODE_PREDEFINED_ERROR = -32768;

    /**
     * The maximum value for codes reserved for pre-defined errors. The value is {@value}.
     */
    public static final int MAX_CODE_PREDEFINED_ERROR = -32000;

    // -------------------------------------------------------------------------------------------------------------

    /**
     * The code value for <i>parse error</i> meaning an invalid json received by the server or an error occurred on the
     * server while parsing the JSON text. The value is {@value}.
     */
    public static final int CODE_PARSE_ERROR = -32700;

    /**
     * The code value for <i>invalid request</i> meaning the JSON sent is not a valid request object. The value is
     * {@value}.
     */
    public static final int CODE_INVALID_REQUEST = -32600;

    /**
     * The code value for <i>method not found</i> meaning the method does not exist nor available. The value is
     * {@value}.
     */
    public static final int CODE_METHOD_NOT_FOUND = -32601;

    /**
     * The code value for <i>invalid params</i> meaning invalid parameter(s). The value is {@value}.
     */
    public static final int CODE_INVALID_PARAMS = -32602;

    /**
     * The code value for <i>internal error</i> meaning an internal JSON-RPC error. The value is {@value}.
     */
    public static final int CODE_INTERNAL_ERROR = -32603;

    // -------------------------------------------------------------------------------------------------------------

    /**
     * The minimum value for codes reserved for implementation-defined server errors. The value is {@value}.
     */
    public static final int MIN_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32099;

    /**
     * The maximum value for codes reserved for implementation-defined server errors. The value is {@value}.
     */
    public static final int MAX_CODE_IMPLEMENTATION_DEFINED_SERVER_ERROR = -32000;

    // -------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public ResponseErrorObject() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "code=" + code
               + ",message=" + message
               + ",data=" + data
               + "}";
    }

    // -------------------------------------------------------------------------------------------------------- code

    /**
     * Returns the current value of {@value #PROPERTY_NAME_CODE} property. Note that the value of the {@value
     * #PROPERTY_NAME_CODE} property must be {@link NotNull} in a Bean-Validation context.
     *
     * @return the current value of {@value #PROPERTY_NAME_CODE} property.
     */
    public int getCode() {
        return code;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_CODE} property with specified value.
     *
     * @param code new value for {@value #PROPERTY_NAME_CODE} property.
     */
    public void setCode(final int code) {
        this.code = code;
    }

    // ----------------------------------------------------------------------------------------------------- message

    /**
     * Returns the current value of {@value #PROPERTY_NAME_MESSAGE} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_MESSAGE} property.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_MESSAGE} property with specified value.
     *
     * @param message new value for {@value #PROPERTY_NAME_MESSAGE} property.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    // -------------------------------------------------------------------------------------------------------- data

    /**
     * Returns the current value of {@value #PROPERTY_NAME_DATA} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_DATA} property.
     */
    public DataType getData() {
        return data;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_DATA} property with specified value.
     *
     * @param data new value for {@value #PROPERTY_NAME_DATA} property.
     */
    public void setData(final DataType data) {
        this.data = data;
    }

    // -------------------------------------------------------------------------------------------------------------

    /**
     * An attribute for {@value #PROPERTY_NAME_CODE} property.
     */
    @NotNull
    private int code;

    /**
     * An attribute for {@value #PROPERTY_NAME_MESSAGE} property.
     */
    @NotNull
    private String message;

    /**
     * An attribute for {@value #PROPERTY_NAME_DATA} property.
     */
    @Valid
    private DataType data;
}

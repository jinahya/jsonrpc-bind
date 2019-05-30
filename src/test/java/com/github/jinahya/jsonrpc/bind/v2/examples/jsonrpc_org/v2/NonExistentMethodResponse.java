package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.ErrorObject;
import com.github.jinahya.jsonrpc.bind.v2.ResponseObject;

public class NonExistentMethodResponse extends ResponseObject<Void, NonExistentMethodResponse.Error> {

    public static class Error extends ErrorObject<Void> {

    }
}

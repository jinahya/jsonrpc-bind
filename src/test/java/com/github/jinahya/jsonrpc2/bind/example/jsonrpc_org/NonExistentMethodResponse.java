package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.ErrorObject;
import com.github.jinahya.jsonrpc2.bind.ResponseObject;

class NonExistentMethodResponse extends ResponseObject<Void, NonExistentMethodResponse.Error> {

    static class Error extends ErrorObject<Void> {

    }
}

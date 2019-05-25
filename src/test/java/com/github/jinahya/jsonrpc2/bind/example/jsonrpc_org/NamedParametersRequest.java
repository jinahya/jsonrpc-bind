package com.github.jinahya.jsonrpc2.bind.example.jsonrpc_org;

import com.github.jinahya.jsonrpc2.bind.RequestObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NamedParametersRequest extends RequestObject<NamedParametersRequest.Params> {

    @Data
    public static class Params {

        private long minuend;

        private long subtrahend;
    }
}

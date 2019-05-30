package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.RequestObject;
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

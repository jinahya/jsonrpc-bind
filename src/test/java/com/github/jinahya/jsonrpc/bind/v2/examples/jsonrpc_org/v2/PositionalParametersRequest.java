package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.v2;

import com.github.jinahya.jsonrpc.bind.v2.RequestObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PositionalParametersRequest extends RequestObject<List<Long>> {

    @Override
    public String toString() {
        final List<Long> params = getParams();
        return super.toString();
    }
}

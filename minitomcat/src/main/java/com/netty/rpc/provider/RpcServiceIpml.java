package com.netty.rpc.provider;

import com.netty.rpc.api.IRpcService;

/**
 * 〈〉
 * create by zgw on 2019/7/10
 */
public class RpcServiceIpml implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mult(int a, int b) {
        return a*b;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }
}

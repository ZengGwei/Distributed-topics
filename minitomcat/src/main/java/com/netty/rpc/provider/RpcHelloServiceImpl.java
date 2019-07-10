package com.netty.rpc.provider;

import com.netty.rpc.api.IRpcHelloServer;

/**
 * 〈〉
 * create by zgw on 2019/7/10
 */
public class RpcHelloServiceImpl implements IRpcHelloServer {
    @Override
    public String hello(String name) {
        return "hello "+name+".";
    }
}

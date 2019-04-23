package com.zgw.rmi.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        TCPTransport tcpTransport = new TCPTransport(this.host,this.port );
        Object send = tcpTransport.send(rpcRequest);

        return send;
    }
}

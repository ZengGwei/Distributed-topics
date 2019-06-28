package com.zgw.rmi.newrpcServer.RPC;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 〈〉
 * @author gw.Zeng
 * @create 2019/6/28
 * @since 1.0.0
 */
public class RequestInvacationHandler implements InvocationHandler {
        private  String host;
        private  int prot;

    public RequestInvacationHandler(String host, int prot) {
        this.host = host;
        this.prot = prot;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        NewRcpRequest rcpRequest = new NewRcpRequest();
        rcpRequest.setMethodName(method.getName());
        rcpRequest.setPrams(args);
        rcpRequest.setServerName(method.getDeclaringClass().getName());

        RemoteTansport remoteTansport = new RemoteTansport(this.host, this.prot);
        Object res = remoteTansport.sendReq(rcpRequest);

        return res;
    }
}

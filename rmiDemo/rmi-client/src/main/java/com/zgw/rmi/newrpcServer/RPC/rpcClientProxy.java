package com.zgw.rmi.newrpcServer.RPC;

import java.lang.reflect.Proxy;

/**
 *代理请求
 * @author gw.Zeng
 * @create 2019/6/28
 * @since 1.0.0
 */
public class rpcClientProxy {

    public  <T> T  getRpcServerProxy(  Class<T> interfaces,String host,int prot ){
        return (T) Proxy.newProxyInstance(interfaces.getClass().getClassLoader(), new Class[]{interfaces}, new RequestInvacationHandler(host,prot));
    }

}

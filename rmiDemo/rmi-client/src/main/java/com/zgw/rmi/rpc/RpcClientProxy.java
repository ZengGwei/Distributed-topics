package com.zgw.rmi.rpc;

import java.lang.reflect.Proxy;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class RpcClientProxy {
    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final  int port){


     return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, new RemoteInvocationHandler(host,port));
    }

}

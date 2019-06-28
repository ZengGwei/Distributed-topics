package com.zgw.rmi.newrpcServer;

import com.zgw.rmi.rpc.RpcClientProxy;

/**

 */
public class RemoteRequestDome {
    public static void main(String[] args) {

        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        UserServer userServer = rpcClientProxy.clientProxy(UserServer.class, "127.0.0.1", 9090);
        String name = userServer.getNameByUserId(10000);
        System.out.println(name);

    }

}

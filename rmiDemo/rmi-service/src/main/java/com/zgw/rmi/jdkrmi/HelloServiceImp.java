package com.zgw.rmi.jdkrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * JRMI 发布服务
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class HelloServiceImp extends UnicastRemoteObject implements IHelloService {
    protected HelloServiceImp() throws RemoteException {
//        super();
    }

    @Override
    public String sayHello(String name)throws RemoteException {
        return "Hello,"+name;
    }
}

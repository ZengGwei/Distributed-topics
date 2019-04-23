package com.zgw.rmi.jdkrmi;

import java.rmi.Remote;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public interface IHelloService extends Remote {
    String sayHello(String name);

}

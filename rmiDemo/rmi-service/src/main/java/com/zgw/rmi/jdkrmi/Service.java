package com.zgw.rmi.jdkrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 服务接口  继承 Remote  ,实现类 继承UnicastRemoteObject
 *
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class Service {
    public static void main(String[] args) {
        //发布服务
        try {
            IHelloService helloService = new HelloServiceImp();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/hello", helloService);//注册中心
            System.out.println("服务启动成功！");


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {

        }


    }


}

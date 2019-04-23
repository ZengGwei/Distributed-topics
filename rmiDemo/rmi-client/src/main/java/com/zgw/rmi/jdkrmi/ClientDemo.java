package com.zgw.rmi.jdkrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class ClientDemo {
    public static void main(String[] args) {
        //请求服务
        try {
            IHelloService helloService = (IHelloService)Naming.lookup("rmi://127.0.0.1/hello");
            String res = helloService.sayHello("李雷");

            System.out.println(res);


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}

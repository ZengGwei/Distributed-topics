package com.zgw.rmi.rpc;

/**
 * 调用服务类
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy clientProxy = new RpcClientProxy();

        IWeather weather = clientProxy.clientProxy(IWeather.class,"localhost" ,8888 );
        String queryWeather = weather.queryWeather("04-23");
        System.out.println(queryWeather);


    }

}

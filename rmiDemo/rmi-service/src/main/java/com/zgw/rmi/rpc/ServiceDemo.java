package com.zgw.rmi.rpc;

/**
 * 发布服务
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class ServiceDemo {

    public static void main(String[] args) {
        IWeather weather = new WeatherImp();
        //发布服务
        RpcService rpcService = new RpcService();
        rpcService.publisher(weather,8888 );


    }
}

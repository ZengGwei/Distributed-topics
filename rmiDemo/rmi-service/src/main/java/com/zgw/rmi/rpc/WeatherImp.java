package com.zgw.rmi.rpc;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class WeatherImp implements  IWeather {
    @Override
    public String queryWeather(String date) {
        return date+" 天气晴 温度10°到20°，空气质量优。";
    }
}

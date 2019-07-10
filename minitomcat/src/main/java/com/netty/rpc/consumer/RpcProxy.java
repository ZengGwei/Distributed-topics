package com.netty.rpc.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 客户端有代理对象去调用
 * create by zgw on 2019/7/10
 */
public class RpcProxy {

    public static  <T> T create(Class<T> clazz){
        //参数即是interface
        MethodProxy proxy =  new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface() ? new Class[]{clazz} :
                clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(),interfaces,proxy);
        return result;
    }
    //由netty 发送网络请求
    public  static  class MethodProxy implements InvocationHandler{
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           if()
        }
    }


}

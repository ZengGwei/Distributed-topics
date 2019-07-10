package com.netty.rpc.registry;

import com.netty.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现注册具体逻辑
 * create by zgw on 2019/7/10
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {
    //用于保存服务
    public static ConcurrentHashMap<String,Object> registryMap= new ConcurrentHashMap<>(128);

    //保存所有相关服务类
    private List<String> className = new ArrayList<>();

    public RegistryHandler( ) {
        scannerClass("com.netty.rpc.provoder");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object res = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;
        
        //当客户端链接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        if (registryMap.containsKey(request.getClassName())){
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            res =  method.invoke(clazz,request.getValues());
        }
        ctx.write(res);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void doRegistry() {
        if (className.size() == 0 ){return;}
        for (String clazzName :className){
            try{
                Class<?> clazz = Class.forName(clazzName);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(),clazz.newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void scannerClass(String pageName) {
        URL url = this.getClass().getClassLoader().getResource(pageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()){
            if (file.isDirectory()){
                scannerClass(pageName+"."+file.getName());
            }else {
                className.add(pageName+"."+file.getName().replace(".class","").trim());
            }
        }
    }

}

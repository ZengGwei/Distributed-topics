package com.zgw.rmi.newrpcServer.RPC;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 〈〉
 *
 * @author gw.Zeng
 * @create 2019/6/27
 * @since 1.0.0
 */
public class NewProcessorHandler implements Runnable {
    private Socket socket;
    private Object server;

    public NewProcessorHandler(Socket socket, Object server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            RcpRequest rcpRequest =(RcpRequest) objectInputStream.readObject();
            Object res =  invoke(rcpRequest);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(res);

            objectOutputStream.flush();

            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RcpRequest rcpRequest) {
        String serverName = rcpRequest.getServerName();
        if (null == serverName){
            throw  new IllegalArgumentException("服务类为null");
        }
        String methodName = rcpRequest.getMethodName();
        Object[] prams = rcpRequest.getPrams();
        Class[] types = new Class[prams.length];
        //获取参数的类型
        for (int i = 0; i <prams.length ; i++) {
            types[i]=prams[i].getClass();
        }
        Object res=null;
        try {
            Method method = this.server.getClass().getMethod(methodName,types);
            res = method.invoke(this.server, prams);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }
}

package com.zgw.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class ProcessorHandler implements  Runnable {
    private  Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream =null;
        //处理请求
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());

            RpcRequest rpcRequest =(RpcRequest)inputStream.readObject();
            Object result = invoke(rpcRequest);

            ObjectOutputStream  outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object invoke(RpcRequest rpcRequest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String className = rpcRequest.getClassName();
        Object[] args = rpcRequest.getParameters();
        Class[] types = new Class[args.length];
        for (int i = 0; i <args.length ; i++) {
             types[i]= args[i].getClass();
        }
        Method method = service.getClass().getMethod( rpcRequest.getMethodName(),types);
        return method.invoke(service,args);
    }
}

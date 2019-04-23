package com.zgw.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class TCPTransport {

    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){
        System.out.println("创建一个新的连接");

        Socket socket =null;

        try {
            socket = new Socket(host, port);
            return  socket;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("建立连接失败！");
        }
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket=null;
        try {
            socket = newSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            ObjectInputStream inputStream  = new ObjectInputStream(socket.getInputStream());

            Object result = inputStream.readObject();
            outputStream.close();
            inputStream.close();
            return result;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发起远程调用异常：",e);
        }finally {
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

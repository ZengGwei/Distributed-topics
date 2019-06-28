package com.zgw.rmi.newrpcServer.RPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 〈〉
 *
 * @author gw.Zeng
 * @create 2019/6/28
 * @since 1.0.0
 */
public class RemoteTansport {

    private String host;
    private int port;

    public RemoteTansport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    private  Socket newSocket()   {
        System.out.println("开始创建一个连接！！");
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            socketClose(socket);

        }
        return  socket;
    }
    public Object  sendReq(NewRcpRequest request){
        System.out.println("发送 远程 request："+request);
        Socket socket = newSocket();
        Object res ;
        try {
            ObjectOutputStream  objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            res =  objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("远程发送连接失败。。。");
        }finally {
            socketClose(socket);
        }


    }

    private  void socketClose(Socket socket){
        if (null != socket){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

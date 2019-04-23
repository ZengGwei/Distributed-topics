package com.zgw.rmi.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class RpcService {

    private  final ExecutorService executorService = Executors.newCachedThreadPool();//定义一个线程池
    //发布
    public void publisher(final Object service,int port){
        ServerSocket serverSocket =null;

        try {
            serverSocket= new ServerSocket(port);//启动一个服务监听
            while (true){
                Socket socket =serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket,service));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

package com.zgw.rmi.newrpcServer.RPC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈〉
 * @author gw.Zeng
 * @create 2019/6/27
 * @since 1.0.0
 */

public class ServerPublish {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private int port;
    private Object server;
    public ServerPublish(int port,Object server ){
       this.port =port;
        this.server = server;
    }

    /*
    服务对外发布//BIO
     */
    public void  publish(){
        ServerSocket serverSocket =null;
        try {
              serverSocket = new ServerSocket(port);
            while (true){
                Socket accept = serverSocket.accept();
                executorService.execute(new NewProcessorHandler(accept,server));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

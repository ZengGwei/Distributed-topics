package com.zgw.rmi.newrpcServer;


import com.zgw.rmi.newrpcServer.RPC.ServerPublish;

public class ServerDome {

    public static void main(String[] args) {
        //服务发布
        ServerPublish serverPublish = new ServerPublish(9090, new UserServerImpl());
        serverPublish.publish();

    }



}

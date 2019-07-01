package ioNioAio.BIOcodeAction;

import ioNioAio.CommonUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈IO的演进之路 BIO〉
 * create by zgw on 2019/7/1
 */
public class BIOServer {
    private static int port = 9090;
    private static ExecutorService es = Executors.newFixedThreadPool(90);
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
//        try {
//            System.out.println("启动服务....");
//            serverSocket = new ServerSocket(port);
//            new Thread(new acceptHandler(serverSocket.accept())).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//          CommonUtils.serverSocketClose(serverSocket);
//        }

        //伪异步阻塞io
        try {
            System.out.println("启动服务....");
            serverSocket = new ServerSocket(port);
            es.execute(new acceptHandler(serverSocket.accept())) ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CommonUtils.serverSocketClose(serverSocket);
        }





    }

    static class acceptHandler implements Runnable {
        private Socket accept;
        public acceptHandler(Socket accept) {
            this.accept = accept;
        }
        public void run() {
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String s = bufferedReader.readLine();
                System.out.println("已接受数据：" + s);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
                bufferedWriter.write(s);
                bufferedWriter.flush();

                bufferedReader.close();
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CommonUtils.socketClose(accept);
                CommonUtils.bufferedReadClose(bufferedReader);
                CommonUtils.bufferedWrietClose(bufferedWriter);
            }

        }
    }


}

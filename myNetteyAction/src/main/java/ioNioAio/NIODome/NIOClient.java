package ioNioAio.NIODome;

import ioNioAio.CommonUtils;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 〈IO的演进之路 NIO〉
 * create by zgw on 2019/7/2
 */
public class NIOClient {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter bufferedWriter =null;

        try{
              socket = new Socket("127.0.0.1", 9090);
              bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
              bufferedWriter.write("早上好！今天请转多云！气温5到20℃。");
              bufferedWriter.flush();
              System.out.println("消息发送完成");
              socket.close();
              bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtils.socketClose(socket);
            CommonUtils.bufferedWrietClose(bufferedWriter);
        }
    }
}

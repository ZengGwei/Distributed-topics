package ioNioAio.BIOcodeAction;

import ioNioAio.CommonUtils;

import java.io.*;
import java.net.Socket;

/**
 * 〈IO演进之路 BIO〉
 * create by zgw on 2019/7/1
 */
public class BIOClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            socket = new Socket("127.0.0.1", 9090);
            System.out.println("开始请求。。");

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("client: this is a test!");
            bufferedWriter.flush();

            bufferedWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CommonUtils.socketClose(socket);
            CommonUtils.bufferedReadClose(bufferedReader);
            CommonUtils.bufferedWrietClose(bufferedWriter);
        }


    }

}

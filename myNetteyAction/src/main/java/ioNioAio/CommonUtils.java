package ioNioAio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈〉
 * create by zgw on 2019/7/1
 */
public class CommonUtils {

  public static void socketClose(Socket socket){
      if (socket !=null){
          try {
              socket.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

  public static void bufferedReadClose(BufferedReader reader){
      if (reader !=null){
          try {
              reader.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

    public static void bufferedWrietClose(BufferedWriter writer){
        if (writer !=null){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   public static void serverSocketClose(ServerSocket serverSocket){
        if (serverSocket !=null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

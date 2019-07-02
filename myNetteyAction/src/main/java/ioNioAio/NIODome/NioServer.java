package ioNioAio.NIODome;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 〈IO的演进之路 NIO〉
 * create by zgw on 2019/7/1
 */
public class NioServer implements Runnable{

    public static void main(String[] args) {
        System.out.println("启动服务！");
        new Thread(new NioServer(9090)).start();

    }


    //1.选择器（多路复用器）
    private Selector selector ;
    //2.建立读写缓冲区
    private ByteBuffer readBuffered = ByteBuffer.allocate(1024);
    private ByteBuffer  writeBudder = ByteBuffer.allocate(1024);

    public NioServer(int port) {
        try{
            //1.打开复用器
            selector = Selector.open();
            //2. 打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            //3 绑定端口
            ssc.bind(new InetSocketAddress(port));
            //4 把服务器注册到多路复用器，并监听
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start,port:"+port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) try {
            this.selector.select();//  1.selector开始监听

            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();//2.返回多路复用已经选择的结果

            while (keys.hasNext()) {
                // 先从移除
                SelectionKey key = keys.next();
                keys.remove();

                if (key.isValid()) {// 是否有效
                    if (key.isAcceptable()) { // 是否阻塞,把阻塞的 放回 selector
                        //1 获取通道
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        // 2 执行阻塞方法
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        // 3 注册
                        accept.register(this.selector, SelectionKey.OP_ACCEPT);
                    }
                    if (key.isReadable()) { //可读状态
                        readInfo(key);
                    }
                    if (key.isWritable()) {//可写状态
                        writeInfo(key);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readInfo(SelectionKey key) throws IOException {
        //1. 清空缓冲区
        this.readBuffered.clear();
        // 2 获取通道
        SocketChannel channel =(SocketChannel) key.channel();
        //3  读数据
        int read = channel.read(this.readBuffered);
        // 4 没数据 取消
        if (read == -1){
            key.channel().close();
            key.cancel();
            return;
        }
        // 有数据读取，position，limit复位
        this.readBuffered.flip();

        //接收
        byte[] bytes = new byte[this.readBuffered.remaining()];
        this.readBuffered.get(bytes);
        String  body = new String(bytes).trim();
        System.out.println("server : "+body);
    }
    private void writeInfo(SelectionKey key) {

    }


}




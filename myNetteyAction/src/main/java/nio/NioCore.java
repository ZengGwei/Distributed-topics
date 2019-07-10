package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 〈netty 的核心组件 == >  缓冲区（buffer） 、选择器(selectot)、通道（channel）〉
 * create by zgw on 2019/7/9
 */
public class NioCore {
   private ByteBuffer buffer  =  ByteBuffer.allocate(1024);
    public static void main(String[] args) {
        ByteBuffer buffer  =  ByteBuffer.allocate(1024);
        IntBuffer intBuffer = IntBuffer.allocate(8); //容量为8 的int缓冲区
        IntBuffer wrap = IntBuffer.wrap(new int[10]);// 缓冲区的分配 有两种方式
        for (int i = 0; i <intBuffer.capacity() ; i++) {
            int j= 2*(i+1);
             intBuffer.put(j);
        }
        intBuffer.flip();//限制位置设为当前位置，位置 设置为0

        while (intBuffer.hasRemaining()){
            int j = intBuffer.get();
            System.out.println(j+"");

        }

        // 缓冲区 分片  容量为10  分出 3-7  为字片
        for (int i = 0; i <wrap.capacity() ; i++) {
            wrap.put((i+1)*2);
        }
        wrap.position(3);
        wrap.limit(7);
        IntBuffer slice = wrap.slice();






    }
    /**
     selector
     */
    private Selector getSelect(int port) throws IOException {
        Selector open = Selector.open(); // 开启 选择器对象
        ServerSocketChannel channel = ServerSocketChannel.open();//开启通道
        channel.configureBlocking(false);//配置非阻塞模式

        ServerSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(port));
        channel.register(open, SelectionKey.OP_ACCEPT);

        return open;
    }

    public  void listen(){
        try {
            while (true){
                Selector select = this.getSelect(8080);
                select.select();//阻塞  ，直到有事件 发生
                Set<SelectionKey> selectionKeys = select.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();// 先移除
                    process(select,key);//处理事件

                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**\
     * 根据不同的事件，不同处理
     * @param key
     */
    private void process( Selector select,SelectionKey key) throws IOException {
        if (!key.isAcceptable()){//接收请求,不是接收请求，重新注册
            ServerSocketChannel channel = (ServerSocketChannel)key.channel();
            channel.configureBlocking(false);
            channel.register(select,SelectionKey.OP_READ);
        }else if (key.isReadable()){//可读事件
            SocketChannel channel = (SocketChannel) key.channel();
            int read = channel.read(buffer);
            if (read>0){
                buffer.flip();
                String content = new String(buffer.array(), 0, read);
                SelectionKey sKey = channel.register(select, SelectionKey.OP_WRITE);
                sKey.attach(content);

            }else {
                channel.close();
            }
            buffer.clear();
        }else if (key.isWritable()){//可写事件
            SocketChannel channel = (SocketChannel) key.channel();
            String context =(String) key.attachment();
            ByteBuffer bb = ByteBuffer.wrap(context.getBytes());
            if (null != bb){
                channel.write(bb);
            }else
                channel.close();
        }




    }


}

package com.gw.nettyHttpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * http服务端开发
 * create by zgw on 2019/7/10
 *
 */
public class HttpFileServer {

    private static final String default_url = "/src/com/gw/nettyHttpServer/";

    public void run(final  int port,final String url){
        EventLoopGroup bossGroup  =new NioEventLoopGroup();
        EventLoopGroup workerGroup  =new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new HttpRequestDecoder());

                            ch.pipeline().addLast(new HttpObjectAggregator(65536));

                            ch.pipeline().addLast(new HttpRequestEncoder());

                            ch.pipeline().addLast(new ChunkedWriteHandler());

                            ch.pipeline().addLast(new HttpFileServerHandler(url));

                        }
                    });
            ChannelFuture future = b.bind(port).sync();
            System.out.println("服务启动，port"+port);
            future.channel().closeFuture().sync();
        }catch (Exception e){
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
     new HttpFileServer().run(8080,default_url);



    }

}

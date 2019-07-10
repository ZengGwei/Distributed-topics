package com.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 服务注册
 * create by zgw on 2019/7/10
 */
public class RpcRegistry {
    //启动一个netty
    private  int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //自定义协议解码器
                            /**入参 5个
                             * maxFrameLength   框架的最大长度，如果帧的长度大于此值，则 抛出TooLongFrameException
                             * lengthFiledOffset 长度字段的偏移量，即对应的长度字段在整个消息数据中的位置
                             * lengthFiledLength 长度字段的长度
                             * lengthAdjustment   要添加长度字段的补偿值
                             * initialBytesToStrip  从解码帧中去除的第一个字节数
                             */
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                    0,4,0,4));
                            //自定义协议编码器
                            ch.pipeline().addLast(new LengthFieldPrepender(4));
                            //对象类型编码器
                            ch.pipeline().addLast("encode",new ObjectEncoder());
                            //对象类型解码器
                            ch.pipeline().addLast("decode",new ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.cacheDisabled(null)));
                            ch.pipeline().addLast(new RegistryHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture future = b.bind(this.port).sync();
            System.out.println("N RPC Registry start listen at "+port);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }
}

package com.gw.nettyHttpServer;

import com.sun.scenario.effect.impl.prism.PrImage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.concurrent.EventExecutorGroup;
import sun.awt.im.SimpleInputMethodWindow;

/**
 * 〈〉
 * create by zgw on 2019/7/10
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String url ;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

    }
}

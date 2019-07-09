package com.cat.core.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

public class NRespones {
    private ChannelHandlerContext ctx;

    private HttpRequest req;
    public NRespones(ChannelHandlerContext ctx,HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void  write(String out){
        if (out == null ||out.length()==0){
            return;
        }
        try {
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("utf-8")));
            response.headers().set("Content-Type","test/html;");
            if (HttpUtil.isKeepAlive(req)){
                response.headers().set("CONNECTION", HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            ctx.flush();
           ctx.close();

        }


    }
}

package com.cat.core.http;


import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

public class NRequest {
    private ChannelHandlerContext txt;

    private HttpRequest req;


    public NRequest(ChannelHandlerContext txt, HttpRequest req) {
        this.txt = txt;
        this.req = req;
    }

    public String getURL(){
        return this.req.uri();
    }

    public String getMethodName(){
        return this.req.method().name();
    }

    public Map<String, List<String>> getParameters(){
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(this.req.uri());
        return  queryStringDecoder.parameters();
    }

    public String getParameters(String name){
        Map<String, List<String>> parameters = this.getParameters();
        List<String> paras = parameters.get(name);
        return paras !=null ? paras.get(0) : null;
    }
    public ChannelHandlerContext getTxt() {
        return txt;
    }

    public void setTxt(ChannelHandlerContext txt) {
        this.txt = txt;
    }

    public HttpRequest getReq() {
        return req;
    }

    public void setReq(HttpRequest req) {
        this.req = req;
    }
}

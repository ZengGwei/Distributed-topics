package com.cat.core.http;

/**
 * 〈〉
 * create by zgw on 2019/7/9
 */
public abstract class NServlet {

    public void  server(NRequest req,NRespones res){
        if ("GET".equals(req.getMethodName())){
            doGet(req,res);
        }else
            doPost(req,res);
    }

    public abstract void doGet(NRequest req, NRespones res);

    public abstract void doPost(NRequest req, NRespones res);


}

package com.cat.core.servlet;

import com.cat.core.http.NRequest;
import com.cat.core.http.NRespones;
import com.cat.core.http.NServlet;

/**
 * 〈〉
 * create by zgw on 2019/7/9
 */
public class FirstServlet extends NServlet {
    public void doGet(NRequest req, NRespones res) {
        this.doPost(req,res);
    }

    public void doPost(NRequest req, NRespones res) {
        res.write("this is a first servlet.");
    }
}

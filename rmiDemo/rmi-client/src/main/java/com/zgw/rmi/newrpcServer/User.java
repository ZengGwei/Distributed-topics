package com.zgw.rmi.newrpcServer;

import java.io.Serializable;

/**
 *
 * @author gw.Zeng
 * @create 2019/6/27
 * @since 1.0.0
 */
public class User implements Serializable {

    private  String name;//姓名
    private Long Id; //身份id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

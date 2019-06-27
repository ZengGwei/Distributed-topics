package com.zgw.rmi.newrpcServer;

/**
 * 〈〉
 *
 * @author gw.Zeng
 * @create 2019/6/27
 * @since 1.0.0
 */
public class UserServerImpl implements UserServer{
    @Override
    public String getNameByUserId(Integer id) {
        if (id.equals(10000))
            return "Tom";
        if (id.equals(10001))
            return "Jerry";
        return "Ann";
    }
}

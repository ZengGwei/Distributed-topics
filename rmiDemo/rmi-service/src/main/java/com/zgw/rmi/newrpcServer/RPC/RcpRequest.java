package com.zgw.rmi.newrpcServer.RPC;

import java.io.Serializable;

/**
 * 请求参数
 * @author gw.Zeng
 * @create 2019/6/27
 * @since 1.0.0
 */
public class RcpRequest implements Serializable {
    private static final long serialVersionUID = -5670285704940480605L;

    private  String serverName;

    private String  methodName;

    private Object[] prams;

    public RcpRequest() {
    }

    public RcpRequest(String serverName, String methodName, Object[] prams) {
        this.serverName = serverName;
        this.methodName = methodName;
        this.prams = prams;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getPrams() {
        return prams;
    }

    public void setPrams(Object[] prams) {
        this.prams = prams;
    }
}

package com.zgw.rmi.newrpcServer.RPC;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 请求参数
 */
public class NewRcpRequest implements Serializable {
    private static long serialVersionUID = -4402454311696246199L;
    private  String serverName;

    private String  methodName;

    private Object[] prams;

    public NewRcpRequest() {
    }

    public NewRcpRequest(String serverName, String methodName, Object[] prams) {
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

    @Override
    public String toString() {
        return "NewRcpRequest{" +
                "serverName='" + serverName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", prams=" + Arrays.toString(prams) +
                '}';
    }
}

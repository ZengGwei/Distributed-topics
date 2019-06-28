package com.zgw.rmi.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * create by Guangwei.Zeng on 2019/2019/4/23
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -5670285704940480605L;
    private  String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}

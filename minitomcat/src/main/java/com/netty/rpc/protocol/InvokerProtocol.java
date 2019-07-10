package com.netty.rpc.protocol;

import lombok.Data;

/**
 * 自定义协议 用于调用接口信息传输
 * create by zgw on 2019/7/10
 */
@Data
public class InvokerProtocol {
    private String className;//类名
    private String methodName;//函数名称
    private Class<?>[] parames;//参数类型
    private Object[] values;//参数类表
}

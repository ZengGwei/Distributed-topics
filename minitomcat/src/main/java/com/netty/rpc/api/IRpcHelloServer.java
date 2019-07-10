package com.netty.rpc.api;

/**
  确认服务是否可用
 * @author gw.Zeng
 * @create 2019/7/10
 * @since 1.0.0
 */
public interface IRpcHelloServer {
    String hello(String name);
}

package com.imooc.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * 客户机代码
 * Created by xuwei
 */
public class MyClient {
    public static void main(String[] args) throws Exception{
        //通过Socket连接RPC Server
        InetSocketAddress addr = new InetSocketAddress("localhost", 1234);
        Configuration conf = new Configuration();
        //获取RPC代理
        MyProtocol proxy = RPC.getProxy(MyProtocol.class, MyProtocol.versionID, addr, conf);
        //执行RPC接口中的方法并获取结果
        String result = proxy.hello("RPC");
        System.out.println("RPC Client(客户机)收到的结果："+result);
    }
}

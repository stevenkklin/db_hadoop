package com.imooc.rpc;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

/**
 * RPC实现类
 * Created by xuwei
 */
public class MyProtocolImpl implements MyProtocol {

    @Override
    public String hello(String name) {
        System.out.println("我被调用了...");
        return "hello "+name;
    }

    /**
     * 接口版本
     * @param protocol
     * @param clientVersion
     * @return
     * @throws IOException
     */
    @Override
    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return versionID;
    }

    /**
     * 协议签名
     * @param protocol
     * @param clientVersion
     * @param clientMethodsHash
     * @return
     * @throws IOException
     */
    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) throws IOException {
        return new ProtocolSignature();
    }
}

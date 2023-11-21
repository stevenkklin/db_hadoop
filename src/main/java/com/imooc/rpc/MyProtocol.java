package com.imooc.rpc;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * 自定义一个RPC接口
 * Created by xuwei
 */
public interface MyProtocol extends VersionedProtocol {
    long versionID = 123456L;
    String hello(String name);
}

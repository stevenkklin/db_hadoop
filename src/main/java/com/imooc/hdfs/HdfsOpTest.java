package com.imooc.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Created by xuwei
 */
public class HdfsOpTest {
    public static void main(String[] args) throws Exception{
        //创建一个配置对象
        Configuration conf = new Configuration();
        //指定HDFS的地址
        conf.set("fs.defaultFS","hdfs://bigdata01:9000");
        //获取操作HDFS的对象
        FileSystem fileSystem = FileSystem.get(conf);

        FSDataOutputStream fos = fileSystem.create(new Path("/user.txt"));
        fos.write("hello world!".getBytes());
        fos.close();
    }
}

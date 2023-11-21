package com.imooc.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

/**
 * Created by xuwei
 */
public class SeqRead {
    public static void main(String[] args) throws Exception{
        //创建一个配置对象
        Configuration conf = new Configuration();
        //指定HDFS的地址
        conf.set("fs.defaultFS","hdfs://bigdata01:9000");
        //seq文件路径
        String inputFile = "/stu_seqfile_none_compress/000000_0";
        //创建阅读器
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(new Path(inputFile)));
        BytesWritable key = new BytesWritable();
        Text value = new Text();
        //循环读取数据
        while(reader.next(key,value)){
            //输出文件名称
            System.out.print("文件名："+new String(key.getBytes())+",");
            //输出文件内容
            System.out.println("文件内容："+value.toString()+"");
        }
        reader.close();
    }
}

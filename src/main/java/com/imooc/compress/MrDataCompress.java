package com.imooc.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 数据压缩格式案例
 *
 * Created by xuwei
 */
public class MrDataCompress {
    /**
     * Map阶段
     */
    public static class MyMapper extends Mapper<LongWritable, Text,Text,LongWritable>{
        @Override
        protected void map(LongWritable k1, Text v1, Context context)
                throws IOException, InterruptedException {
            String[] words = v1.toString().split(" ");
            for (String word : words) {
                Text k2 = new Text(word);
                LongWritable v2 = new LongWritable(1L);
                context.write(k2,v2);
            }
        }
    }


    /**
     * Reduce阶段
     */
    public static class MyReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
        @Override
        protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context)
                throws IOException, InterruptedException {
            long sum = 0L;
            for(LongWritable v2: v2s){
                sum += v2.get();
            }

            Text k3 = k2;
            LongWritable v3 = new LongWritable(sum);
            context.write(k3,v3);
        }
    }

    /**
     * 组装Job=Map+Reduce
     */
    public static void main(String[] args) {
        try{
            //指定Job需要的配置参数
            Configuration conf = new Configuration();
            //解析命令行中通过-D传递过来的参数，添加到conf中
            String[] remainingArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

            //创建一个Job
            Job job = Job.getInstance(conf);

            //注意了：这一行必须设置，否则在集群中执行的时候是找不到WordCountJob这个类的
            job.setJarByClass(MrDataCompress.class);
            
            //指定输入路径（可以是文件，也可以是目录）
            FileInputFormat.setInputPaths(job,new Path(remainingArgs[0]));
            //指定输出路径(只能指定一个不存在的目录)
            FileOutputFormat.setOutputPath(job,new Path(remainingArgs[1]));

            //指定map相关的代码
            job.setMapperClass(MyMapper.class);
            //指定k2的类型
            job.setMapOutputKeyClass(Text.class);
            //指定v2的类型
            job.setMapOutputValueClass(LongWritable.class);


            //指定reduce相关的代码
            job.setReducerClass(MyReducer.class);
            //指定k3的类型
            job.setOutputKeyClass(Text.class);
            //指定v3的类型
            job.setOutputValueClass(LongWritable.class);

            //提交job
            job.waitForCompletion(true);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}

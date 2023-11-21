package com.imooc.compress;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 生成测试数据
 * Created by xuwei
 */
public class GenerateDat {
    public static void main(String[] args) throws Exception{
        String fileName = "D:\\words.dat";
        System.out.println("start: 开始生成2G文件->"+fileName);
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName));
        int num = 0;
        while(num<75000000){
            bfw.write("hello_"+num+" you_"+num);
            bfw.newLine();
            num ++;
            if(num%10000==0){
                bfw.flush();
            }
        }
        System.out.println("end: 2G文件已生成");
    }
}

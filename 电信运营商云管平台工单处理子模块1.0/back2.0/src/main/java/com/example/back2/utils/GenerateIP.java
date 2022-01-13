package com.example.back2.utils;

import java.util.Random;

//获取随机生成的IP地址
public class GenerateIP {
    public String createip(){
        String ip = "";
        Random r = new Random();
        for (int i = 0;i < 3;i++){
            int temp = r.nextInt(256) - 1;
            ip += temp;
            ip += '.';
        }
        ip += r.nextInt(256) - 1;
        return ip;
    }
}

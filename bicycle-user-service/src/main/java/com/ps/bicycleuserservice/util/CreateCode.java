package com.ps.bicycleuserservice.util;

import java.util.Random;

public class CreateCode {

    //生成验证码
    public static   String createCode(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        System.out.println(result);
        return result;
    }
}

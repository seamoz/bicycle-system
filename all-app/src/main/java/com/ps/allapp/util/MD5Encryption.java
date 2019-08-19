package com.ps.allapp.util;


/**
 * @description: 密码加密封装类
 * @author: 谭倾云
 * @create: 2019/08/15
 */
public class MD5Encryption {

    /**
     * @Description 对传过来的密码进行MD5加密   加盐方式:us+密码+er
     * @param password 加密的密码
     * @return String 返回加密的密码
     * */
    public static String encryption(String password){
        StringBuilder newPassword = new StringBuilder();

        //加盐一
        newPassword.append("us");

        //主密码
        newPassword.append(password);

        //加盐二
        newPassword.append("er");

        return MD5Util.getMd5Code(newPassword.toString());
    }
}

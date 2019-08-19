package com.ps.bicycleuserservice.util;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/17
 */
public class Regexs {

    /**
     * 2 *正则表达式验证手机
     * 3
     */
    public static boolean orPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || "".equals(phoneNumber)) {
            return false;
        }
        String regex = "^1[3|4|5|8][0-9]\\d{8}$";
        return phoneNumber.matches(regex);
    }

    /**
     * 正则表达式验证邮箱
     */
    public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
}

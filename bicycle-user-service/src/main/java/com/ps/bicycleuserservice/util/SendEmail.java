package com.ps.bicycleuserservice.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class SendEmail {

    public static void setEmail(String toEmail,String verifyCode) {
        // 1.
        HtmlEmail email = new HtmlEmail();

        // 2.发送人,使用什么类型的邮箱
        // 配置信息
        email.setHostName("smtp.qq.com");

        try {
            email.setFrom("2803334007@qq.com");
            email.setAuthentication("2803334007@qq.com","urubzgvsgfdvdcci");

            email.setCharset("UTF-8");

            email.setSubject("邮箱验证码");
//        Q6o32cmvIf3NLwLKbq0wGQ
//        6hi4pGf8P3T7ChIrpvx1ow
//        26CSjfe5vpoqRRI8lkx/Ag==
//                v4ga6FIQa7Ypmq+OTYEx4Q==

            //对发送邮箱的信息进行加密处理
            //获取加解密的秘钥
    /*    String passwordKey = AESUtil.generateKey();
        //密码AESUtil加密
        System.out.println("发邮箱的key="+passwordKey);
        //对称加密
        String encodeUser = AESUtil.encode(passwordKey, "sadas");
        System.out.println("加密后userName："+encodeUser);
*/

            //我邮箱填写的发送内容
            email.setHtmlMsg("尊敬的用户: 你的验证码为:"+verifyCode);

            // 3 接收的用户邮箱
            email.addTo(toEmail);

            // 4.发送邮箱
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}

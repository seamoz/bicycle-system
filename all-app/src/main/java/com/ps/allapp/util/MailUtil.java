package com.ps.allapp.util;

import com.ps.allapp.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 邮箱工具类
 * @author: 谭倾云
 * @create: 2019/08/14
 */
@Slf4j
public class MailUtil{

    public static boolean send(Mail mail) {
        // TODO
        /**
         * 发邮箱的人
         * */
        String from = "2387995324@qq.com";
        /**
         * 我也不知道这是什么
         * */
        int port = 25;
        /**
         * 发送格式  指明是一qq发送邮箱
         * */
        String host = "smtp.qq.com";
        /**
         * QQ邮箱 SMTP服务密码
         * */
        String pass = "cfhgdiacsrmadjef";

        /**
         * 好像没什么用 乱填的
         * */
        String nickname = "2387995324@qq.com";

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers()));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers()) + "失败", e);
            return false;
        }
    }
}

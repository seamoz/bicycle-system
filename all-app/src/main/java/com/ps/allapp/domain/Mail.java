package com.ps.allapp.domain;

import lombok.Data;

import java.util.Set;

/**
 * @description: 自定义 邮箱bean
 * @author: 谭倾云
 * @create: 2019/08/14
 */
@Data
public class Mail {

    /**
     * 主板
     * */
    private String subject;

    /**
     * 发送消息的内容
     * */
    private String message;

    /**
     * 发送给那些用户的集合   存放的是用户的邮箱地址
     * */
    private Set<String> receivers;
}

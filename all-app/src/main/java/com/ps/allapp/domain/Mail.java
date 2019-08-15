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
    private String subject;

    private String message;

    private Set<String> receivers;
}

package com.ps.allapp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service")
public interface UserService {


    /**
     * 接口例子，所有模块controller需要暴露的接口实现相应的service接口，重写方法
     * @return
     */
    @RequestMapping("/user/test")
    String test();
}

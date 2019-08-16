package com.ps.bicycleh5app.controller.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "user-service")
public interface UserService {

    /**
     * 接口例子，所有模块controller需要暴露的接口实现相应的service接口，重写方法
     * @return
     */
    @RequestMapping("/user/test")
    String test(String p1,String p2);
}

package com.ps.bicycleh5app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@FeignClient("user-service")
public interface UserService {

    @RequestMapping("/hello")
    String hello();

}

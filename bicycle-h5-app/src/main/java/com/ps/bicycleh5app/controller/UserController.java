package com.ps.bicycleh5app.controller;

import com.ps.bicycleh5app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/demo01")
    public String hello(){
        userService.hello();
        return " - -";
    }




}

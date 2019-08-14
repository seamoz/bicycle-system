package com.ps.bicycleuserservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@RestController
public class UserController {

    @RequestMapping("/hello")
    public void hello(){
        System.out.println("userController hello");
    }



}

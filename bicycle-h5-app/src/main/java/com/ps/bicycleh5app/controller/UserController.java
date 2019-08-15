package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @GetMapping("/confidential-payment")
    public Result confidentialPayment(@RequestParam("userId") int userId,
                                      @RequestParam("password") String password){
        Result result = userService.confidentialPayment(userId, password);

        return result;
    }



}

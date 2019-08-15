package com.ps.bicycleuserservice.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.bicycleuserservice.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/hello")
    public void hello(){
        System.out.println("userController hello");
    }

    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @GetMapping("/confidential-payment")
    public Result confidentialPayment(@RequestParam("userId") int userId,@RequestParam("password") String password){

        Result result  = userServiceImpl.confidentialPayment(userId,password);
        return result;
    }


}

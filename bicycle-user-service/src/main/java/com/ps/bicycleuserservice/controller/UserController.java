package com.ps.bicycleuserservice.controller;

import com.ps.allapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserService {

    @Override
    @RequestMapping("/user/test")
    public String test() {
        return "user Controller!";
    }
}

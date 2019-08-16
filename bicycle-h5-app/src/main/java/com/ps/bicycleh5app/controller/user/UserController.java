package com.ps.bicycleh5app.controller.user;

import com.ps.allapp.domain.User;
import com.ps.bicycleh5app.controller.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/h5")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        User user = new User();
        return userService.test("","");
    }
}

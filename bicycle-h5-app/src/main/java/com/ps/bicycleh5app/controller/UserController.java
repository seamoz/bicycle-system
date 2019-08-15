package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //我的里程查询
    @GetMapping("/queryRoute")
    public Result queryRoute(@RequestParam("userId") int userId){
        Result queryRoute = userService.queryRoute(userId);
        return queryRoute;
    }

    //查询订单详情
    @GetMapping("/userOrder")
    public Result userOrder(@RequestParam("id") int id){
        return  userService.userOrder(id);
    }


    //个人信息查看
    @GetMapping("/queryPersonage/{userId}")
    public Result queryPersonage(@PathVariable("userId") int userId){
     Result queryPersonage =  userService.queryPersonage(userId);
        return queryPersonage;
    }

    //修改邮箱
    @RequestMapping("/updateEmail")
    public Result updateEmail(@RequestParam("id") int id,@RequestParam ("email") String email){
        Result result = userService.updateMailbox(id, email);
        return result;
    }

    //发送验证码
    @RequestMapping("/sendCodeToEmail")
    public Result sendCodeToEmail(@RequestParam("id") int id,@RequestParam ("email") String email){
        return userService.sendCodeToEmail(id, email);
    }

    //添加邮箱
    @RequestMapping("/addEmail")
    public Result addEmail(@RequestParam("id") int id,@RequestParam ("email") String email){
        Result result = userService.addEmail(id, email);
        return result;
    }

    //添加手机号
    @RequestMapping("/addPhone")
    public Result addPhone(@RequestParam("id") int id,@RequestParam ("phone") String phone){
        Result addPhone = userService.addPhone(id,phone);
        return addPhone;
    }

    //修改手机号
    @RequestMapping("/updatePhone")
    public Result updatePhone(@RequestParam("id") int id,@RequestParam ("phone") String phone){
        Result result = userService.updatePhone(id, phone);
        return result;
    }


}

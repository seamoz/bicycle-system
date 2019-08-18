package com.ps.bicycleuserservice.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.Message;
import com.ps.bicycleuserservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 用户controller
 * @author: 谭倾云
 * @create: 2019/08/14
 */
@RestController
public class UserController {
    /**
     * user 的 service 类
     * */
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    //我的里程查询
    @GetMapping("/queryRoute")
    public Result queryRoute(@RequestParam("userId") int userId){
        Result queryRoute =  userServiceImpl.queryRoute(userId);
        return queryRoute;
    }

    //查询订单详情
    @GetMapping("/userOrder")
    public Result userOrder(@RequestParam("id") int id){
        Result userOrder =  userServiceImpl.userOrder(id);
        return userOrder;
    }

    //个人信息查看
    @GetMapping("/queryPersonage/{userId}")
    public Result queryPersonage(@PathVariable("userId") int userId){
        Result queryPersonage = userServiceImpl.queryPersonage(userId);
        return queryPersonage;
    }

    //修改邮箱
    @RequestMapping("/updateEmail")
    public Result updateEmail(@RequestParam("id") int id,@RequestParam ("email") String email){
        Result result = userServiceImpl.updateMailbox(id, email);
        return result;
    }

    //添加邮箱
    @RequestMapping("/addEmail")
    public Result addEmail(@RequestParam("id") int id,@RequestParam ("email") String email){
        Result result = userServiceImpl.addEmail(id, email);
        return result;
    }



    //发送验证码至邮箱
    @RequestMapping("/sendCodeToEmail")
    public Result sendCodeToEmail(@RequestParam int id,@RequestParam String email){
        return userServiceImpl.sendCodeToEmail(id , email);
    }

    /**
     * @Description 根据电话修改密码
     * @param phone 用户的电话号码
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */

    @RequestMapping("/updatePasswordByPhone")
    public Message<String> updatePasswordByPhone(String phone,String newPassword){
        System.out.println("newPassword:"+newPassword);
        return userService.updatePasswordByPhone(phone,newPassword);
    }

    /**
     * @Description 根据电话修改密码
     * @param email 用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/updatePasswordByEmail")
    public Message<String> updatePasswordByEmail(String email,String newPassword){
        return userService.updatePasswordByEmail(email,newPassword);
    }

    /**
     * @Description 给邮箱发送短信
     * @param email 用户的邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/verification")
    public Message<String> verificationCodes(String email){
        return userService.verificationCodes(email);
    }

    //添加手机号
    @RequestMapping("/addPhone")
    public Result addPhone(@RequestParam("id") int id,@RequestParam ("phone") String phone){
        Result addPhone = userServiceImpl.addPhone(id,phone);
        return addPhone;
    }

    //修改手机号
    @RequestMapping("/updatePhone")
    public Result updatePhone(@RequestParam("id") int id,@RequestParam ("phone") String phone){
        Result result = userServiceImpl.updatePhone(id, phone);
        return result;
    }
    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("verificationPhone")
    public Message<String> verificationCodesPhone(String phone){
        return userService.verificationCodesPhone(phone);
    }

    /**
     * @Description 根据电话修改密码
     * @param password 用户保存的密码
     * @param phone 用户手机号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/setPasswordByUserPhone")
    Message<String> setPasswordByUserPhone(String phone, String password){
        return userService.setPasswordByUserPhone(phone,password);
    }

    /**
     * @Description 判断验证码正不正确 正确就注册用户
     * @param verificationCode 验证码
     * @param phone 手机号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/judgePhoneVerificationCode")
    Message<String> judgePhoneVerificationCode(String phone,String verificationCode){
        return userService.judgeVerificationCode(phone,verificationCode);
    }

    /**
     * @Description 根据邮箱注册用户
     * @param verificationCode 验证码
     * @param email 邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/judgeEmailVerificationCode")
    Message<String> judgeEmailVerificationCode(String userName,String password,String email,String verificationCode){
        return userService.judgeEmailVerificationCode(userName,password,email,verificationCode);
    }
}

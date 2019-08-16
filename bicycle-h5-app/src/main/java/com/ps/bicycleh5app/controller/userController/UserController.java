package com.ps.bicycleh5app.controller.userController;

import com.ps.allapp.domain.Message;
import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 登录controller
 * @author: 谭倾云
 * @create: 2019/08/13
=======
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

    /**
     * @Description 根据电话修改密码
     * @param map 一个存放了 phone(用户的电话号码) 和 newPassword(用户的新密码) 的容器
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/updatePasswordByPhone")
    public Message<String> updatePasswordByPhone(@RequestBody Map<String,String> map){
        return userService.updatePasswordByPhone(map.get("phone"),map.get("newPassword"));
    }

    /**
     * @Description 根据邮箱修改密码
     * @param email 用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/updatePasswordByEmail")
    public Message<String> updatePasswordByEmail(String email,String newPassword){
        return userService.updatePasswordByEmail(email,newPassword);
    }

    /**
     * @Description 给邮箱发送验证码
     * @param email 用户的邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/verificationEmail")
    public Message<String> verificationCodesEmail(String email){
        return userService.verificationCodes(email);
    }

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/verificationPhone")
    public Message<String> verificationCodesPhone(String phone){
        return userService.verificationCodesPhone(phone);
    }

    /**
     * @Description 根据电话设置密码
     * @param password 用户保存的密码
     * @param phone 用户手机号码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/setPasswordByUserPhone")
    public Message<String> setPasswordByUserPhone(String phone,String password){
        return userService.setPasswordByUserPhone(phone,password);
    }

    /**
     * @Description 判断验证码正不正确 正确就创建用户
     * @param phone 用户手机号码
     * @param verificationCode 验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/judgePhoneVerificationCode")
    public Message<String> judgePhoneVerificationCode(String phone,String verificationCode){
        return userService.judgePhoneVerificationCode(phone,verificationCode);
    }

    /**
     * @Description 判断验证码正不正确 正确就创建用户
     * @param verificationCode 验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/judgeEmailVerificationCode")
    public Message<String> judgeEmailVerificationCode(@RequestParam("userName") String userName,
                                                      @RequestParam("password") String password,
                                                      @RequestParam("email") String email,
                                                      @RequestParam("verificationCode") String verificationCode){
        return userService.judgeEmailVerificationCode(userName,password,email,verificationCode);
    }

}

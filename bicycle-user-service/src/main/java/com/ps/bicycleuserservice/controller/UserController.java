package com.ps.bicycleuserservice.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.Message;
import com.ps.bicycleuserservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/verificationPhone")
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

    /**
     * @Description 根据用户id查找钱包
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/main")
    Message getWalletMain(@RequestParam("userId") Integer userId){
        return userService.getWalletMain(userId);
    }

    /**
     * @Description 根据用户id查找优惠券
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/discount")
    public Message getDiscount(@RequestParam("userId") Integer userId){
        return userService.getDiscount(userId);
    }

    /**
     * @Description 根据用户id查询支付记录
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/payrecord")
    public Message getPayrecord(@RequestParam("userId") Integer userId){
        return userService.getPayrecord(userId);
    }

    /**
     * @Description 用户充值
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @PostMapping("/my/recharge")
    public Message recharge(@RequestParam("userId") Integer userId,@RequestParam("payType") String payType ,@RequestParam("payMoney") float payMoney) {
        return userService.recharge(userId,payType,payMoney);
    }
}

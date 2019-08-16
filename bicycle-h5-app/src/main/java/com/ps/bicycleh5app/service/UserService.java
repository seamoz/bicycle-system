package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.ps.allapp.domain.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@FeignClient("USER-SERVICE")
public interface UserService {

    /**
     * @Description 根据电话修改密码
     * @param phone 用户的电话号码
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */

    @RequestMapping("/updatePasswordByPhone")
    Message<String> updatePasswordByPhone(@RequestParam("phone") String phone, @RequestParam("newPassword") String newPassword);

    /**
     * @Description 根据电话修改密码
     * @param email 用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/updatePasswordByEmail")
    Message<String> updatePasswordByEmail(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword);

    /**
     * @Description 给邮箱发送短信
     * @param email 用户的邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/verification")
    Message<String> verificationCodes(@RequestParam("email") String email);

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/verificationPhone")
    Message<String> verificationCodesPhone(@RequestParam("phone") String phone);

    /**
     * @Description 根据电话修改密码
     * @param password 用户保存的密码
     * @param phone 用户手机号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/setPasswordByUserPhone")
    Message<String> setPasswordByUserPhone(@RequestParam("phone") String phone, @RequestParam("password")String password);

    /**
     * @Description 判断验证码正不正确 正确就创建用户
     * @param phone 用户手机号码
     * @param verificationCode 验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/judgePhoneVerificationCode")
    Message<String> judgePhoneVerificationCode(@RequestParam("phone") String phone, @RequestParam("verificationCode") String verificationCode);

    /**
     * @Description 判断验证码正不正确 正确就创建用户
     * @param email 邮箱地址
     * @param verificationCode 验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/judgeEmailVerificationCode")
    Message<String> judgeEmailVerificationCode(@RequestParam("userName") String userName,
                                               @RequestParam("password") String password,
                                               @RequestParam("email") String email,
                                               @RequestParam("verificationCode") String verificationCode);

    /**
     * @Description 根据用户id查找钱包
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/main")
    Message getWalletMain(@RequestParam("userId") Integer userId);

    /**
     * @Description 根据用户id查找优惠券
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/discount")
    Message getDiscount(@RequestParam("userId") Integer userId);

    /**
     * @Description 根据用户id查询支付记录
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/payrecord")
    Message getPayrecord(@RequestParam("userId") Integer userId);

    /**
     * @Description 用户充值
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @PostMapping("/my/recharge")
    public Message recharge(@RequestParam("userId") Integer userId,@RequestParam("payType") String payType ,@RequestParam("payMoney") float payMoney);

    }

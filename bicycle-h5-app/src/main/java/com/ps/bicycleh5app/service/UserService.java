package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ps.allapp.domain.Message;
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
     * 登录（校验了是否是用户名、手机号、邮箱）
     * @param userData
     * @param password
     * @return
     */
    @RequestMapping("/logIn")
    Result logIn(@RequestParam("username") String userData, String password);

    //我的里程查询
    @GetMapping("/queryRoute")
    Result queryRoute(@RequestParam("userId") int userId);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/userOrder")
    Result userOrder(@RequestParam("id") int id);

    /**
     * 个人信息
     * @param userId
     * @return
     */
    @GetMapping("/queryPersonage/{userId}")
    Result queryPersonage(@PathVariable("userId") int userId);

    /**
     * 修改邮箱
     * @param id
     * @param email
     * @return
     */
    @RequestMapping("/updateMailbox")
    Result updateMailbox(@RequestParam("id") int id,@RequestParam("email") String email);

    /**
     * 添加邮箱
     * @param id
     * @param email
     * @return
     */
    @RequestMapping("/addEmail")
    Result addEmail(@RequestParam("id") int id,@RequestParam("email") String email);

    /**
     * 根据用户id,email发送验证码,并保存在验证码至数据库
     * @param id
     * @param email
     * @return
     */
    @RequestMapping("/sendCodeToEmail")
    Result sendCodeToEmail(@RequestParam int id,@RequestParam String email);

    /**
     * 添加手机号
     */
    @RequestMapping("/addPhone")
    Result addPhone(@RequestParam int userId,@RequestParam("phone") String phone);

    /**
     * 修改手机号
     * @param id
     * @param phone
     * @return
     */
    @RequestMapping("/updatePhone")
    Result updatePhone(@RequestParam("id") int id,@RequestParam("phone") String phone);


    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @GetMapping("/confidential-payment")
    Result confidentialPayment(@RequestParam("userId") int userId, @RequestParam("password") String password);

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
}

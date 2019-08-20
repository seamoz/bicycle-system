package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Verify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.ps.allapp.domain.Message;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@FeignClient("USER-SERVICE")
public interface UserService {

    /**
     * 手机验证码登录校验
     * @param verify
     * @return
     */
    @RequestMapping("/logInByPhoneAndVerify")
    Result logInByPhoneAndVerify(@RequestBody Verify verify);

    /**
     * 发送验证码
     * @param verify
     * @return
     */
    @RequestMapping("/sendVerify")
    Result sendVerify(@RequestBody Verify verify);

    /**
     * 登录（校验了是否是用户名、手机号、邮箱）
     * @param user
     * @return
     */
    @RequestMapping("/logIn")
    Result logIn(@RequestBody User user);

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
    Message<String> verificationCodes(@RequestParam("email") String email, @RequestParam("state") Integer state);

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/verificationPhone")
    Message<String> verificationCodesPhone(@RequestParam("phone") String phone, @RequestParam("state") Integer state);

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
     * @Description 判断验证码正不正确 正确就创建用户
     * @param id 用户的id
     * @param newEmail 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/updateEmailById")
    Message<String> updateEmailById(@RequestParam("id") String id,
                                    @RequestParam("newEmail") String newEmail,
                                    @RequestParam("verificationCode") String verificationCode);

    /**
     * @Description 添加邮箱
     * @param id 用户的id
     * @param newEmail 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/addEmailById")
    Message<String> addEmailById(@RequestParam("id") String id,
                                 @RequestParam("newEmail") String newEmail,
                                 @RequestParam("verificationCode") String verificationCode);

    /**
     * @Description 修改电话
     * @param id 用户的id
     * @param newPhone 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/updatePhoneById")
    Message<String> updatePhoneById(@RequestParam("id") String id,
                                    @RequestParam("newPhone") String newPhone,
                                    @RequestParam("verificationCode") String verificationCode);

    /**
     * @Description 修改电话
     * @param id 用户的id
     * @param newPhone 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @RequestMapping("/addPhoneById")
    Message<String> addPhoneById(@RequestParam("id") String id,
                                 @RequestParam("newPhone") String newPhone,
                                 @RequestParam("verificationCode") String verificationCode);

    /**
     * @param phone         用户要修改的邮箱地址
     * @return Message<String> 返回的对象提示
     * @Description 查询电话存不存在
     */
    @RequestMapping("/judgePhone")
    Message<String> judgePhone(@RequestParam("phone") String phone);

    /**
     * @Description 根据用户id查找钱包
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/main")
    public Message getWalletMain(@RequestParam("userId") Integer userId);


    /**
     * @Description 根据用户id查找优惠券
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/discount")
    public Message getDiscount(@RequestParam("userId") Integer userId);

    /**
     * @Description 根据用户id查询支付记录
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/payrecord")
    public Message getPayrecord(@RequestParam("userId") Integer userId);

    /**
     * @Description 用户充值
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/recharge")
    public Message recharge(@RequestParam("userId") Integer userId,@RequestParam("payType") String payType ,@RequestParam("payMoney") float payMoney);

    /**
     * @param key 用户的邮箱或手机号
     * @return Message<String> 返回的对象提示
     * @Description 重置密码  判断验证存不存在
     */
    @PostMapping("/resetPassword")
    public Message<String> resetPassword(@RequestParam("key") String key,
                                         @RequestParam("password") String password,
                                         @RequestParam("verificationCode") String verificationCode);

    /**
     * @param key 用户的邮箱或手机号
     * @return Message<String> 返回的对象提示
     * @Description 重置密码
     */
    @PostMapping("/setPassword")
    public Message<String> setPassword(@RequestParam("key") String key,
                                @RequestParam("password") String password,
                                @RequestParam("index") Integer index);
}

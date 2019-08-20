package com.ps.bicycleuserservice.controller;

import com.ps.allapp.domain.*;
import com.ps.allapp.domain.Result;
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

    @RequestMapping("/logInByPhoneAndVerify")
    public Result logInByPhoneAndVerify(@RequestBody Verify verify){
        return userService.logInByPhoneAndVerify(verify);
    }

    @RequestMapping("/sendVerify")
    public Result sendVerify(@RequestBody Verify verify){
        return userService.sendVerify(verify);
    }

    @RequestMapping("/logIn")
    public Result logIn(@RequestBody User user){
        return userService.logIn(user.getUsername(), user.getPassword());
    }


    //我的里程查询
    @GetMapping("/queryRoute")
    public Result queryRoute(@RequestParam("userId") int userId) {
        Result queryRoute = userServiceImpl.queryRoute(userId);
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
    public Result updateEmail(@RequestParam("id") int id, @RequestParam("email") String email) {
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
    public Result sendCodeToEmail(@RequestParam int id, @RequestParam String email) {
        return userServiceImpl.sendCodeToEmail(id, email);
    }


    /**
     * @param phone       用户的电话号码
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * @Description 根据电话修改密码
     */
    @RequestMapping("/updatePasswordByPhone")
    public Message<String> updatePasswordByPhone(String phone, String newPassword) {
        System.out.println("newPassword:" + newPassword);
        return userService.updatePasswordByPhone(phone, newPassword);
    }

    /**
     * @param email       用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * @Description 根据电话修改密码
     */
    @RequestMapping("/updatePasswordByEmail")
    public Message<String> updatePasswordByEmail(String email, String newPassword) {
        return userService.updatePasswordByEmail(email, newPassword);
    }

    /**
     * @Description 给邮箱发送短信
     * @param email 用户的邮箱地址
     * @return Message<String> 返回的对象提示
     * @Description 给邮箱发送短信
     */
    @RequestMapping("/verification")
    public Message<String> verificationCodes(String email, Integer state) {
        return userService.verificationCodes(email, state);
    }

    /**
     * 添加手机号
     * @param id
     * @param phone
     * @return
     */
    @RequestMapping("/addPhone")
    public Result addPhone(@RequestParam("id") int id, @RequestParam("phone") String phone) {
        Result addPhone = userServiceImpl.addPhone(id, phone);
        return addPhone;
    }

    /**
     * 修改手机号
     * @param id
     * @param phone
     * @return
     */
    @RequestMapping("/updatePhone")
    public Result updatePhone(@RequestParam("id") int id, @RequestParam("phone") String phone) {
        Result result = userServiceImpl.updatePhone(id, phone);
        return result;
    }
    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * @Description 给手机发送短信
     */
    @RequestMapping("/verificationPhone")
    public Message<String> verificationCodesPhone(String phone, Integer state) {
        return userService.verificationCodesPhone(phone, state);
    }

    /**
     * @Description 根据电话修改密码
     * @param password 用户保存的密码
     * @param phone    用户手机号码
     * @return Message<String> 返回的对象提示
     * @Description 根据电话修改密码
     */
    @RequestMapping("/setPasswordByUserPhone")
    Message<String> setPasswordByUserPhone(String phone, String password) {
        return userService.setPasswordByUserPhone(phone, password);
    }

    /**
     * @Description 判断验证码正不正确 正确就注册用户
     * @param verificationCode 验证码
     * @param phone            手机号码
     * @return Message<String> 返回的对象提示
     * @Description 判断验证码正不正确 正确就注册用户
     */
    @RequestMapping("/judgePhoneVerificationCode")
    Message<String> judgePhoneVerificationCode(String phone, String verificationCode) {
        return userService.judgeVerificationCode(phone, verificationCode);
    }

    /**
     * @Description 根据邮箱注册用户
     * @param verificationCode 验证码
     * @param email            邮箱地址
     * @return Message<String> 返回的对象提示
     * @Description 根据邮箱注册用户
     */
    @RequestMapping("/judgeEmailVerificationCode")
    Message<String> judgeEmailVerificationCode(String userName, String password, String email, String verificationCode) {
        return userService.judgeEmailVerificationCode(userName, password, email, verificationCode);
    }

    /**
     * @param id               用户的id
     * @param newEmail         用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * @Description 修改用户的邮箱
     */
    @RequestMapping("/updateEmailById")
    public Message<String> updateEmailById(String id, String newEmail, String verificationCode) {
        return userService.updateEmailById(id, newEmail, verificationCode);
    }

    /**
     * @Description 根据用户id查找钱包
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/main")
    public Message getWalletMain(@RequestParam("userId") Integer userId){
        System.out.println(userId);
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
    @GetMapping("/my/recharge")
    public Message recharge(@RequestParam("userId") Integer userId,@RequestParam("payType") String payType ,@RequestParam("payMoney") float payMoney){
        return userService.recharge(userId,payType,payMoney);
    }


    /**
     * @param id               用户的id
     * @param newEmail         用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * @Description 添加邮箱
     */
    @RequestMapping("/addEmailById")
    Message<String> addEmailById(@RequestParam("id") String id,
                                 @RequestParam("newEmail") String newEmail,
                                 @RequestParam("verificationCode") String verificationCode) {
        return userService.addEmailById(id, newEmail, verificationCode);
    }

    /**
     * @param id               用户的id
     * @param newPhone         用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * @Description 修改电话
     */
    @RequestMapping("/updatePhoneById")
    Message<String> updatePhoneById(@RequestParam("id") String id,
                                    @RequestParam("newPhone") String newPhone,
                                    @RequestParam("verificationCode") String verificationCode) {
        return userService.updatePhoneById(id, newPhone, verificationCode);
    }

    /**
     * @param id               用户的id
     * @param newPhone         用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * @Description 修改电话
     */
    @RequestMapping("/addPhoneById")
    Message<String> addPhoneById(String id, String newPhone, String verificationCode) {
        return userService.addPhoneById(id, newPhone, verificationCode);
    }

    /**
     * @param phone         用户要修改的邮箱地址
     * @return Message<String> 返回的对象提示
     * @Description 查询电话存不存在
     */
    @RequestMapping("/judgePhone")
    Message<String> judgePhone(String phone) {
        return userService.judgePhone(phone);
    }

    /**
     * @param key 用户的邮箱或手机号
     * @return Message<String> 返回的对象提示
     * @Description 重置密码  判断验证存不存在
     */
    @PostMapping("/resetPassword")
    public Message<String> resetPassword(String key, String verificationCode){
        return userService.resetPassword(key,verificationCode);
    }

    /**
     * @param key 用户的邮箱或手机号
     * @return Message<String> 返回的对象提示
     * @Description 重置密码
     */
    @PostMapping("/setPassword")
    public Message<String> setPassword(String key, String password, Integer index){
        return userService.setPassword(key,password,index);
    }
}
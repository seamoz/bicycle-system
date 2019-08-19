package com.ps.bicycleh5app.controller.userController;

import com.ps.allapp.domain.Message;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Verify;
import com.ps.bicycleh5app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 登录controller
 * @author: 谭倾云
 * @create: 2019/08/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        return userService.logIn(user);
    }

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
    @GetMapping("/sendMessage")
    public Message<String> verificationCodesEmail(String email,Integer state){
        return userService.verificationCodes(email,state);
    }

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/sendPhoneMessage")
    public Message<String> verificationCodesPhone(String phone,Integer state){
        return userService.verificationCodesPhone(phone,state);
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
    /**
     * @Description 根据用户id查找钱包
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/my/main")
    @CrossOrigin
    public Message getWalletMain(@RequestParam("userId") Integer userId){
        System.out.println(userId);
        return userService.getWalletMain(userId);
    }



     /* @Description 根据用户id查找优惠券
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
    public Message getPayrecord(@RequestParam("userId") Integer userId) {
        return userService.getPayrecord(userId);
    }

    /**
     * @Description 用户充值
     * @param userId 用户id
     * @return Message<String> 返回的对象提示
     * */
    @PostMapping("/my/recharge")
    public Message recharge(@RequestParam("userId") Integer userId,@RequestParam("payType") String payType ,@RequestParam("payMoney") float payMoney){
        return userService.recharge(userId,payType,payMoney);
    }

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
    /**
     * @Description 修改用户的邮箱
     * @param id 用户的id
     * @param newEmail 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/updateEmailById")
    public Message<String> updateEmailById(String id,String newEmail,String verificationCode){
        return userService.updateEmailById(id,newEmail,verificationCode);
    }

    /**
     * @Description 添加邮箱
     * @param id 用户的id
     * @param newEmail 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/addEmailById")
    public Message<String> addEmailById(String id,String newEmail,String verificationCode){
        return userService.addEmailById(id,newEmail,verificationCode);
    }

    /**
     * @Description 修改电话
     * @param id 用户的id
     * @param newPhone 用户要修改的邮箱地址
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/updatePhoneById")
    public Message<String> updatePhoneById(String id,String newPhone,String verificationCode){
        return userService.updatePhoneById(id,newPhone,verificationCode);
    }

    /**
     * @Description 添加电话
     * @param id 用户的id
     * @param newPhone 用户要添加的电话
     * @param verificationCode 邮箱验证码
     * @return Message<String> 返回的对象提示
     * */
    @GetMapping("/addPhoneById")
    public Message<String> addPhoneById(String id,String newPhone,String verificationCode){
        return userService.addPhoneById(id,newPhone,verificationCode);
    }

    /**
     * @param phone         用户要修改的邮箱地址
     * @return Message<String> 返回的对象提示
     * @Description 查询电话存不存在
     */
    @GetMapping("/judgePhone")
    public Message<String> judgePhone(String phone) {
        return userService.judgePhone(phone);
    }
}

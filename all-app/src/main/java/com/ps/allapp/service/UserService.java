package com.ps.allapp.service;

import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserService {

    /**
     * 登录（校验了是否是用户名、手机号、邮箱）
     * @param userData
     * @param password
     * @return
     */
    Result logIn(String userData, String password);

    /**
     * 手机号码密码登录
     * @param phone
     * @param password
     * @return
     */
    Result logInByPhoneAndPassword(String phone, String password);

    /**
     * 邮箱密码登录
     * @param email
     * @param password
     * @return
     */
    Result logInByEmailAndPassword(String email, String password);

    /**
     * 用户名密码登录
     * @param userName
     * @param password
     * @return
     */
    Result logInByUserNameAndPassword(String userName, String password);

    /**
     * 接口例子，所有模块controller需要暴露的接口实现相应的service接口，重写方法
     * @return
     */

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

}

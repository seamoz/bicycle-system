package com.ps.allapp.service;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("user-service")
public interface UserService {


    /**
     * 接口例子，所有模块controller需要暴露的接口实现相应的service接口，重写方法
     * @return
     */
     /*   @RequestMapping("/user/test")
        String test();*/

    /**
     * 查询我的行程
     * @return
     */
    List<ShareBicycle> queryRoute(int userId);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    Integer userOrder(int id);

    /**
     * 个人信息
     * @param userId
     * @return
     */
    User queryPersonage(int userId);



}

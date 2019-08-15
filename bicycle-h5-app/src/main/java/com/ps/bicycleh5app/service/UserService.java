package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/14
 */
@FeignClient("user-service")
public interface UserService {

    @RequestMapping("/hello")
    String hello();

    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @GetMapping("/confidential-payment")
    public Result confidentialPayment(@RequestParam("userId") int userId, @RequestParam("password") String password);

}

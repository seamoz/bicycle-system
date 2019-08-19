package com.ps.bicycleh5app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("billing-service")
public interface WalletService {
    /**
     * 设置支付密码
     * @param userId
     * @param payPassword
     * @return
     */
    @PostMapping("/paymentCode")
    public Integer findPay(@RequestParam("userId")Integer userId, @RequestParam("payPassword")String payPassword);
}

package com.ps.bicyclebillingsevice.controller;

import com.ps.allapp.domain.Wallet;
import com.ps.bicyclebillingsevice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 不等于0，则成功设置支付密码
     * @param userId
     * @param payPassword
     * @return
     */
    @PostMapping("/paymentCode")
    public Integer findPay(Integer userId,String payPassword){
        // 钱包主键
        Integer key = null;

        // 钱包存在？
        Integer pay = walletService.findPay(userId);
        if(pay == null) {
            // 创建钱包
            System.out.println("钱包不存在");
            Wallet wallet = new Wallet();
            walletService.insertWallet(wallet);
            key = wallet.getId();
            System.out.println("我创建了钱包");
        }

        // 设置支付密码
        Integer integer = walletService.setPayPassword(userId, payPassword);
        System.out.println("我设置了支付密码");

        return integer;
    }
}

package com.ps.bicyclebillingsevice.service;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import org.springframework.transaction.annotation.Transactional;

public interface BillingService {

    /**
     * 钱包id植入用户表
     */
    public Integer setBB(Integer userId, Integer bb);

    /**
     * 更改支付密码
     * return 1成功，0失败, 3源密码不一致
     */
    public Integer updatePassword(Integer userId,String payPassword);

    /**
     * 查询用户支付密码
     * return 支付密码
     */
    public Wallet getPayPassword(Integer userId);

    /**
     * 查询id有没有钱包
     * return id
     */
    public Integer findPay(Integer userId);

    /**
     * 创建钱包
     * return 钱包主键
     */
    public Integer insertWallet(Wallet wallet);

    /**
     * 设置支付密码
     * return 成功与否
     */
    public Integer setPayPassword(Integer userId, String payPassword);

    /**
     * 免密支付
     * @param wallet
     * @return
     */
    Result confidentialPayment(Wallet wallet);

    /**
     * 退押金、交押金
     * @param userId
     * @return
     */
    @Transactional
    Result RefundPayDeposit(int userId, String state);

    /**
     * 查询金额
     * @param userId
     * @return
     */
    Result inquiryAmount(int userId);
}

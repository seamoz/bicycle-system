package com.ps.bicyclebillingsevice.service;

import com.ps.allapp.domain.Wallet;

/**
 * 该类修改权，需向作者授权
 */
public interface WalletService {
    /**
     * 修改密码
     * return 成功与否
     */
    public Integer updatePassword(Integer userId,String payPassword);

    /**
     * 查询用户支付密码
     * return 支付密码
     */
    public String getPayPassword(Integer userId);

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
}

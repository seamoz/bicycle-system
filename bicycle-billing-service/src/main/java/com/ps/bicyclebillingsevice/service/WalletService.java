package com.ps.bicyclebillingsevice.service;

import com.ps.allapp.domain.Wallet;

public interface WalletService {
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

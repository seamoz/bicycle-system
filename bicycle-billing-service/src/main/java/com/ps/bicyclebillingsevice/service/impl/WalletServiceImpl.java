package com.ps.bicyclebillingsevice.service.impl;

import com.ps.allapp.domain.Wallet;
import com.ps.bicyclebillingsevice.mapper.WalletMapper;
import com.ps.bicyclebillingsevice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Integer updatePassword(Integer userId, String payPassword) {
        return null;
    }

    @Override
    public String getPayPassword(Integer userId) {
        return walletMapper.getPayPassword(userId);
    }

    /**
     * 查询id有没有钱包
     * return id
     */
    @Override
    public Integer findPay(Integer userId) {
        return walletMapper.findPay(userId);
    }

    @Override
    public Integer insertWallet(Wallet wallet) {
        return walletMapper.insertWallet(wallet);
    }

    @Override
    public Integer setPayPassword(Integer userId, String payPassword) {
        return walletMapper.setPayPassword(userId,payPassword);
    }
}

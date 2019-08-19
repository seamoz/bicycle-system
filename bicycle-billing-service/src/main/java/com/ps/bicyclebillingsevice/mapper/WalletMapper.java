package com.ps.bicyclebillingsevice.mapper;

import com.ps.allapp.domain.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WalletMapper {
    /**
     *
     */
    Integer setBB(Integer userId , Integer bb);

    /**
     * 查询id有没有钱包
     * return id
     */
    Integer findPay(Integer userId);

    /**
     * 创建钱包
     * return 钱包主键
     */
    Integer insertWallet(Wallet wallet);

    /**
     * 设置支付密码
     * return 成功与否
     */
    Integer setPayPassword(Integer userId, String payPassword);

    /**
     * 查询用户支付密码
     * return 支付密码
     */
    Wallet getPayPassword(Integer userId);

    /**
     * 更改密码
     * @param userId
     * @param payPassword
     * @return
     */
    Integer updatePassword(Integer userId, String payPassword);
}

package com.ps.bicyclebillingsevice.mapper;

import com.ps.allapp.domain.Payrecord;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author VP
 */
@Mapper
@Repository
public interface BillingMapper {

    /**
     *  免密支付
     * @param id
     * @param noPasswordPay
     * @return
     */
    Integer confidentialPayment(int id,int noPasswordPay);

    /**
     * 根据用户id，密码查询 钱包id
     * @param userId
     * @param password
     * @return
     */
    Integer userWalletDetails(int userId, String password);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 根据钱包ID查询钱包信息
     * @param walletId
     * @return
     */
    Wallet selectWalletById(int walletId);

    /**
     * 支付
     * @param wallet
     * @return
     */
    int updateWalletById(Wallet wallet);

    /**
     * 插入消费记录
     * @param payrecord
     * @return
     */
    int insertPayrecord(Payrecord payrecord);

    /**
     * 查询是否交押金
     * @param walletId
     * @return
     */
    String pledgeState(int walletId);

}

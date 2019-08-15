package com.ps.bicycleuserservice.mapper;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author VP
 */
@Mapper
@Repository
public interface UserMapper {

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

}

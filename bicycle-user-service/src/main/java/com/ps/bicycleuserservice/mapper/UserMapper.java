package com.ps.bicycleuserservice.mapper;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.UserAndVerify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据输入用户名密码登录
     * @param userName
     * @param password
     * @return
     */
    User logInByUserNameAndPassword(String userName, String password);

    /**
     * 根据输入邮箱密码登录
     * @param email
     * @param password
     * @return
     */
    User logInByEmailAndPassword(String email, String password);

    /**
     * 根据输入手机号密码登录
     * @param phone
     * @param password
     * @return
     */
    User logInByPhoneAndPassword(String phone, String password);

    /**
     * 根据手机号发送验证码登录
     * @param phone
     * @param verify
     * @return
     */
    UserAndVerify logInByPhoneAndVerify(String phone, String verify);

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

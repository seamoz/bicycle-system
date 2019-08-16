package com.ps.bicycleuserservice.mapper;

import com.ps.allapp.domain.*;
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
     * @Description 根据电话修改密码
     * @param phone 用户的电话号码
     * @return User 返回的用户对象
     * */
    User queryUserByPhone(String phone);

    /**
     * @Description 根据电话修改密码
     * @param phone 用户的电话号码
     * @param newPassword 用户修改的密码
     * */
    void updatePasswordByPhone(String phone, String newPassword);

    /**
     * @Description 根据邮箱查找用户
     * @param email 用户的邮箱
     * @return User 返回的用户对象
     * */
    User queryUserByEmail(String email);

    /**
     * @Description 根据邮箱修改密码
     * @param email 用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * */
    void updatePasswordByEmail(String email, String newPassword);

    /**
     * @Description 添加用户
     * @param name 用户名
     * @param phone 用户电话
     * */
    void addUserByCode(String name, String phone);

    /**
     * @Description 添加用户
     * @param userName 用户名
     * @param encryptionPassword 用户密码
     * @param email 邮箱地址
     * */
    void addUser(String userName, String encryptionPassword, String email);

    /**
     * @Description 电话发送短信进行记录
     * @param phone 用户名电话
     * @param state 短信类型
     * @param verificationCode 验证码
     * */
    void addCodeRecordPhone(String phone, Integer state, String verificationCode);

    /**
     * @Description 邮箱发送短信进行记录
     * @param email 用户邮箱地址
     * @param state 短信类型
     * @param verificationCode 验证码
     * */
    void addCodeRecordEmail(String email, Integer state, String verificationCode);

}
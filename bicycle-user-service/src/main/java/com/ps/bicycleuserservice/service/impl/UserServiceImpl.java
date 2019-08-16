package com.ps.bicycleuserservice.service.impl;

import com.ps.allapp.domain.*;
import com.ps.allapp.service.UserService;
import com.ps.allapp.util.JuheDemo;
import com.ps.allapp.util.MD5Encryption;
import com.ps.allapp.util.MD5Util;
import com.ps.allapp.util.MailUtil;
import com.ps.bicycleuserservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author VP
 */
@Service
public class UserServiceImpl{

    //redis 电话号码验证码存缓 的key
    private static final String userPhone = "phone:";
    //redis 邮箱地址验证码存缓 的key
    private static final String userEmail = "email:";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate template;

    public List<ShareBicycle> queryRoute(int userId) {
        List<ShareBicycle> shareBicycles = userMapper.queryRoute(userId);
        return shareBicycles;
    }

    public Integer userOrder(int id) {
        return userMapper.userOrder(id);
    }


    public User queryPersonage(int userId) {
        return userMapper.queryPersonage(userId);
    }

    /**
     * @Description 根据电话修改密码
     * @param phone 用户的电话号码
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> updatePasswordByPhone(String phone, String newPassword) {
        //返回对象
        Message<String> message = new Message<>();

        //根据电话号码判断有没有该用户 没有就返回提示
        User user = userMapper.queryUserByPhone(phone);
        if(user == null){
            message.setMsg("电话号码错误.");
            message.setCode(0);
            message.setState(false);
            return message;
        }

        //开始修改密码
        userMapper.updatePasswordByPhone(phone,newPassword);

        //返回修改成功提示
        message.setMsg("修改成功");
        message.setState(true);
        message.setCode(200);
        return message;
    }

    /**
     * @Description 根据电话修改密码
     * @param email 用户的邮箱地址
     * @param newPassword 用户要修改的密码
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> updatePasswordByEmail(String email, String newPassword) {
        //返回对象
        Message<String> message = new Message<>();

        //根据邮箱判断用户是否存在 诺不存在就返回提示
        User user = userMapper.queryUserByEmail(email);
        if(user == null){
            message.setCode(0);
            message.setState(false);
            message.setMsg("邮箱错误.");
            return message;
        }

        //开始修改密码
        userMapper.updatePasswordByEmail(email,newPassword);
        message.setMsg("修改成功.");
        message.setCode(200);
        message.setState(true);
        return message;
    }

    /**
     * @Description 给邮箱发送短信
     * @param email 用户的邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> verificationCodes(String email) {
        //返回信息对象
        Message<String> message = new Message<>();

        //如果邮箱以有人注册或绑定 就返回提示
        User user = userMapper.queryUserByEmail(email);
        if(user != null){
            message.setCode(0);
            message.setMsg("邮箱已注册.");
            message.setState(false);
            return message;
        }

        //发送邮箱
        new Thread(() -> {
            try {
                //邮箱工具类
                Mail mail = new Mail();
                //随机数对象
                Random random = new Random();
                //拼接验证码
                StringBuilder code = new StringBuilder();
                code.append("验证码:");
                for(int i = 0; i < 6; i++){
                    code.append(random.nextInt(9));
                }
                //设置内容
                mail.setMessage(code.toString());
                Set<String> set = new HashSet<>();
                //添加收信者
                set.add(email);
                //设置收信者
                mail.setReceivers(set);
                //标题
                mail.setSubject("注册验证码:");
                //开始发送
                MailUtil.send(mail);

                //存入redis存缓里面去90秒过期
                ValueOperations<String,String> valueOperations = template.opsForValue();
                String key = userEmail+email;
                valueOperations.set(key,code.toString().split(":")[1],90, TimeUnit.SECONDS);
                //添加一条发送信息记录
                userMapper.addCodeRecordEmail(email,0,code.toString().split(":")[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        message.setState(true);
        message.setMsg("发送成功.");
        message.setCode(200);
        return message;
    }

    /**
     * @Description 给手机发送短信
     * @param phone 用户的电话号码
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> verificationCodesPhone(String phone) {
        //返回信息对象
        Message<String> message = new Message<>();

        //判断 该号码有没有被注册过
        User user = userMapper.queryUserByPhone(phone);
        if(user != null){
            message.setCode(0);
            message.setMsg("该号码以被注册.");
            message.setState(false);
            return message;
        }

        //发送邮箱
        new Thread(() -> {
            try {
                //随机数对象
                Random random = new Random();
                //拼接验证码
                StringBuilder code = new StringBuilder();
                //code.append("验证码:");
                for(int i = 0; i < 6; i++){
                    code.append(random.nextInt(9));
                }
                JuheDemo.mobileQuery(phone,code.toString());

                //存入redis存缓里面去90秒过期
                ValueOperations<String,String> valueOperations = template.opsForValue();
                String key = userPhone+phone;
                valueOperations.set(key,code.toString(),90, TimeUnit.SECONDS);

                //添加一条发送信息记录
                userMapper.addCodeRecordPhone(phone,0,code.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        message.setState(true);
        message.setMsg("发送成功.");
        message.setCode(200);
        return message;
    }

    /**
     * @Description 根据电话修改密码
     * @param password 用户保存的密码
     * @param phone 用户手机号码
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> setPasswordByUserPhone(String phone, String password) {
        //返回对象
        Message<String> message = new Message<>();

        //根据用户电话号码设置用户密码
        userMapper.updatePasswordByPhone(phone, password);

        message.setState(true);
        message.setMsg("设置成功.");
        message.setCode(200);
        return message;
    }

    /**
     * @Description 判断验证码正不正确 正确就创建用户
     * @param verificationCode 验证码
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> judgeVerificationCode(String phone,String verificationCode) {
        //返回对象
        Message<String> message = new Message<>();

        //查看 redis 存缓里有没有 验证码存缓
        boolean judeg = judgeCode(userPhone+phone,verificationCode);
        if(!judeg){
            message.setCode(0);
            message.setMsg("验证码已过期或验证码输入错误.");
            message.setState(false);
            return message;
        }

        //创建用户  用户名就是 用户电话加密
        String phoneNumber = phone.replaceAll("(\\d{3})\\d{5}(\\d{3})","$1*****$2");
        //点电话进行加密  初始化用户名
        String name = MD5Util.getMd5Code(phoneNumber);
        //开始添加用户
        userMapper.addUserByCode(name,phone);

        message.setCode(200);
        message.setMsg("验证码正确.");
        message.setState(true);
        return message;
    }

    /**
     * @Description 根据邮箱注册用户
     * @param verificationCode 验证码
     * @param email 邮箱地址
     * @return Message<String> 返回的对象提示
     * */
    public Message<String> judgeEmailVerificationCode(String userName, String password, String email, String verificationCode) {
        //返回对象
        Message<String> message = new Message<>();

        //判断邮箱有没有注册????
        User user = userMapper.queryUserByEmail(email);
        if(user != null){
            message.setState(false);
            message.setCode(0);
            message.setMsg("该邮箱已注册.");
            return message;
        }
        //查看 redis 存缓里有没有 有没有过期 验证码存缓
        boolean dome = judgeCode(userEmail+email,verificationCode);
        if(!dome){
            message.setMsg("验证码已过期或验证码输入错误.");
            message.setState(false);
            message.setCode(0);
            return message;
        }

        //对密码进行加盐加密
        String encryptionPassword = MD5Encryption.encryption(password);

        //开始添加用户
        userMapper.addUser(userName,encryptionPassword,email);

        message.setMsg("注册成功.");
        message.setCode(200);
        message.setState(true);
        return message;
    }

    /**
     * @Description 判断验证码对不对或有没有过期
     * @param verificationCode 验证码 值
     * @param key 键
     * @return Message<String> 返回的对象提示
     * */
    public boolean judgeCode(String key,String verificationCode){
        ValueOperations<String, String> valueOperations = template.opsForValue();
        //判断这个验证码有没有过期  或存不存在 存在返回true  不存在返回false
        String code = valueOperations.get(key);
        if(code == null || (!code.equals(verificationCode))){
            return false;
        }
        return true;
    }


}

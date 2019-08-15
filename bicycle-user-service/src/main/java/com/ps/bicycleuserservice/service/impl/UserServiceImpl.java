package com.ps.bicycleuserservice.service.impl;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import com.ps.allapp.service.UserService;
import com.ps.bicycleuserservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author VP
 */
@Service
public class UserServiceImpl{

    @Autowired
    private UserMapper userMapper;


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
     * 免密支付
     * @param userId,password
     * @return
     */
    public Result confidentialPayment(int userId, String password) {
        Result result = new Result();

        if(password == null || userId <= 0){
            result.setError_code(102);
            return result;
        }
        System.out.println(userId +" "+ password);

        Integer id = userMapper.userWalletDetails(userId,password);
        if (id <= 0){
            result.setError_code(100);
            return result;
        }

        Integer integer = userMapper.confidentialPayment(id,1);

        if(integer <= 0){
            result.setError_code(103);
            return result;
        }

        result.setError_code(200);
        return result;
    }
}

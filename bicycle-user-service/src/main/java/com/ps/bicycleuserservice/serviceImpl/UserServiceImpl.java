package com.ps.bicycleuserservice.serviceImpl;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.service.UserService;
import com.ps.bicycleuserservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ShareBicycle> queryRoute(int userId) {
        List<ShareBicycle> shareBicycles = userMapper.queryRoute(userId);
        return shareBicycles;
    }

    @Override
    public Integer userOrder(int id) {
        return userMapper.userOrder(id);
    }

    @Override
    public User queryPersonage(int userId) {
        return userMapper.queryPersonage(userId);
    }
}

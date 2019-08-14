package com.ps.bicycleuserservice.mapper;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}

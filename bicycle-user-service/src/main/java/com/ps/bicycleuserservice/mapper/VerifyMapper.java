package com.ps.bicycleuserservice.mapper;

import com.ps.allapp.domain.Verify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/15
 */
@Mapper
@Repository
public interface VerifyMapper {

    /**
     * 根据用户ID修改验证码信息（发送验证码）
     * @param verify
     * @return
     */
    int updateVerifyByUserId(Verify verify);

    /**
     * 创建用户关联验证码
     * @param userId
     * @return
     */
    int createVerify(int userId);

}

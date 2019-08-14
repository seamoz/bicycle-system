package com.ps.bicyclemanagebicycleservice.mapper;

import com.ps.allapp.domain.Fault;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author VP
 */
@Mapper
@Repository
public interface ManageBicycleMapper {

    void hello();
    void hello1();

    /**
     *  历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    List<Fault> historyMalfunction(int userId);

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    Fault faultDetails(int id);

}

package com.ps.bicyclebillingsevice.service;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface BillingService {

    /**
     * 免密支付
     * @param user
     * @return
     */
    Result confidentialPayment( User user);

    /**
     * 退押金、交押金
     * @param userId
     * @return
     */
    @Transactional
    Result RefundPayDeposit(int userId, String state);

    /**
     * 查询金额
     * @param userId
     * @return
     */
    Result inquiryAmount(int userId);
}

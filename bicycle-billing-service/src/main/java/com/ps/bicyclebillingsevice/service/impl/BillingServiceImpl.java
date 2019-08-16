package com.ps.bicyclebillingsevice.service.impl;

import com.ps.allapp.domain.Payrecord;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import com.ps.bicyclebillingsevice.mapper.BillingMapper;
import com.ps.bicyclebillingsevice.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author VP
 */
@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingMapper billingMapper;

    /**
     * 免密支付
     * @param userId,password
     * @return
     */
    @Override
    public Result confidentialPayment(int userId, String password) {
        Result result = new Result();

        if(password == null || userId <= 0){
            result.setError_code(102);
            result.setMeg("输入框为空或者输入有误，请正确输入...");
            return result;
        }

        Integer id = billingMapper.userWalletDetails(userId,password);

        if (id == null || id <= 0){
            result.setError_code(100);
            result.setMeg("输入有误，请重新输入...");
            return result;
        }

        Integer integer = billingMapper.confidentialPayment(id,1);

        if(integer == null || integer <= 0){
            result.setError_code(103);
            result.setMeg("网络异常，稍后重试！");
            return result;
        }

        result.setMeg("操作成功！");
        result.setError_code(200);
        return result;
    }

    /**
     * 退押金、交押金
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Result RefundPayDeposit(int userId, String state) {
        String cross = "交押金",pay="退押金";
        Result result = new Result();

        // 判断参数是否为空
        if(userId <= 0 || state == null){
            result.setError_code(102);
            result.setMeg("输入框为空或者输入有误，请正确输入...");
            return result;
        }

        //根据用户ID查询用户信息
        User user = billingMapper.selectUserById(userId);

        //根据钱包ID查询钱包信息
        Wallet wallet = billingMapper.selectWalletById(user.getWalletId());

        // 退押金
        if(pay.equals(state)){
            wallet.setRemainMoney(wallet.getRemainMoney() + 299f);

            // 交押金
        }else if(cross.equals(state)){
            // 余额不足，返回
            if(wallet.getRemainMoney() <= 0 || wallet.getRemainMoney() < 299f){
                result.setError_code(105);
                result.setMeg("余额不足，请充值");
                return result;
            }
            wallet.setRemainMoney(wallet.getRemainMoney() - 299f);
        }

        int updateCode = billingMapper.updateWalletById(wallet);
        if(updateCode == 0){
            result.setError_code(103);
            result.setMeg("网络异常，操作失败");
            return result;
        }

        //增加消费记录
        Payrecord payrecord = new Payrecord();
        payrecord.setPayMoney(299);

        int code = 0;
        String meg = "";
        // 退押金
        if(pay.equals(state)){
            payrecord.setPayType("退还押金");
            code = 1;
            meg="已退还押金，请注意查收";
            // 交押金
        }else if(cross.equals(state)){
            payrecord.setPayType("支付押金");
            meg="押金支付成功";
            code = 2;
        }

        payrecord.setUserId(userId);

        int insertCode = billingMapper.insertPayrecord(payrecord);
        // 消费记录增加成功
        if(insertCode != 0){
            result.setMeg(meg);
            result.setError_code(200);
            return result;
        }

        result.setMeg("网络异常，操作失败");
        result.setError_code(103);
        if(code == 1){
            // 退还押金失误，回退
            wallet.setRemainMoney(wallet.getRemainMoney() - 299f);

        }else if(code == 2){
            // 交押金失误，回退
            wallet.setRemainMoney(wallet.getRemainMoney() + 299f);
        }

        return result;
    }

    /**
     * 查询金额
     * @param userId
     * @return
     */
    @Override
    public Result inquiryAmount(int userId) {
        Result resul  = new Result();
        Map<String ,Float> map = new HashMap<String,Float>();
        map.put("money",229f);

        resul.setData(map);
        resul.setMeg("押金为："+229+"元");
        return resul;
    }

}

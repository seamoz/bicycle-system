package com.ps.bicyclebillingsevice.service.impl;

import com.ps.allapp.domain.Payrecord;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import com.ps.bicyclebillingsevice.mapper.BillingMapper;
import com.ps.bicyclebillingsevice.mapper.WalletMapper;
import com.ps.bicyclebillingsevice.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private WalletMapper walletMapper;

    @Value("${user.cashPledge}")
    private Float cashPledge;

    @Override
    public Integer setBB(Integer userId, Integer bb) {
        return walletMapper.setBB(userId, bb);
    }

    @Override
    public Integer updatePassword(Integer userId, String payPassword) {
        return walletMapper.updatePassword(userId,payPassword);
    }

    @Override
    public Wallet getPayPassword(Integer userId) {
        return walletMapper.getPayPassword(userId);
    }

    /**
     * 查询id有没有钱包
     * return id
     */
    @Override
    public Integer findPay(Integer userId) {
        return walletMapper.findPay(userId);
    }

    @Override
    public Integer insertWallet(Wallet wallet) {
        return walletMapper.insertWallet(wallet);
    }

    @Override
    public Integer setPayPassword(Integer userId, String payPassword) {
        return walletMapper.setPayPassword(userId,payPassword);
    }

    /**
     * 免密支付
     * @param user(userId,password)
     * @return
     */
    @Override
    public Result confidentialPayment(User user) {
        Result result = new Result();

        if(user.getPassword() == null || user.getUserId() == null || user.getUserId() <= 0){
            result.setError_code(102);
            result.setMeg("输入框为空或者输入有误，请正确输入...");
            return result;
        }

        Integer id = billingMapper.userWalletDetails(user.getUserId(),user.getPassword());

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
        String cross = "交押金",pay="退押金",handInPay = "已交",notToPay = "未交",meg = "";
        Result result = new Result();
        Payrecord payrecord = new Payrecord();
        int code = 0;

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

        // 查看押金状态
        String pledgeState= billingMapper.pledgeState(user.getWalletId());

        // 退押金
        if(pay.equals(state) && pledgeState.equals(handInPay)){

            wallet.setRemainMoney(wallet.getRemainMoney() + this.cashPledge);
            wallet.setPledgeState("0");
            payrecord.setPayType("退还押金");
            code = 1;
            meg="已退还押金，请注意查收";

            // 交押金
        }else if(cross.equals(state) && pledgeState.equals(notToPay)){
            // 余额不足，返回
            if(wallet.getRemainMoney() <= 0 || wallet.getRemainMoney() < this.cashPledge){
                result.setError_code(105);
                result.setMeg("余额不足，请充值");
                return result;
            }

            wallet.setRemainMoney(wallet.getRemainMoney() - this.cashPledge);
            wallet.setPledgeState("1");
            payrecord.setPayType("支付押金");
            meg="押金支付成功";
            code = 2;

        }else{
            result.setError_code(106);
            result.setMeg("押金"+pledgeState);
            return result;
        }

        // 更改金额与押金状态
        int updateCode = billingMapper.updateWalletById(wallet);
        if(updateCode == 0){
            result.setError_code(103);
            result.setMeg("网络异常，操作失败");
            return result;
        }

        //增加消费记录

        payrecord.setPayMoney(this.cashPledge);
        payrecord.setUserId(userId);

        // 插入消费记录
        int insertCode = billingMapper.insertPayrecord(payrecord);
        // 消费记录增加成功
        if(insertCode != 0){
            result.setMeg(meg);
            result.setError_code(200);
            return result;
        }

        //  回退押金
        result = backTheDeposit(result,wallet,code);

        return result;
    }

    /**
     * 回退押金
     * @param result
     * @param wallet
     * @param code
     * @return
     */
    public Result backTheDeposit(Result result,Wallet wallet,int code){
        result.setMeg("网络异常，操作失败");
        result.setError_code(103);
        if(code == 1){
            // 退还押金失误，回退
            wallet.setRemainMoney(wallet.getRemainMoney() - this.cashPledge);
            wallet.setPledgeState("1");
        }else if(code == 2){
            // 交押金失误，回退
            wallet.setRemainMoney(wallet.getRemainMoney() + this.cashPledge);
            wallet.setPledgeState("0");
        }

        // 更改金额与押金状态
        int updateCode1 = billingMapper.updateWalletById(wallet);
        if(updateCode1 == 0){
            result.setError_code(103);
            result.setMeg("网络异常，回退操作失败");
            return result;
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
        map.put("money",this.cashPledge);

        resul.setError_code(200);
        resul.setData(map);
        resul.setMeg("押金为："+this.cashPledge+"元");
        return resul;
    }

}

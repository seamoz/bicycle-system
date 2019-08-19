package com.ps.bicyclemanagebicycleservice.service.impl;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.*;
import com.ps.allapp.exception.BusinessException;
import com.ps.bicyclemanagebicycleservice.mapper.ManageBicycleMapper;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZZH
 */
@Service
public class ManageBicycleServiceImpl implements ManageBicycleService {

    @Autowired
    private ManageBicycleMapper manageBicycleMapper;

    @Override
    public List<ShareBicycle> changeAddress(String address){
        return manageBicycleMapper.changeAddress(address);
    }
    @Override
    public void appointmentBicycle(int userId,int bicycleNum) {

        if(manageBicycleMapper.checkUnlock(userId) != 0){
            throw new BusinessException(500, "您有一个订单正在进行！");
        }
        int bicycleNumber = checkAppointmentBicycle(userId);
        if(bicycleNumber != 0){
            throw new BusinessException(500, "您已经预约过单车了");
        }
        manageBicycleMapper.appointmentBicycle(bicycleNum,userId);
        manageBicycleMapper.changeBicycleState(1,bicycleNum);
    }

    @Override
    public User bicycleInit(int userId) {
        return manageBicycleMapper.bicycleInit(userId);
    }

    @Override
    public int checkAppointmentBicycle(int userId) {
        return manageBicycleMapper.checkAppointment(userId).getBicycleNum();
    }

    @Override
    public void unlockBicycle(int userId,int bicycleNum){

        if(manageBicycleMapper.checkUnlock(userId) != 0){
            throw new BusinessException(500, "您有一个订单正在进行！");
        }
        //是否预约了单车
        int bicycleNumber = checkAppointmentBicycle(userId);
        if(bicycleNumber!=0 && bicycleNum != bicycleNumber){
            if(bicycleNum != bicycleNumber){
                throw new BusinessException(500, "您已经预约过一辆车了！");
            }
        }
        /*#{userId},#{bicycleNum},#{initialAddress},#{startTime}*/
        //获取当前时间
        String nowTime = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date());
        //根据单车编号获取位置
        String site = manageBicycleMapper.getSiteByBicycleNum(bicycleNum);
        //添加到行车记录
        manageBicycleMapper.unlockBicycle(new ShareBicycle(userId,bicycleNum,site,nowTime));
        //修改单车状态
        manageBicycleMapper.changeBicycleState(2,bicycleNum);
        //清空预约单车项
        manageBicycleMapper.appointmentBicycle(0,userId);
    }

    /**
     * 骑行中
     * @param userId    用户ID
     * @return
     */
    @Override
    public Result cycling(int userId){
        Result result = new Result();
        //查询骑行中
        ShareBicycle shareBicycle = manageBicycleMapper.selectShareBicycleByUserId(userId);
        if(shareBicycle == null){
            throw new BusinessException(500, "没有骑行!");
        }
        if (shareBicycle.getBicycleState() != 0){
            throw new BusinessException(500, "没有骑行!");
        }
        String startTime = shareBicycle.getStartTime();
        String[] strings = startTime.split("[.]");
        startTime = strings[0];
        shareBicycle.setStartTime(startTime);
        Date st = null;
        long stl = 0L;
        long ctl = 0L;
        try {
            st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
            stl = st.getTime();
            ctl = System.currentTimeMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long xxx = ctl - stl;
        xxx = xxx/60000;
        shareBicycle.setUseTime(xxx+"");
        result.setData(shareBicycle);
        result.setError_code(0);
        return result;
    }

    /**
     * 骑行扣费页
     * @param id    骑行记录ID
     * @return
     */
    @Override
    public Result deduction(int id){
        Result result = new Result();
        //查询骑行扣费页
        result.setError_code(0);
        result.setData(manageBicycleMapper.selectShareBicycleById(id));
        return result;
    }

    /**
     * 支付
     * @param userId    用户ID
     * @param money     费用
     * @param payPassword   支付密码
     * @param payType   支付类型
     * @return
     */
    @Override
    public Result pay(int userId, float money, String payPassword, String payType){
        Result result = new Result();
        //根据用户ID查询用户信息
        User user = manageBicycleMapper.selectUserById(userId);
        //根据钱包ID查询钱包信息
        Wallet wallet = manageBicycleMapper.selectWalletById(user.getWalletId());
        //校验支付密码
        if (!wallet.getPayPassword().equals(payPassword)){
            throw new BusinessException(500, "支付密码错误!");
        }
        //支付
        wallet.setRemainMoney(wallet.getRemainMoney() - money);
        int updateCode = manageBicycleMapper.updateWalletById(wallet);
        //增加消费记录
        Payrecord payrecord = new Payrecord();
        payrecord.setPayMoney(money);
        payrecord.setPayTime(new Date());
        payrecord.setPayType(payType);
        payrecord.setUserId(userId);
        int insertCode = manageBicycleMapper.insertPayrecord(payrecord);
        result.setMeg("支付成功!");
        return result;
    }

    /**
     * 历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    @Override
    public Result historyMalfunction(int userId) {
        Result result = new Result();

        List<Fault> list = manageBicycleMapper.historyMalfunction(userId);

        result.setError_code(200);

        if (list == null || list.size() <= 0){
            result.setMeg("该用户没有提交单车故障");
            return result;
        }

        result.setData(list);
        result.setMeg("详情资料如下：");
        return result;
    }

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    @Override
    public Result faultDetails(int id) {
        Result result = new Result();

        Fault fault = manageBicycleMapper.faultDetails(id);
        result.setError_code(200);

        if (fault == null){
            result.setMeg("该单车没有故障资料...");
            return result;
        }

        result.setData(fault);
        result.setMeg("详情资料如下:");
        return result;
    }

    /**
     * 上报故障
     * @param fault
     * @return
     */
    @Override
    public Result sbikeFault(Fault fault){
        Result result = new Result();

        /* 确认传入的参数是否为空，或者值有误 */
        if(fault.getFaultType() == null ||
                fault.getBicycleNum() == null ||
                fault.getUserId() <= 0){
            result.setError_code(102);
            result.setMeg("故障类型、单车编号不能为空，请重新输入...");
            return result;
        }
        int bicycleNum = 0;
        try{
            bicycleNum = Integer.valueOf(fault.getBicycleNum());
        }catch(Exception e){
            result.setError_code(105);
            result.setMeg("输入有误...");
            return result;
        }

        String count = manageBicycleMapper.getSiteByBicycleNum(bicycleNum);
        if(count == null || count.equals("")){
            result.setError_code(104);
            result.setMeg("该单车不存在...");
            return result;
        }

        Integer integer = manageBicycleMapper.sbikeFault(fault);

        if(integer == null || fault.getId() <= 0 || integer == 0){
            result.setError_code(103);
            result.setMeg("网络异常，请销后再试...");
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("id",fault.getId());

        result.setData(map);
        result.setMeg("故障发送成功！");
        result.setError_code(200);
        return result;

    }

    /**
     * 关锁
     * @param id      骑行记录ID
     * @param useTimes    骑行时间
     * @return
     */
    @Override
    public Result shut(int id, String useTimes) {
        ShareBicycle shareBicycle = new ShareBicycle();
        shareBicycle.setId(id);
        shareBicycle.setUseTime(useTimes);
        Result result = new Result();
        shareBicycle.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        long useTime = Long.valueOf(shareBicycle.getUseTime());
        float count = 0;
        useTime = useTime - 60;
        if(useTime < 0){
            count++;
        } else {
            useTime = useTime/30;
            count = useTime*0.5f;
        }
        shareBicycle.setMoney(count);
        int updateCode = manageBicycleMapper.shutUpdateShareBicycle(shareBicycle);
        if(updateCode == 1){
            throw new BusinessException(500, "关锁失败!");
        }
        ShareBicycle shareBicycle1 = manageBicycleMapper.selectShareBicycleById(id);
        manageBicycleMapper.changeBicycleState(0,shareBicycle1.getBicycleNum());
        result.setError_code(updateCode);
        result.setMeg("关锁成功!");
        return result;
    }

    /**
     * 上报计费异常
     * @param id    骑行记录ID
     * @param abnormal  异常
     * @return
     */
    @Override
    public Result abnormal(int id, String abnormal) {
        Result result = new Result();
        ShareBicycle shareBicycle = manageBicycleMapper.selectShareBicycleById(id);
        Abnormal abnormals = new Abnormal();
        abnormals.setShareId(id);
        abnormals.setUserId(shareBicycle.getUserId());
        abnormals.setReportTime(new Date());
        abnormals.setAbnormal(abnormal);
        int insertCode = manageBicycleMapper.reportAbnormalBilling(abnormals);
        if(insertCode != 1){
            throw new BusinessException(500, "上报失败!");
        }
        int updateCode = manageBicycleMapper.abnormalUpdateShareBicycle(id);
        result.setMeg("上报成功");
        result.setError_code(insertCode);
        return null;
    }

}

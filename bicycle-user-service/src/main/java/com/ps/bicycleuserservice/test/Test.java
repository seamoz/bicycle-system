package com.ps.bicycleuserservice.test;

import com.ps.allapp.domain.UserAndVerify;
import com.ps.bicycleuserservice.BicycleUserServiceApplication;
import com.ps.bicycleuserservice.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BicycleUserServiceApplication.class)
public class Test {

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void test() throws ParseException {
        UserAndVerify u = userMapper.logInByPhoneAndVerify("15536541254", "4321");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(String.valueOf(u.getVerify().getVerifyTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        System.out.println(sdf.format(date));
    }
}

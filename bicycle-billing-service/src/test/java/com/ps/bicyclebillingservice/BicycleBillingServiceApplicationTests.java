package com.ps.bicyclebillingservice;

import com.ps.bicyclebillingsevice.mapper.WalletMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BicycleBillingServiceApplicationTests {

    @Autowired
    private WalletMapper walletMapper;

    @Test
    public void contextLoads() {

    }

    @Test
    public void keyTest() {
        /*Integer s = null;
        Integer integer = walletMapper.insertWallet(s);*/
    }

}

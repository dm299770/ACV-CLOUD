package com.acv.cloud.controller.account;

import com.acv.cloud.dto.sys.UserInfo;
import com.acv.cloud.mapper.account.AccountMapper;
import com.acv.cloud.mapper.user.TsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class TransactionalTest {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TsUserMapper tsUserMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping(value = "/de", method = RequestMethod.POST)
    public void deduct() {
        Date date = new Date();    //下单时间及修改时间
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = ft.format(date);
        String user_id = "e5792c05-fa1c-4ffa-9adb-aa66f2cd8863";
        String balance = "5000";
        String id = UUID.randomUUID().toString();
        String moneyD = "100";
        Integer direction = -1;
        UserInfo userInfo = tsUserMapper.findUserPhoneNum(user_id);
        String comment = "662101959121503129";

        try {
            accountMapper.upadteBalance(user_id, balance, updateTime);
            throw new Exception("异常了");
        } catch (Exception e) {
            e.printStackTrace();
        }
        accountMapper.saveChargeFlow(id, user_id, Double.valueOf(moneyD), direction, updateTime, userInfo.getPhoneNum(), "PowreSare", comment);
    }
}

package com.acv.cloud.services.account.impl;

import com.acv.cloud.models.jsonBean.account.TtChargeFlow;
import com.alibaba.fastjson.JSONObject;
import com.acv.cloud.dto.sys.UserInfo;
import com.acv.cloud.frame.constants.AppResultConstants;
import com.acv.cloud.mapper.account.AccountMapper;
import com.acv.cloud.mapper.user.TsUserMapper;
import com.acv.cloud.models.jsonBean.account.UserAccount;
import com.acv.cloud.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * PowerShare 接口
 * Created by liyang
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final String BALANCE = "balance";
    private static final String DEDUCT_SUCCESS = "已扣款成功";
    private static final String DEDUCT_FATL = "余额不足,扣款失败";
    private static final String CHARGETO = "PowerShare";//到达方
    private static final String DEDUCT_ERROR = "该订单已扣款,请勿重复扣款";
    private static final String ORDER_BALANCE = "账户余额";
    private static final String ORDER_SUCCESS = "账单查询成功";
    private static final String DEDUCT_CODE = "401";
    private static final String SERIAL_NUM = "id";


    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TsUserMapper tsUserMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public JSONObject deduct(String user_id, String money, String comment) throws Exception {
        JSONObject json = new JSONObject();
        UserAccount userAccount = null;
        UserInfo userInfo = null;
        if (money == null || "".equals(money)) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
        } else if (user_id == null || "".equals(user_id)) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
        } else if (comment == null || "".equals(comment)) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
        } else {
            TtChargeFlow ttChargeFlow = accountMapper.selOrderNum(comment);
            //判断该订单号是否存在订单，如果有，则勿重复扣款，
            if (ttChargeFlow != null) {
                json.put(AppResultConstants.STATUS, DEDUCT_CODE);
                json.put(AppResultConstants.MSG, DEDUCT_ERROR);
            } else {
                Integer direction = -1;//增减标识（扣款为减少，-1）
                String id = UUID.randomUUID().toString();//流水单号
                userAccount = accountMapper.selectBalance(user_id);
                BigDecimal amount = new BigDecimal(userAccount.getBalance());//当前账户余额
                BigDecimal moneyD = new BigDecimal(money);//扣款金额
                BigDecimal balanceD = amount.subtract(moneyD);//消费后的余额
                String balance = String.valueOf(balanceD);
                //比较标识符
                int comTo = moneyD.compareTo(amount);
                Date date = new Date();    //下单时间及修改时间
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String updateTime = ft.format(date);
                //扣费操作
                if (comTo == -1) {
                    accountMapper.upadteBalance(user_id, balance, updateTime);
                    //发起方查询
                    userInfo = tsUserMapper.findUserPhoneNum(user_id);
                    accountMapper.saveChargeFlow(id, user_id, Double.valueOf(String.valueOf(moneyD)), direction, updateTime, userInfo.getPhoneNum(), CHARGETO, comment);
                    json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                    json.put(AppResultConstants.MSG, DEDUCT_SUCCESS);
                    json.put(BALANCE, balance);
                    json.put(SERIAL_NUM, id);

                } else if (comTo == 0) { //如果余额和消费金额相等，则修改余额为零
                    accountMapper.upadteBalance(user_id, "0", updateTime);
                    //发起方查询
                    userInfo = tsUserMapper.findUserPhoneNum(user_id);
                    accountMapper.saveChargeFlow(id, user_id, Double.valueOf(String.valueOf(moneyD)), direction, updateTime, userInfo.getPhoneNum(), CHARGETO, comment);
                    json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                    json.put(AppResultConstants.MSG, DEDUCT_SUCCESS);
                    json.put(BALANCE, balance);
                    json.put(SERIAL_NUM, id);
                } else {
                    //余额不足
                    json.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                    json.put(AppResultConstants.MSG, DEDUCT_FATL);
                    json.put(BALANCE, balance);
                }
            }
        }
        return json;
    }


    @Override
    public JSONObject selAll(String user_id) {
        JSONObject json = new JSONObject();
        try {
            if (user_id.isEmpty() || "".equals(user_id)) {
                json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
                json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
            } else {
                List<TtChargeFlow> ttChargeFlow = accountMapper.selectAll(user_id);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, ORDER_SUCCESS);
                json.put("data", ttChargeFlow);
            }
        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public JSONObject selBalance(String user_id) {
        JSONObject json = new JSONObject();
        try {
            if (user_id.isEmpty() || "".equals(user_id)) {
                json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
                json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
            } else {
                UserAccount userAccount = accountMapper.selBalance(user_id);
                String balance = userAccount.getBalance();
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, ORDER_BALANCE);
                json.put(BALANCE, balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
        }
        return json;
    }
}

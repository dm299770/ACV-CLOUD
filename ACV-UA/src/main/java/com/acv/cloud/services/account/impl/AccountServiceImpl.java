package com.acv.cloud.services.account.impl;

import com.alibaba.fastjson.JSONObject;
import com.acv.cloud.dto.sys.UserInfo;
import com.acv.cloud.frame.constants.AppResultConstants;
import com.acv.cloud.mapper.account.AccountMapper;
import com.acv.cloud.mapper.user.TsUserMapper;
import com.acv.cloud.models.jsonBean.account.UserAccount;
import com.acv.cloud.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String BALANCE = "balance";
    private static final String DEDUCT_SUCCESS = "已缴费成功";
    private static final String DEDUCT_FATL = "余额不足,缴费失败";
    private static final String DEDUCT_ERROR = "服务异常,请稍后重试或联系相关app";
    private static final String DEDUCT_FORMATERROR = "扣款金额格式不正确,请检查";
    private static final String CHARGETO = "PowerShare";//到达方

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TsUserMapper tsUserMapper;

    /**
     * 扣款计费
     */
    @Override
    //@Transactional
    public JSONObject deduct(String user_id, String money) {
        JSONObject json = new JSONObject();
        UserAccount userAccount = null;
        UserInfo userInfo = null;
        if (money == null && money.isEmpty()) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, DEDUCT_ERROR);
        }
        try {
            userAccount = accountMapper.selectBalance(user_id);
            BigDecimal amount = new BigDecimal(userAccount.getBalance());
            BigDecimal moneyD = new BigDecimal(money);
            BigDecimal balance = amount.subtract(moneyD);//消费后的余额
            //比较标识符
            int comTo = moneyD.compareTo(amount);

            Date updateTime = new Date();    //下单时间及修改时间

            //扣费操作
            if (comTo == -1) {
                accountMapper.upadteBalance(user_id, String.valueOf(balance), updateTime);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, DEDUCT_SUCCESS);
                json.put(BALANCE, balance);
            } else if (comTo == 0) { //如果余额和消费金额相等，则修改余额为零
                accountMapper.upadteBalance(user_id, "0", updateTime);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, DEDUCT_SUCCESS);
                json.put(BALANCE, balance);
            } else {
                //余额不足
                json.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                json.put(AppResultConstants.MSG, DEDUCT_FATL);
                json.put(BALANCE, balance);
            }
            //扣费流水记录
            if (comTo != 1) {
                String id = UUID.randomUUID().toString();//流水单号
                Integer direction = -1; //增减标识（扣款为减少，-1）
                //发起方查询
                userInfo = tsUserMapper.findUserPhoneNum(user_id);
                accountMapper.saveChargeFlow(id, user_id, Double.valueOf(String.valueOf(moneyD)), direction, updateTime, userInfo.getPhoneNum(), CHARGETO);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, DEDUCT_SUCCESS);
            }
        } catch (NumberFormatException e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, DEDUCT_FORMATERROR);
            e.printStackTrace();
        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, DEDUCT_ERROR);
            e.printStackTrace();
        }
        return json;
    }
}

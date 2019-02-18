package com.acv.cloud.models.jsonBean.account;

import com.acv.cloud.dto.sys.UserInfo;

public class AccountBody {
    UserInfo CurrentUser;
    String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public UserInfo getUserInfo() {
        return CurrentUser;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.CurrentUser = userInfo;
    }
}

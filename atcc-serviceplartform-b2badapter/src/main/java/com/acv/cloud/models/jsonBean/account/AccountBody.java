package com.acv.cloud.models.jsonBean.account;

import com.acv.cloud.dto.sys.UserInfo;

public class AccountBody {
    String comment;//订单号
    String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

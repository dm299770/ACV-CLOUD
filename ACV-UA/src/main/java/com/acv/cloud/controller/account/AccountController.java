package com.acv.cloud.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.acv.cloud.dto.sys.UserInfo;
import com.acv.cloud.frame.annotation.CurrentUser;
import com.acv.cloud.frame.annotation.LoginRequired;
import com.acv.cloud.models.jsonBean.account.AccountBody;
import com.acv.cloud.services.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:账户
 * @author:@leo.
 */
@Controller
//@RequestMapping("/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    /**
     * 用户充电扣款
     */

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    public Object deduct(@RequestBody AccountBody money) {
        UserInfo user = money.getUserInfo();
        String user_id = user.getUserId();
        JSONObject jsonObject = accountService.deduct(user_id, money.getMoney());
        return jsonObject;
    }

}
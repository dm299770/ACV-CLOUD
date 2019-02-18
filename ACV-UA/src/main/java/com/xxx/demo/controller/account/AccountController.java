package com.xxx.demo.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.dto.sys.UserInfo;
import com.xxx.demo.frame.annotation.CurrentUser;
import com.xxx.demo.frame.annotation.LoginRequired;
import com.xxx.demo.models.jsonBean.account.AccountBody;
import com.xxx.demo.services.account.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PowerShare 接口
 * Created by liyang
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    /**
     * 用户充电扣款
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    public Object deduct(@CurrentUser UserInfo user, @RequestBody AccountBody money) {
        logger.info(user.toString());
        JSONObject jsonObject = accountService.deduct(user.getUserId(), money.getMoney(), money.getComment());
        return jsonObject;
    }

    /**
     * 账单查询
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/selAll", method = RequestMethod.POST)
    public Object selAll(@CurrentUser UserInfo user) {
        logger.info(user.toString());
        JSONObject jsonObject = accountService.selAll(user.getUserId());
        return jsonObject;
    }

    /**
     * 账户余额
     *
     * @param user
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/selBalance", method = RequestMethod.POST)
    public Object selBalance(@CurrentUser UserInfo user) {
        logger.info(user.toString());
        JSONObject jsonObject = accountService.selBalance(user.getUserId());
        return jsonObject;
    }
}
package com.xxx.demo.services.account;

import com.alibaba.fastjson.JSONObject;

/**
 * @description:账户相关
 * @author:@leo.
 */
public interface AccountService {
    /**
     * 用户充电扣款
     *
     * @param user_id 用户主键
     * @param money   充电扣费
     * @return
     */
    JSONObject deduct(String user_id, String money, String comment);

    /**
     * @param user_id 用户主键
     * @return
     */
    JSONObject selAll(String user_id);

    /**
     * @param user_id 用户主键
     * @return
     */
    JSONObject selBalance(String user_id);

}

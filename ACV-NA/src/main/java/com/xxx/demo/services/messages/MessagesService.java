package com.xxx.demo.services.messages;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liyang on 2018/12/24.
 */
public interface MessagesService {

    /**
     * 发送短信接口
     *
     * @param phoneNum 手机号
     * @param content 短信内容
     * @return
     */
    JSONObject sendSms(String phoneNum, String content);
}

package com.xxx.demo.services.sms;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.sms.SMS;

/**
 * Created by liyang on 2018/12/24.
 */
public interface SMSService {

    /**
     * 发送短信接口
     *
     * @return
     */
    JSONObject sendSms(SMS sms);
}

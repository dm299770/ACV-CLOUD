package com.xxx.demo.controller.sms;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.sms.SMS;
import com.xxx.demo.services.sms.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公用发送短信接口
 *
 * Created by liyang on 2018/12/24.
 */
@Controller
@RequestMapping({"/sms"})
public class SMSController {

    @Autowired
    private SMSService smsService;

    /**
     *根据手机号发送短信
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send")
    public Object sendSmsToPhone(@RequestBody SMS sms) {
        JSONObject jsonObject = smsService.sendSms(sms);
        return jsonObject;
    }

}

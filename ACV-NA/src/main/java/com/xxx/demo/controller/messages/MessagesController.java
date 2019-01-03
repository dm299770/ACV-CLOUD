package com.xxx.demo.controller.messages;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.messages.Messages;
import com.xxx.demo.services.messages.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping({"/messages"})
public class MessagesController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessagesService messagesService;

    /**
     *根据手机号发送短信
     *
     * @param phoneNum 手机号
     * @param content  短信内容
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send")
    public Object sendSmsToPhone(@RequestBody Messages me) {
        String phoneNum = me.getPhoneNum();
        String content = me.getContent();
        JSONObject jsonObject = messagesService.sendSms(phoneNum, content);
        logger.info(jsonObject.toString());
        return jsonObject;
    }

}

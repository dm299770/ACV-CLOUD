package com.acv.cloud.controller.message;

import com.acv.cloud.models.jsonBean.message.request.MessageRequest;
import com.acv.cloud.services.message.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公用通知查询接口
 * Created by liyang on 2019/01/10.
 */
@RestController
@RequestMapping({"/message"})
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ResponseBody
    @RequestMapping(value = "select")
    public Object selectMessage(@RequestBody MessageRequest message) {
        JSONObject result = messageService.selectMessage(message);
        return result;
    }
}

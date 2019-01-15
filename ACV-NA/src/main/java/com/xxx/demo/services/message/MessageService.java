package com.xxx.demo.services.message;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.jsonBean.message.request.MessageRequest;

/**
 * Created by liyang on 2018/01/10.
 */
public interface MessageService {

    JSONObject selectMessage(MessageRequest message);
}

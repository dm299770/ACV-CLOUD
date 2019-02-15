package com.xxx.demo.services.smartSpeaker;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.jsonBean.smartSpeaker.TaskRequest;

public interface AliSmartEvHandle {
    JSONObject execute(TaskRequest taskRequest);
}

package com.xxx.demo.services.smartSpeaker;


import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.jsonBean.smartSpeaker.TaskRequest;


public interface SmallEvHande {

    //public TaskResult execute(TaskQuery taskQuery);

    /**运行处理智能音箱意图请求*/
     JSONObject execute(TaskRequest taskRequest);

}


package com.acv.cloud.controller.unread;

import com.acv.cloud.models.mongdb.notification.Notification;
import com.acv.cloud.services.unread.UnReadService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知已读未读接口
 * Created by liyang on 2019/01/10.
 */
@RestController
@RequestMapping({"/readStatus"})
public class UnReadController {

    @Autowired
    private UnReadService unReadService;

    @ResponseBody
    @RequestMapping(value = "update")
    public Object readSubmit(@RequestBody Notification no) {
        JSONObject json =unReadService.updateUnRead(no);
        return json;
    }

}

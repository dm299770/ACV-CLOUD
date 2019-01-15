package com.xxx.demo.services.unread;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.notification.Notification;

/**
 * Created by liyang on 2018/01/10.
 */
public interface UnReadService {

    JSONObject updateUnRead(Notification no);
}

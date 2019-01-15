package com.xxx.demo.services.notification;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.notification.Notification;

/**
 * Created by liyang on 2018/12/18.
 */
public interface NotificationService {

    /**
     * 推送接口
     *
     * @return
     */
    JSONObject pushMsgDevice(Notification no);

    /**
     * 推送消息给单个app的所有IOS设备
     *
     * @return
     */
    JSONObject pushMsgDeviceAll(Notification no);

    /**
     * @return
     */
    JSONObject pushMsgDeviceList(Notification no);
}

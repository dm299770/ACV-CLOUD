package com.xxx.demo.services.notification;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liyang on 2018/12/18.
 */
public interface NotificationService {

    /**
     * 推送接口
     *
     * @param token
     * @param title
     * @param subtitle
     * @param body
     * @return
     */
    JSONObject pushMsgDevice(String phoneNum, String title, String subtitle, String body);

    /**
     * 推送消息给单个app的所有IOS设备
     *
     * @param title
     * @param subtitle
     * @param body
     * @return
     */
    JSONObject pushMsgDeviceAll(String title, String subtitle, String body);

    /**
     *
     * @param phoneNum
     * @param title
     * @param subtitle
     * @param body
     * @return
     */
    JSONObject pushMsgDeviceList(String phoneNum, String title, String subtitle, String body);
}

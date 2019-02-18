package com.acv.cloud.controller.notification;

import com.acv.cloud.models.mongdb.notification.Notification;
import com.acv.cloud.services.notification.NotificationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公用推送通知接口
 * Created by liyang on 2018/12/18.
 */
@Controller
@RequestMapping({"/notification"})
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 用账号推送通知给单个IOS设备
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/push")
    public Object pushMsg(@RequestBody Notification no) {
        JSONObject jsonObject = notificationService.pushMsgDevice(no);
        return jsonObject;
    }

    /**
     * 推送给单个app的所有IOS设备
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushAll")
    public Object pushMsgAll(@RequestBody Notification no) {
        JSONObject jsonObject = notificationService.pushMsgDeviceAll(no);
        return jsonObject;
    }

    /**
     * 用账号推送通知给多个IOS设备
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushList")
    public JSONObject pushMsgList(@RequestBody Notification no) {
        JSONObject jsonObject = notificationService.pushMsgDeviceList(no);
        return jsonObject;
    }
}

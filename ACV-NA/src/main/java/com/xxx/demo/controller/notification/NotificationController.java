package com.xxx.demo.controller.notification;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.models.mongdb.notification.Notification;
import com.xxx.demo.services.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NotificationService notificationService;

    /**
     * 用账号推送通知给单个IOS设备
     *
     * @param token    设备绑定token
     * @param title    推送标题
     * @param subtitle 推送子标题
     * @param body     推送内容
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/push")
    public Object pushMsg(@RequestBody Notification no) {
        logger.info(no.toString());
        String title = no.getTitle();
        String subtitle = no.getSubtitle();
        String body = no.getBody();
        String phoneNum = no.getPhoneNum();
        JSONObject jsonObject = notificationService.pushMsgDevice(phoneNum, title, subtitle, body);
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
        logger.info(no.toString());
        String title = no.getTitle();
        String subtitle = no.getSubtitle();
        String body = no.getBody();
        JSONObject jsonObject = notificationService.pushMsgDeviceAll(title, subtitle, body);
        return jsonObject;
    }

    /**
     * 用账号推送通知给多个IOS设备
     *
     * @param phoneNum 用户手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushList")
    public JSONObject pushMsgList(@RequestBody Notification no) {
        logger.info(no.toString());
        String phoneNum = no.getPhoneNum();
        String title = no.getTitle();
        String subtitle = no.getSubtitle();
        String body = no.getBody();
        JSONObject jsonObject = notificationService.pushMsgDeviceList(phoneNum, title, subtitle, body);
        return jsonObject;
    }
}

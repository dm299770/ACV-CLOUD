package com.xxx.demo.services.notification.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
import com.xxx.demo.frame.constants.AppResultConstants;
import com.xxx.demo.mapper.user.TsUserMapper;
import com.xxx.demo.models.sys.TsUser;
import com.xxx.demo.repository.mongotemplate.INotificationMongoDBDao;
import com.xxx.demo.repository.redistemplate.INotificationDao;
import com.xxx.demo.services.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liyang on 2018/12/18.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TITLE_ERROR = "推送标题异常,请检查后重试";
    private static final String PHONEM_ERROR = "手机号异常,请检查后重试";
    private static final String BOBY_ERROR = "推送内容异常,请检查后重试";

    @Value("${secretKey}")
    private String secretKey;

    @Value("${accessId}")
    private Long accessId;

    @Autowired
    private INotificationDao notificationDao;

    @Autowired
    private INotificationMongoDBDao notificationMongoDBDao;

    @Autowired
    private TsUserMapper tsUserMapper;

    @Override
    public JSONObject pushMsgDevice(String phoneNum, String title, String subtitle, String body) {
        if (phoneNum.isEmpty() && "".equals(phoneNum)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, PHONEM_ERROR);
        }
        if (title.isEmpty() && "".equals(title)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, TITLE_ERROR);
        }
        if (body.isEmpty() && "".equals(body)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, BOBY_ERROR);
        }
        try {
            XingeApp xinge = new XingeApp(accessId, secretKey);
            MessageIOS mess = new MessageIOS();
            mess.setExpireTime(86400);
            mess.setAlert("ios test");
            mess.setBadge(1);
            mess.setSound("beep.wav");
            Map<String, Object> custom = new HashMap<String, Object>();
            custom.put("key", "value");
            mess.setCustom(custom);
            TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
            mess.addAcceptTime(acceptTime);
            JSONObject obj = new JSONObject();
            JSONObject aps = new JSONObject();
            JSONObject alert = new JSONObject();
            alert.put("title", title);
            alert.put("subtitle", subtitle);
            alert.put("body", body);
            aps.put("sound", "beep.wav");
            aps.put("alert", alert);
            aps.put("badge", 1);
            aps.put("content-available", 1);
            obj.put("aps", aps);
            mess.setRaw(obj.toString());
            //用手机号查token
            String token = notificationDao.getDeviceToken(phoneNum);
            logger.info(token);
            //推送
            logger.info(xinge.pushSingleAccount(0, phoneNum, mess, XingeApp.IOSENV_DEV).toString());
            //去数据库查userId
            TsUser tsUser = tsUserMapper.findUserId(phoneNum);
            //把推送消息插入mongodb
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = df.format(new Date());
            notificationMongoDBDao.insertList(phoneNum, token, title, subtitle, body, createDate, tsUser);
            logger.info(obj.toString());
            return obj;
        } catch (Exception e) {
            JSONObject jsonExcptionObject = new JSONObject();
            jsonExcptionObject.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            jsonExcptionObject.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
            return jsonExcptionObject;
        }
    }

    @Override
    public JSONObject pushMsgDeviceAll(String title, String subtitle, String body) {
        //对参数进行非空判断
        if (title.isEmpty() && "".equals(title)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, TITLE_ERROR);
        }
        if (body.isEmpty() && "".equals(body)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, BOBY_ERROR);
        }
        try {
            XingeApp xinge = new XingeApp(accessId, secretKey);
            MessageIOS mess = new MessageIOS();
            mess.setExpireTime(86400);
            mess.setAlert("ios test");
            mess.setBadge(1);
            mess.setSound("beep.wav");
            Map<String, Object> custom = new HashMap<String, Object>();
            custom.put("key", "value");
            mess.setCustom(custom);
            TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
            mess.addAcceptTime(acceptTime);
            JSONObject obj = new JSONObject();
            JSONObject aps = new JSONObject();
            JSONObject alert = new JSONObject();
            alert.put("title", title);
            alert.put("subtitle", subtitle);
            alert.put("body", body);
            aps.put("sound", "beep.wav");
            aps.put("alert", alert);
            aps.put("badge", 1);
            aps.put("content-available", 1);
            obj.put("aps", aps);
            mess.setRaw(obj.toString());
            logger.info(xinge.pushAllDevice(0, mess, XingeApp.IOSENV_DEV).toString());
            //把推送消息插入mongodb
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = df.format(new Date());
            notificationMongoDBDao.insertAll(title, subtitle, body, createDate,
                    "99999999999","99999999999","99999999999");
            logger.info(obj.toString());
            return obj;
        } catch (Exception e) {
            JSONObject jsonExcptionObject = new JSONObject();
            jsonExcptionObject.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            jsonExcptionObject.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
            return jsonExcptionObject;
        }
    }

    @Override
    public JSONObject pushMsgDeviceList(String phoneNum, String title, String subtitle, String body) {
        if (phoneNum.isEmpty() && "".equals(phoneNum)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, PHONEM_ERROR);
        }
        if (title.isEmpty() && "".equals(title)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, TITLE_ERROR);
        }
        if (body.isEmpty() && "".equals(body)) {
            JSONObject json = new JSONObject();
            json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
            json.put(AppResultConstants.STATUS, BOBY_ERROR);
        }

        try {
            XingeApp xinge = new XingeApp(accessId, secretKey);
            MessageIOS mess = new MessageIOS();
            mess.setExpireTime(86400);
            mess.setAlert("ios test");
            mess.setBadge(1);
            mess.setSound("beep.wav");
            Map<String, Object> custom = new HashMap<String, Object>();
            custom.put("key", "value");
            mess.setCustom(custom);
            TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
            mess.addAcceptTime(acceptTime);
            JSONObject obj = new JSONObject();
            JSONObject aps = new JSONObject();
            JSONObject alert = new JSONObject();
            alert.put("title", title);
            alert.put("subtitle", subtitle);
            alert.put("body", body);
            aps.put("sound", "beep.wav");
            aps.put("alert", alert);
            aps.put("badge", 1);
            aps.put("content-available", 1);
            obj.put("aps", aps);
            mess.setRaw(obj.toString());
            //通过redis查询token
            JSONArray jsonArray = JSONArray.parseArray(phoneNum);
            List<String> phoneNums = jsonArray.toJavaList(String.class);
            logger.info(String.valueOf(phoneNums));
            for (String tokenList : phoneNums) {
                String token = notificationDao.getDeviceToken(tokenList);
                logger.info(tokenList);
                //推送
                logger.info(xinge.pushSingleAccount(0, phoneNum, mess, XingeApp.IOSENV_DEV).toString());
                //去数据库查userId
                TsUser tsUser = tsUserMapper.findUserId(tokenList);
                //把推送消息插入mongodb
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createDate = df.format(new Date());
                notificationMongoDBDao.insertList(tokenList, token, title, subtitle, body, createDate, tsUser);
            }
            logger.info(obj.toString());
            return obj;
        } catch (Exception e) {
            JSONObject jsonExcptionObject = new JSONObject();
            jsonExcptionObject.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            jsonExcptionObject.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
            return jsonExcptionObject;
        }
    }
}

package com.acv.cloud.services.notification.impl;

import com.acv.cloud.frame.constants.AppResultConstants;
import com.acv.cloud.mapper.TsUserMapper;
import com.acv.cloud.models.mongdb.notification.Notification;
import com.acv.cloud.frame.util.DateUtil;
import com.acv.cloud.models.sys.TsUser;
import com.acv.cloud.repository.mongotemplate.INotificationMongoDBDao;
import com.acv.cloud.repository.redistemplate.INotificationDao;
import com.acv.cloud.services.notification.NotificationService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liyang on 2018/12/18.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TITLE_ERROR = "推送标题异常,请检查后重试";
    private static final String PHONE_ERROR = "手机号异常,请检查后重试";
    private static final String CONTEXT_ERROR = "推送内容异常,请检查后重试";
    private static final String TYPE_ERROR = "推送类型异常,请检查后重试";
    private static final String SUCCESS_EX = "推送成功";
    private final static String RETURN_EX = "推送接口返回异常";
    private final static String FAIL_EX = "推送失败";

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
    public JSONObject pushMsgDevice(Notification no) {
        JSONObject obj = new JSONObject();
        try {
            logger.info("请求体:" + no);
            String title = no.getTitle();
            String context = no.getContext();
            String phoneNum = no.getPhoneNum();
            String vin = no.getVin();
            String type = no.getType();
            String ids = UUID.randomUUID().toString().replaceAll("-", "");
            String imageURL = no.getImageURL();

            if (phoneNum == null || "".equals(phoneNum)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, PHONE_ERROR);
            } else if (title == null || "".equals(title)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TITLE_ERROR);
            } else if (context == null || "".equals(context)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, CONTEXT_ERROR);
            } else if (type == null || "".equals(type)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TYPE_ERROR);
            } else {
                XingeApp xinge = new XingeApp(accessId, secretKey);
                MessageIOS mess = pushClient(title, context, type, imageURL);

                //设置时间格式
                String createDate = DateUtil.getDate("yyyy/MM/dd HH:mm:ss");
                //未读标识符,未读
                Integer readflag = 0;
                //用手机号查token
                String token = notificationDao.getDeviceToken(phoneNum);
                logger.info("用户token信息:" + token);
                //去数据库查userId
                TsUser userId = tsUserMapper.findUserId(phoneNum);
                //推送
                String returnCode = xinge.pushSingleDevice(token, mess, XingeApp.IOSENV_DEV).toString();
                //判断返回状态码是否推送成功
                if (returnCode.contains("ret_code")) {
                    //{"err_msg":"无效帐号，请检查后重试","ret_code":48}
                    JSONObject returnCodeJson = JSONObject.parseObject(returnCode);
                    String returnCodeString = String.valueOf(returnCodeJson.get("ret_code"));
                    if ("0".equals(returnCodeString)) {
                        obj.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                        obj.put(AppResultConstants.MSG, SUCCESS_EX);
                        //把推送消息插入mongodb
                        notificationMongoDBDao.insertList(ids, phoneNum, token, title, vin, context, createDate, userId, type, readflag, imageURL);
                    } else {
                        obj.put(AppResultConstants.MSG, RETURN_EX);
                        obj.put(AppResultConstants.STATUS, "返回状态码:" + returnCodeString);
                    }
                } else {
                    obj.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                    obj.put(AppResultConstants.MSG, FAIL_EX);
                    logger.info("返回状态码:" + returnCode);
                }
                logger.info(obj.toString());
                logger.info("返回状态码:" + returnCode);
            }
        } catch (Exception e) {
            obj.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            obj.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return obj;
    }


    @Override
    public JSONObject pushMsgDeviceList(Notification no) {
        JSONObject obj = new JSONObject();
        try {
            logger.info("请求体:" + no);
            String phoneNum = no.getPhoneNum();
            String title = no.getTitle();
            String context = no.getContext();
            String vin = no.getVin();
            String type = no.getType();
            String ids = UUID.randomUUID().toString().replaceAll("-", "");
            String imageURL = no.getImageURL();

            if (phoneNum == null || "".equals(phoneNum)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, PHONE_ERROR);
            } else if (title == null || "".equals(title)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TITLE_ERROR);
            } else if (context == null || "".equals(context)) {
                JSONObject json = new JSONObject();
                json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                json.put(AppResultConstants.MSG, CONTEXT_ERROR);
            } else if (type == null || "".equals(type)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TYPE_ERROR);
            } else {
                XingeApp xinge = new XingeApp(accessId, secretKey);
                MessageIOS mess = pushClient(title, context, type, imageURL);

                //未读标识符，未读
                Integer readflag = 0;
                //设置时间格式
                String createDate = DateUtil.getDate("yyyy/MM/dd HH:mm:ss");
                //json格式转换
                JSONArray jsonArray = JSONArray.parseArray(phoneNum);
                List<String> phoneNumList = jsonArray.toJavaList(String.class);
                logger.info("用户账号信息列表:" + phoneNumList);
                for (String phoneNums : phoneNumList) {
                    //通过redis查询token
                    String token = notificationDao.getDeviceToken(phoneNums);
                    logger.info(phoneNums);
                    //去数据库查userId
                    TsUser userId = tsUserMapper.findUserId(phoneNums);
                    //推送
                    String returnCode = xinge.pushSingleDevice(token, mess, XingeApp.IOSENV_DEV).toString();
                    //判断返回状态码是否推送成功
                    if (returnCode.contains("ret_code")) {
                        //{"err_msg":"无效帐号，请检查后重试","ret_code":48}
                        JSONObject returnCodeJson = JSONObject.parseObject(returnCode);
                        String returnCodeString = String.valueOf(returnCodeJson.get("ret_code"));
                        if ("0".equals(returnCodeString)) {
                            obj.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                            obj.put(AppResultConstants.MSG, SUCCESS_EX);
                            //把推送消息插入mongodb
                            notificationMongoDBDao.insertList(ids, phoneNums, token, title, vin, context, createDate, userId, type, readflag, imageURL);
                        } else {
                            obj.put(AppResultConstants.MSG, RETURN_EX);
                            obj.put(AppResultConstants.STATUS, "返回状态码:" + returnCodeString);
                        }
                    } else {
                        obj.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                        obj.put(AppResultConstants.MSG, FAIL_EX);
                        logger.info("返回状态码:" + returnCode);
                    }
                }
            }
        } catch (Exception e) {
            obj.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            obj.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return obj;
    }


    @Override
    public JSONObject pushMsgDeviceAll(Notification no) {
        JSONObject obj = new JSONObject();
        try {
            logger.info("请求体:" + no);
            String title = no.getTitle();
            String context = no.getContext();
            String type = no.getType();
            String vin = no.getVin();
            String ids = UUID.randomUUID().toString().replaceAll("-", "");
            String imageURL = no.getImageURL();
            String phoneNum = "999999";
            String token = "999999";
            String userId = "999999";

            if (title == null || "".equals(title)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TITLE_ERROR);
            } else if (context == null || "".equals(context)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, CONTEXT_ERROR);
            } else if (type == null || "".equals(type)) {
                obj.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                obj.put(AppResultConstants.MSG, TYPE_ERROR);
            } else {
                XingeApp xinge = new XingeApp(accessId, secretKey);
                MessageIOS mess = pushClient(title, context, type, imageURL);
                //未读标识符，未读
                Integer readflag = 0;
                //设置时间格式
                String createDate = DateUtil.getDate("yyyy/MM/dd HH:mm:ss");
                String returnCode = xinge.pushAllDevice(0, mess, XingeApp.IOSENV_DEV).toString();
                //判断返回状态码是否推送成功
                if (returnCode.contains("ret_code")) {
                    //{"err_msg":"无效帐号，请检查后重试","ret_code":48}
                    JSONObject returnCodeJson = JSONObject.parseObject(returnCode);
                    String returnCodeString = String.valueOf(returnCodeJson.get("ret_code"));
                    if ("0".equals(returnCodeString)) {
                        obj.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                        obj.put(AppResultConstants.MSG, SUCCESS_EX);
                        //把推送消息插入mongodb
                        notificationMongoDBDao.insertAll(ids, phoneNum, token, title, vin, context, createDate, userId, type, readflag, imageURL);
                    } else {
                        obj.put(AppResultConstants.MSG, RETURN_EX);
                        obj.put(AppResultConstants.STATUS, "返回状态码:" + returnCodeString);
                    }
                } else {
                    obj.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                    obj.put(AppResultConstants.MSG, FAIL_EX);
                    logger.info("返回状态码:" + returnCode);
                }

            }
        } catch (Exception e) {
            obj.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            obj.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 封装信鸽SDK
     *
     * @param title    标题
     * @param context  内容
     * @param type     类型
     * @param imageURL 图片路径
     * @return mess
     */
    private MessageIOS pushClient(String title, String context, String type, String imageURL) {
        JSONObject obj = new JSONObject();
        JSONObject aps = new JSONObject();
        JSONObject alert = new JSONObject();
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
        alert.put("title", title);
        alert.put("body", context);
        alert.put("type", type);
        aps.put("sound", "beep.wav");
        aps.put("alert", alert);
        aps.put("badge", 1);
        aps.put("content-available", 1);
        aps.put("mutable-content", 1);
//        if (imageURL == null || "".equals(imageURL)) {
//            if (){
//
//            }
//        }
        obj.put("xg_media_resources", imageURL);
        obj.put("aps", aps);
        mess.setRaw(obj.toString());
        return mess;
    }
}

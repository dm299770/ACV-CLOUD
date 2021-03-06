package com.acv.cloud.services.message.impl;

import com.acv.cloud.frame.constants.AppResultConstants;
import com.acv.cloud.models.jsonBean.message.request.MessageRequest;
import com.acv.cloud.models.jsonBean.message.response.MessageResponse;
import com.acv.cloud.repository.mongotemplate.INotificationMongoDBDao;
import com.acv.cloud.services.message.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyang on 2019/01/10.
 */
@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PHONE_ERROR = "手机号异常,请检查后重试";
    private static final String TYPE_ERROR = "通知类型异常,请检查后重试";
    private static final String QUERY_SUCCESS = "推送通知查询成功";

    @Autowired
    private INotificationMongoDBDao notificationMongoDBDao;

    @Override
    public JSONObject selectMessage(MessageRequest message) {
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
        try {
            logger.info("请求体:" + message);

            String phoneNum = message.getData().getPhoneNum();
            String type = message.getData().getType(); //通知类型
            Integer pageNum = message.getData().getPageNum();
            Integer pageSize = message.getData().getPageSize();

            if (phoneNum == null || "".equals(phoneNum)) {
                json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                json.put(AppResultConstants.MSG, PHONE_ERROR);
            } else if (type == null || "".equals(type)) {
                json.put(AppResultConstants.STATUS, AppResultConstants.Paramer_ERROR);
                json.put(AppResultConstants.MSG, TYPE_ERROR);
            } else if ("all".equals(type)) {
                //查全部类型通知
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                obj.put("message", messageResponse);
                json.put("data", obj);
            } else if ("system".equals(type)) {
                //查系统类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                obj.put("message", messageResponse);
                json.put("data", obj);
            } else if ("remind".equals(type)) {
                //查提醒类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                obj.put("message", messageResponse);
                json.put("data", obj);
            } else if ("warn".equals(type)) {
                //查警告类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                obj.put("message", messageResponse);
                json.put("data", obj);
            } else {
                json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
                json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            }
        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return json;
    }
}

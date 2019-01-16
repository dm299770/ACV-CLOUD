package com.xxx.demo.services.message.impl;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.frame.constants.AppResultConstants;
import com.xxx.demo.models.jsonBean.message.request.MessageRequest;
import com.xxx.demo.models.jsonBean.message.response.MessageResponse;
import com.xxx.demo.repository.mongotemplate.INotificationMongoDBDao;
import com.xxx.demo.services.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyang on 2018/01/10.
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
        try {
            logger.info("请求体:" + message);

            String phoneNum = message.getData().getPhoneNum();
            String type = message.getData().getType(); //通知类型
            Integer pageNum = message.getPageNum();
            Integer pageSize = message.getPageSize();

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
                json.put("data", messageResponse);
            } else if ("system".equals(type)) {
                //查系统类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                json.put("data", messageResponse);
            } else if ("remind".equals(type)) {
                //查提醒类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                json.put("data", messageResponse);
            } else if ("warn".equals(type)) {
                //查警告类型
                List<MessageResponse> messageResponse = notificationMongoDBDao.queryList(phoneNum, type, pageSize, pageNum);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, QUERY_SUCCESS);
                json.put("data", messageResponse);
            }
        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return json;
    }
}

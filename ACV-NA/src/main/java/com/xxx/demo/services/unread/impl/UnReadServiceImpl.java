package com.xxx.demo.services.unread.impl;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.frame.constants.AppResultConstants;
import com.xxx.demo.models.mongdb.notification.Notification;
import com.xxx.demo.repository.mongotemplate.INotificationMongoDBDao;
import com.xxx.demo.services.unread.UnReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnReadServiceImpl implements UnReadService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String UPDATE_SUCCESS = "未读状态修改成功";
    private static final String UPDATE_ERROR = "该消息可能已经是已读状态";

    @Autowired
    private INotificationMongoDBDao notificationMongoDBDao;

    @Override
    public JSONObject updateUnRead(Notification no) {
        JSONObject json = new JSONObject();
        try {
            Integer readflag = no.getReadflag();
            String ids = no.getIds();
            logger.info("修改消息唯一id:" + ids);

            //判断状态是否为未读
            if (readflag == 0) {
                notificationMongoDBDao.updateUnRead(ids, readflag);
                json.put(AppResultConstants.STATUS, AppResultConstants.SUCCESS_STATUS);
                json.put(AppResultConstants.MSG, UPDATE_SUCCESS);
            } else if (readflag == 1) {
                //修改失败返回状态
                json.put(AppResultConstants.STATUS, AppResultConstants.FAIL_STATUS);
                json.put(AppResultConstants.MSG, UPDATE_ERROR);
            } else {
                json.put(AppResultConstants.STATUS, AppResultConstants.NOTFOUND_ERROR);
                json.put(AppResultConstants.MSG, AppResultConstants.Paramer_ERROR);
            }
        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return json;
    }
}

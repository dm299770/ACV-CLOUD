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

    @Autowired
    private INotificationMongoDBDao notificationMongoDBDao;

    @Override
    public JSONObject updateUnRead(Notification no) {
        JSONObject json = new JSONObject();
        try {
            Boolean readflag = no.getReadflag();
            //判断状态是否为未读
            if (readflag == false) {
                notificationMongoDBDao.updateUnRead(readflag);
            } else {
                //修改失败返回状态

            }

        } catch (Exception e) {
            json.put(AppResultConstants.STATUS, AppResultConstants.ERROR_STATUS);
            json.put(AppResultConstants.MSG, AppResultConstants.SEVER_ERROR);
            e.printStackTrace();
        }
        return null;
    }
}

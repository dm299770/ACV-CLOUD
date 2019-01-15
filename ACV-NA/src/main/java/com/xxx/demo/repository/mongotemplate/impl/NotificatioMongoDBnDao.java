package com.xxx.demo.repository.mongotemplate.impl;

import com.xxx.demo.models.mongdb.notification.Notification;
import com.xxx.demo.models.sys.TsUser;
import com.xxx.demo.repository.mongotemplate.INotificationMongoDBDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * 操作mongodb,把推送存入
 * Created by liyang on 2018/12/20.
 *
 * @param <T>
 */
@Repository
public class NotificatioMongoDBnDao<T> implements INotificationMongoDBDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertAll(String title, String subtitle,
                          String body,String createDate,
                          String phoneNum,String token,
                          String userId) {
        Notification no = new Notification();
        no.setTitle(title);
        no.setSubtitle(subtitle);
        no.setBody(body);
        no.setCreateDate(createDate);
        no.setUserId("99999999999");//11个9
        no.setPhoneNum("99999999999");
        no.setToken("99999999999");
        mongoTemplate.insert(no);
    }

    @Override
    public void insertList(String phoneNum, String token,
                           String title, String subtitle,
                           String body, String createDate,
                           TsUser tsUser) {
        Notification no = new Notification();
        no.setPhoneNum(phoneNum);
        no.setToken(token);
        no.setTitle(title);
        no.setSubtitle(subtitle);
        no.setBody(body);
        no.setCreateDate(createDate);
        no.setUserId(tsUser.getUserId());
        mongoTemplate.insert(no);

    }
}

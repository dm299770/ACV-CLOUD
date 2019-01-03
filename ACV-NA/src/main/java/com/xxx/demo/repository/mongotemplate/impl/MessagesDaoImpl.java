package com.xxx.demo.repository.mongotemplate.impl;

import com.xxx.demo.models.mongdb.messages.Messages;
import com.xxx.demo.repository.mongotemplate.IMessagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * 操作mongodb,把消息存入
 * Created by liyang on 2018/12/24.
 *
 * @param <T>
 */
@Repository
public class MessagesDaoImpl<T> implements IMessagesDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertSms(String phoneNum, String content, String createDate) {
        Messages me = new Messages();
        me.setContent(content);
        me.setCreateDate(createDate);
        me.setPhoneNum(phoneNum);
        mongoTemplate.insert(me);
    }
}

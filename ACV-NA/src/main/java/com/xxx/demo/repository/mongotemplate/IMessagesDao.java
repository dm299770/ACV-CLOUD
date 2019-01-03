package com.xxx.demo.repository.mongotemplate;

/**
 * Created by liyang on 2018/12/24.
 */
public interface IMessagesDao<T> {

    void insertSms(String phoneNum, String content,String createDate);
}

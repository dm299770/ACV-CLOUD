package com.xxx.demo.repository.mongotemplate;

import com.xxx.demo.models.sys.TsUser;

/**
 *
 * Created by liyang on 2018/12/20.
 *
 */
public interface INotificationMongoDBDao<T> {

    void insertAll(String title, String subtitle, String body,String createDate,
                   String phoneNum,String token,String userId);

    void insertList(String phoneNum, String token, String title, String subtitle,
                    String body, String createDate, TsUser userId);

}

package com.xxx.demo.repository.redistemplate.impl;

import com.xxx.demo.repository.redistemplate.INotificationDao;
import com.xxx.demo.repository.redistemplate.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by liyang on 2018/12/19.
 */
@Repository
public class NotificationDaoImpl implements INotificationDao {
    @Autowired
    private RedisRepository redisRepository;

    @Override
    public String getDeviceToken(String tokenList ) {
        String token= String.valueOf(redisRepository.get(tokenList));
        return token;
    }
}

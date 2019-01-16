package com.xxx.demo.repository.mongotemplate.impl;

import com.xxx.demo.models.jsonBean.message.request.MessageRequest;
import com.xxx.demo.models.jsonBean.message.response.MessageResponse;
import com.xxx.demo.models.mongdb.notification.Notification;
import com.xxx.demo.models.sys.TsUser;
import com.xxx.demo.repository.mongotemplate.INotificationMongoDBDao;
import com.xxx.demo.services.page.MongoDBPageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * 操作mongodb
 * <p>
 * Created by liyang on 2018/12/20.
 */
@Repository
public class NotificationMongoDBnDao<T> implements INotificationMongoDBDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertList(String phoneNum, String token, String title,
                           String vin, String context, String createDate,
                           TsUser tsUser, String type, Boolean readflag) {
        Notification no = new Notification();
        no.setPhoneNum(phoneNum);
        no.setToken(token);
        no.setTitle(title);
        no.setContext(context);
        no.setReadflag(readflag);
        no.setVin(vin);
        no.setType(type);
        no.setCreateDate(createDate);
        no.setUserId(tsUser.getUserId());
        mongoTemplate.insert(no);
    }

    @Override
    public void insertAll(String phoneNum, String token, String title,
                          String vin, String context, String createDate,
                          String userId, String type, Boolean readflag) {
        Notification no = new Notification();
        no.setPhoneNum(phoneNum);
        no.setToken(token);
        no.setTitle(title);
        no.setContext(context);
        no.setReadflag(readflag);
        no.setVin(vin);
        no.setType(type);
        no.setCreateDate(createDate);
        no.setUserId(userId);
        mongoTemplate.insert(no);
    }

    @Override
    public List<MessageResponse> queryList(String phoneNum, String type, Integer pageSize, Integer pageNum) {
        MongoDBPageable pageable = new MongoDBPageable();
        MessageRequest pm = new MessageRequest();
        //判断推送查询类型
        Query query = new Query();
        logger.info("消息类型:"+type);
        if (type == "all" || "all".equals(type)) {
            query.addCriteria(Criteria.where("phoneNum").is(phoneNum));
        } else {
            query.addCriteria(Criteria.where("type").is(type).and("phoneNum").is(phoneNum));
        }
        List<Order> orders = new ArrayList<Order>();  //排序
        orders.add(new Order(Direction.DESC, "phoneNum"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pm.setPageNum(pageNum);
        // 每页条数
        pm.setPageSize(pageSize);
        // 排序
        pm.setSort(sort);
        pageable.setPage(pm);
        List<Notification> noResponse = mongoTemplate.find(query.with(pageable), Notification.class);
        logger.info("历史消息查询结果:" + noResponse);
        int count = (int) mongoTemplate.count(query, Notification.class);
        logger.info("查询总条数为" + count);
        List<MessageResponse> messagesList = new ArrayList<>();
        for (Notification notificationRequestList : noResponse) {
            String title = notificationRequestList.getTitle();
            String context = notificationRequestList.getContext();
            String createDate = notificationRequestList.getCreateDate();
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setContext(context);
            messageResponse.setTitle(title);
            messageResponse.setCreateDate(createDate);
            messagesList.add(messageResponse);
        }
        Page<MessageResponse> pageList = new PageImpl<MessageResponse>(messagesList, pageable, count);
        logger.info("历史消息分页结果:" + pageList);
        List<MessageResponse> messageResponse = pageList.getContent();
        logger.info("历史消息返回结果:" + messageResponse);
        return messageResponse;
    }

    @Override
    public void updateUnRead(Boolean readflag) {

    }

}

package com.xxx.demo.models.jsonBean.message.request;

import com.xxx.demo.models.mongdb.notification.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 推送通知历史消息请求实体
 * <p>
 * Created by liyang on 2018/01/10.
 */
@Document(collection = "Notification")
public class MessageRequest {
    //通知推送实体
    private Notification data;
    //当前页
    private Integer pageNum = 1;
    //每页显示数
    private Integer pageSize = 10;
    // 排序条件
    private Sort sort;


    public Notification getData() {
        return data;
    }

    public void setData(Notification data) {
        this.data = data;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}

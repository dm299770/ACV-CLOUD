package com.xxx.demo.models.mongdb.notification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 推送实体类
 * Created by liyang on 2018/12/19.
 */
@Document(collection = "Notification")
public class Notification implements Serializable {

    @Id
    private String id;//id属性是给mongodb用的，用@Id注解修饰
    private String token;//设备token
    private String title;//标题
    private String context;//消息推送内容
    private String phoneNum;//用户手机号(账号)
    private String type;//推送类型

    private String vin;//车架号
    private Boolean readflag;//已读未读状态位
    private String comment;//备注
    private String createDate;//发送时间
    private String userId;//用户userId

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getReadflag() {
        return readflag;
    }

    public void setReadflag(Boolean readflag) {
        this.readflag = readflag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

package com.xxx.demo.models.jsonBean.message.response;


/**
 * 推送通知历史消息返回实体
 *
 * Created by liyang on 2018/01/10.
 */
public class MessageResponse {
    private String title;
    private String context;
    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}

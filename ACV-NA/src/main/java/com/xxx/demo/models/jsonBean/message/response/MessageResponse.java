package com.xxx.demo.models.jsonBean.message.response;


/**
 * 推送通知历史消息返回实体
 * <p>
 * Created by liyang on 2018/01/10.
 */
public class MessageResponse {

    private String id;
    private String title;
    private String context;
    private String createDate;
    private Boolean readflag;

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

    public Boolean getReadflag() {
        return readflag;
    }

    public void setReadflag(Boolean readflag) {
        this.readflag = readflag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

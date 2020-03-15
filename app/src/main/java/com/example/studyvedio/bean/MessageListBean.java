package com.example.studyvedio.bean;

public class MessageListBean {
    private String userName;
    private String content;
    private String time;

    public MessageListBean(String userName, String content, String time) {
        this.userName = userName;
        this.content = content;
        this.time = time;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

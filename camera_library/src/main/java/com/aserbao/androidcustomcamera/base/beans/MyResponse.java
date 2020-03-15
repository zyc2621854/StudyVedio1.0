package com.aserbao.androidcustomcamera.base.beans;

import org.json.JSONObject;

public class MyResponse<T> {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

    public void fill(JSONObject json)throws Exception{

        this.code = json.getInt("code");

        this.msg  = json.getString("msg");

    }


    @Override
    public String toString() {
        return "MyResponse{" +
                " msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}

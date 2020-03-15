package com.aserbao.androidcustomcamera.base.beans;

public interface MyCallBack<T> {
    public void onSuccess(T data);
    public void onError(String msg);
}
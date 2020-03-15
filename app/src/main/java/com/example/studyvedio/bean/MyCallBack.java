package com.example.studyvedio.bean;

public interface MyCallBack<T> {
    public void onSuccess(T data);
    public void onError(String msg);
}
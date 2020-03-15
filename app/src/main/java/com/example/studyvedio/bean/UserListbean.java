package com.example.studyvedio.bean;

import java.util.ArrayList;

public class UserListbean {
    private String total;

    public ArrayList<MediaBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<MediaBean> records) {
        this.records = records;
    }

    //    private ArrayList<Userbean> records;
    private ArrayList<MediaBean> records;
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

//    public ArrayList<Userbean> getRecords() {
//        return records;
//    }
//
//    public void setRecords(ArrayList<Userbean> records) {
//        this.records = records;
//    }



}

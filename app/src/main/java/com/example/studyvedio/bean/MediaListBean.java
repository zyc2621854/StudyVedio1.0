package com.example.studyvedio.bean;

import java.util.ArrayList;


public class MediaListBean {



    private String total;
    private ArrayList<MediaBean> records;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<MediaBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<MediaBean> records) {
        this.records = records;
    }

}

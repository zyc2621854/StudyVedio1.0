package com.example.studyvedio.bean;

import io.reactivex.Scheduler;

public class LevideoData {
    private String tvLike;
    private String tvComment;
    private String tvReprinted;

    public String getTvLike() {
        return tvLike;
    }

    public void setTvLike(String tvLike) {
        this.tvLike = tvLike;
    }

    public String getTvComment() {
        return tvComment;
    }

    public void setTvComment(String tvComment) {
        this.tvComment = tvComment;
    }

    public String getTvReprinted() {
        return tvReprinted;
    }

    public void setTvReprinted(String tvReprinted) {
        this.tvReprinted = tvReprinted;
    }
}

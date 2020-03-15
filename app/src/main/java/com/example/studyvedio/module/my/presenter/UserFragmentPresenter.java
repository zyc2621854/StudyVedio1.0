package com.example.studyvedio.module.my.presenter;

import com.example.studyvedio.module.my.model.UserFragmentBiz;
import com.example.studyvedio.module.my.view.IUserFragmentView;

import javax.inject.Inject;

public class UserFragmentPresenter {
    private IUserFragmentView mIUserFragmentView;
    private UserFragmentBiz mUserFragmentBiz;

    @Inject
    public UserFragmentPresenter(IUserFragmentView mIUserFragmentView){
        this.mIUserFragmentView=mIUserFragmentView;
        mUserFragmentBiz = new UserFragmentBiz();
    }

    public void loading(){

    }

}

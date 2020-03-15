package com.example.studyvedio.module.my.module;

import com.example.studyvedio.module.my.presenter.UserFragmentPresenter;
import com.example.studyvedio.module.my.view.IUserFragmentView;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Module
public class UserMainModule {
    private IUserFragmentView mUserFragmentView;

    public UserMainModule(IUserFragmentView userFragmentView){
        mUserFragmentView = userFragmentView;
    }

    @Provides
    public UserFragmentPresenter getUserFragmentPresenter(){
        return new UserFragmentPresenter(mUserFragmentView);
    }
}

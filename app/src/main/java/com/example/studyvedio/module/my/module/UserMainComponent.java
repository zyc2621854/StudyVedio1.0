package com.example.studyvedio.module.my.module;

import com.example.studyvedio.module.my.ui.UserFragment;

import dagger.Component;

@Component(modules = UserMainModule.class)
public interface UserMainComponent {
    void inject(UserFragment userFragment);
}

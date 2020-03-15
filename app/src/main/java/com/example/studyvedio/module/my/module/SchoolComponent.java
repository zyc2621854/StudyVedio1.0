package com.example.studyvedio.module.my.module;

import com.example.studyvedio.module.my.ui.UserFragment;

import dagger.Component;

@Component(modules = MainModules.class)
public interface SchoolComponent {
    void inject(UserFragment userFragment);
}

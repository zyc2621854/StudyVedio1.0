package com.example.studyvedio.module.my.module;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModules {
    @Provides
    public School provideSchool(){
        return new School("南京高级中学","江苏南京");
    }
}

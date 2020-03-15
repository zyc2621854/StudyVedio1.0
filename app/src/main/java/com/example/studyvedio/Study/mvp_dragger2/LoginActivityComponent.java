package com.example.studyvedio.Study.mvp_dragger2;

import dagger.Component;

@Component(modules = LoginModule.class)
public interface LoginActivityComponent {
    void inject(StudyActivity activity);
}

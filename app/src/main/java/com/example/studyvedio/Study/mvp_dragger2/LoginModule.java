package com.example.studyvedio.Study.mvp_dragger2;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private ILoginView mILoginView;
    public LoginModule(ILoginView loginView){
        this.mILoginView = loginView;
    }

//    @Provides
//    public LoginPresenter getLoginPrensenter(){
//        return new LoginPresenter(mILoginView,loginBiz);
//    }

    @Provides
    public LoginBiz getLoginBiz(){
        return new LoginBiz();
    }
}

package com.example.studyvedio.Study.mvp_dragger2;

public class LoginPresenter {
    private ILoginView loginView;
    private LoginBiz loginBiz;

    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        this.loginBiz = loginBiz;
    }

    public void doLogin(){
        loginView.showTip("Loading。。。");
        String result = loginBiz.login(loginView.getName(),loginView.getPwd());
    }
}

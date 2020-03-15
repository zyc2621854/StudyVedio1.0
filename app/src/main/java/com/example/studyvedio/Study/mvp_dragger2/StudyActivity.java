package com.example.studyvedio.Study.mvp_dragger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyvedio.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.DaggerActivity;

public class StudyActivity extends AppCompatActivity implements ILoginView {
    @Inject
    LoginPresenter mPresenter;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_pwd)
    EditText edtPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_show)
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ButterKnife.bind(this);
        //mPresenter = new LoginPresenter(this);
//        DaggerLoginActivityComponent.builder()//套路要求
//                .loginModule(new LoginModule(this))//显式传入，LoginPresenter必要的参数ILoginView
//                .build()//套路要求
//                .inject(this);



    }

    private boolean checkInput(){
        if (edtName.getText().toString().equals("")){
            showTip("请输入姓名");
            return false;
        }
        if (edtPwd.getText().toString().equals("")){
            showTip("请输入密码");
            return false;
        }
        return true;
    }
    //传递用户名


    @Override
    public String getName() {
        return edtName.getText().toString();
    }

    @Override
    public String getPwd() {
        return edtPwd.getText().toString();
    }

    @Override
    public void showTip(String tip) {
        Toast.makeText(this,tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setResult(String result) {
        txtShow.setText(result);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if(checkInput()) mPresenter.doLogin();
    }
}

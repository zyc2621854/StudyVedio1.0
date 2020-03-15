package com.example.studyvedio;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.widget.ClearWriteEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class EditNormalActivtiy extends BaseActivity {

    public static final String EDIT_TYPE = "EDIT_TYPE";
    public static final int EDIT_NAME = 10;
    public static final int EDIT_NUM = 11;
    public static final int EDIT_INTRODUCTION = 12;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvHint)
    TextView tvHint;
    @BindView(R.id.editText)
    ClearWriteEditText editText;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.editLines)
    EditText editLines;

    private int type;

    @Override
    protected void initView() {
        type = getIntent().getIntExtra(EDIT_TYPE, EDIT_NAME);
        switch (type) {
            case EDIT_NAME:
                editText.setVisibility(VISIBLE);
                tvNote.setVisibility(VISIBLE);
                editLines.setVisibility(GONE);
                //editText.text = Editable.Factory.getInstance().newEditable(userInfo.name)
                tvTitle.setText("修改昵称");
                tvHint.setText("我的昵称");
                tvNote.setText("3/20");
                break;
            case EDIT_NUM:
//                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(16));
//                editText.keyListener = DigitsKeyListener.getInstance("" +
//                        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.");
                editText.setVisibility(VISIBLE);
                tvNote.setVisibility(VISIBLE);
                editLines.setVisibility(GONE);
                //editText.text = Editable.Factory.getInstance().newEditable(userInfo.number)
                tvTitle.setText("修改抖音号");
                tvHint.setText("我的抖音号");
                tvNote.setText("最多16个字，只允许包含字母、数字、下划线和点，30天内仅能修改一次");
                break;
            case EDIT_INTRODUCTION:
                editText.setVisibility(GONE);
                tvNote.setVisibility(GONE);
                editLines.setVisibility(VISIBLE);
                tvTitle.setText("修改简介");
                tvHint.setText("个人简介");

        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_normal;
    }



    @OnClick({R.id.ivBack, R.id.tvSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSave:
                break;
        }
    }
}

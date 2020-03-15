package com.example.studyvedio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.studyvedio.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditMessageActivity extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivHead)
    QMUIRadiusImageView ivHead;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.tvIntroduction)
    TextView tvIntroduction;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.tvSchool)
    TextView tvSchool;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.text8)
    TextView text8;
    @BindView(R.id.tvArea)
    TextView tvArea;

    @Override
    protected void initView() {
//        initToolbar(toolbar);
//        GlideApp.with(EditMessageActivity.this)
//                .load(Constants.userInfo.getUrl())
//                .error(R.mipmap.logo)
//                .transform(new ColorFilterTransformation(0x79000000))
//                .into(ivHead);
//        setMessage();
//        initBirthday();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_message;
    }


    @OnClick({R.id.ivBack, R.id.ivHead, R.id.tvName, R.id.tvNumber, R.id.tvIntroduction, R.id.tvSchool, R.id.tvGender, R.id.tvBirthday, R.id.tvArea})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivHead:
                showHeadDialog();
                break;
            case R.id.tvName:
                startEdit(EditNormalActivtiy.EDIT_NAME);
                break;
            case R.id.tvNumber:
                startEdit(EditNormalActivtiy.EDIT_NUM);
                break;
            case R.id.tvIntroduction:
                startEdit(EditNormalActivtiy.EDIT_INTRODUCTION);
                break;
            case R.id.tvSchool:
                break;
            case R.id.tvGender:
                showGenderDialog();
                break;
            case R.id.tvBirthday:
                //pvTime.show();
                break;
            case R.id.tvArea:
                break;
        }
    }

    private void showHeadDialog() {
        final String[] items = new String[]{"拍一张", "相册选择", "查看大图", "取消"};
        new QMUIDialog.MenuDialogBuilder(EditMessageActivity.this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void startEdit(int type) {
        Intent intent = new Intent(EditMessageActivity.this, EditNormalActivtiy.class);
        intent.putExtra(EditNormalActivtiy.EDIT_TYPE, type);
        startActivity(intent);
    }

    private void showGenderDialog() {
        final String[] items = new String[]{"男", "女", "不显示"};
        new QMUIDialog.MenuDialogBuilder(EditMessageActivity.this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Constants.userInfo.setGender(items[which]);
                        tvGender.setText(items[which]);
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
//
//    /**
//     * 编辑
//     *
//     * @param type
//     */
//    private void startEdit(int type) {
//        Intent intent = new Intent(EditMessageActivity.this, EditNormalActivtiy.class);
//        intent.putExtra(EditNormalActivtiy.Companion.getEDIT_TYPE(), type);
//        startActivity(intent);
//    }
//
//    /**
//     * 选择生日
//     */
//    private void initBirthday() {
//        Calendar selectedDate = Calendar.getInstance();//系统当前时间
//        Calendar startDate = Calendar.getInstance();
//        startDate.set(2014, 1, 23);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2027, 2, 28);
//        //时间选择器
//        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvBirthday.setText(DateUtils.INSTANCE.dateToYMD(date));
//            }
//        })
//                .setDate(selectedDate)
//                .setRangDate(startDate, endDate)
//                .setLayoutRes(R.layout.dialog_birthday, new CustomListener() {
//
//                    @Override
//                    public void customLayout(View v) {
//                        ConstraintLayout constraintLayout = v.findViewById(R.id.constraintLayout);
//                        TextView tvConfirm = v.findViewById(R.id.tvConfirm);
//                        View view = v.findViewById(R.id.view);
//                        View viewBg = v.findViewById(R.id.viewBg);
//                        Switch switchBtn = v.findViewById(R.id.switchBtn);
//                        constraintLayout.setOnClickListener(v13 -> {});
//                        view.setOnClickListener(v1 -> {});
//                        viewBg.setOnClickListener(v14 -> {});
//                        if (!TextUtils.equals("不显示", tvBirthday.getText().toString())){
//                            switchBtn.setChecked(false);
//                        }
//                        switchBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                            if (isChecked) {
//                                viewBg.setVisibility(View.VISIBLE);
//                            } else {
//                                viewBg.setVisibility(View.GONE);
//                            }
//                        });
//                        tvConfirm.setOnClickListener(v12 -> {
//                            if (switchBtn.isChecked()){
//                                tvBirthday.setText("不显示");
//                            } else {
//                                pvTime.returnData();
//                            }
//                            pvTime.dismiss();
//                        });
//                    }
//                })
//                .setContentTextSize(18)
//                .setType(new boolean[]{true, true, true, false, false, false})
//                .setLabel("", "", "", "", "", "")
//                .setLineSpacingMultiplier(2.2f)
//                .setGravity(Gravity.CENTER)
//                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setDividerColor(0xFF4d4d4d)
//                .setDividerType(WheelView.DividerType.FILL)
//                .build();
//    }
}

package com.example.studyvedio.module.my.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studyvedio.ContactActivity;
import com.example.studyvedio.EditMessageActivity;
import com.example.studyvedio.R;
import com.example.studyvedio.base.BaseFragment;
import com.example.studyvedio.base.BasePresenter;
import com.example.studyvedio.bean.MediaListBean;
import com.example.studyvedio.bean.MyCallBack;
import com.example.studyvedio.bean.UserListbean;
import com.example.studyvedio.bean.Userbean;
import com.example.studyvedio.http.API;
import com.example.studyvedio.module.home.adapter.MyPagerAdapter;
import com.example.studyvedio.module.my.module.School;
import com.example.studyvedio.module.my.module.UserMainComponent;
import com.example.studyvedio.module.my.module.UserMainModule;
import com.example.studyvedio.module.my.presenter.UserFragmentPresenter;
import com.example.studyvedio.module.my.view.IUserFragmentView;
import com.example.studyvedio.widget.emojiview.EmojiconTextView;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class UserFragment extends BaseFragment implements IUserFragmentView {

    @BindView(R.id.tablayout)
    QMUITabSegment tablayout;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.ivHead)
    QMUIRadiusImageView ivHead;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.tvFriend)
    ImageView tvFriend;
    @BindView(R.id.tvName)
    EmojiconTextView tvName;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvIntroduction)
    EmojiconTextView tvIntroduction;
    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.tvAttention)
    TextView tvAttention;
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.tvTakePhoto)
    TextView tvTakePhoto;
    @BindView(R.id.llTakePhoto)
    LinearLayout llTakePhoto;

    @Inject
    UserFragmentPresenter mUserFragmentPresenter;
    @Inject
    School mSchool;
    private MyPagerAdapter adapter;

    String[] mTitles = new String[]{"作品", "动态", "喜欢"};


    @Override
    protected void initListerner() {

    }

    @Override
    protected int setView() {
        return R.layout.fragment_user;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }



    @Override
    protected void initView(View view) {
        /** 此处为dragger测试添加 **/
        //DaggerUserMainComponent.builder().userMainModule.(new UserMainModule(this)).build().inject.(this);
        //DaggerSchoolComponent.

        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new WorkListFragment());
        mFragments.add(new WorkListFragment());
        mFragments.add(new WorkListFragment());
        adapter = new MyPagerAdapter(getChildFragmentManager(),mFragments);
        viewPager.setAdapter(adapter);
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            int finalI = i;
            mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mTitles[finalI];
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });

            SpannableString sp = new SpannableString(mTitles[i]);
            sp.setSpan(new StyleSpan(Typeface.BOLD), 0, sp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            QMUITabSegment.Tab tab = new QMUITabSegment.Tab(sp);
            tab.setText(sp);
            tab.setTextColor(Color.parseColor("#A9A9A9"), Color.WHITE);
            tablayout.addTab(tab);
        }
        //        mTabLayout.setTabData(mTabEntities);

        tablayout.setHasIndicator(true);  //是否需要显示indicator
//        tablayout.setIndicatorDrawable();
        tablayout.setIndicatorPosition(false);//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
        tablayout.setIndicatorWidthAdjustContent(true);//设置 indicator的宽度是否随内容宽度变化
        tablayout.setupWithViewPager(viewPager, false);
        tablayout.notifyDataChanged();
        tablayout.selectTab(0, true, true);
        Log.d(TAG, "initView: end");



    }
    int  i=0;
    @Override
    protected void initData(Bundle savedInstanceState) {

        Log.d(TAG, "initData: start");
        API.getInstance().getUserInfo(getActivity(), 1, new MyCallBack<Userbean>() {
            @Override
            public void onSuccess(Userbean data) {
                i=data.getFollowCount();
                Log.d(TAG, "onSuccess: start"+data.getFollowCount());
                tvFans.setText(""+data.getFollowCount()+" 关注");
                tvAttention.setText(""+data.getFollowerCount()+" 粉丝");
                Log.d(TAG, "onSuccess: end");
            }

            @Override
            public void onError(String msg) {

            }
        });
        /** 已确定是Data的问题使得函数无法执行  原因未知 **/
        /** 基本可确定是MediaBean的问题 **/
        /** 基本可证明是MediaListBean类出现了问题 导致无法运行onNext **/
        API.getInstance().getUserList(getActivity(), new MyCallBack<UserListbean>() {
            @Override
            public void onSuccess(UserListbean data) {
                /** 已执行该方法 但没有正确获取数据 **/
                Log.d(TAG, "onSuccess: "+data.getRecords().toString());
                /** VedioList的Total已成功读取 **/
                Log.d(TAG, "onSuccess: "+data.getTotal());
                //Log.d(TAG, "onSuccess: "+data.getRecords().get(0).getVedioUrl());
                Log.d(TAG, "onSuccess: 测试再次成功");
            }

            @Override
            public void onError(String msg) {

            }
        });
        /** 此处也无法实现vediolist的next **/
//        API.getInstance().getVideoList(getActivity(), 1, 3, new MyCallBack<MediaListBean>() {
//
//            @Override
//            public void onSuccess(MediaListBean data) {
//                Log.d(TAG, "onSuccess: ===========================================================================================");
//                //Log.d(TAG, "onSuccess: "+data.getRecords().get(1).getVedioUrl());
//                //mMediaListBean=data;
//            }
//
//            @Override
//            public void onError(String msg) {
//                Log.d(TAG, "onError: ==============================================================================================");
//            }
//        });
        Log.d(TAG, "initData: end");

        Log.d(TAG, "initData: end"+i);
    }

    @Override
    protected void Load() {

    }

    @OnClick({R.id.ivHead, R.id.tvEdit, R.id.tvFriend, R.id.llTakePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHead:
                break;
            case R.id.tvEdit:
                startActivity(new Intent(getActivity(), EditMessageActivity.class));
                break;
            case R.id.tvFriend:
                startActivity(new Intent(getActivity(), ContactActivity.class));
                break;
            case R.id.llTakePhoto:
                break;
        }
    }

}

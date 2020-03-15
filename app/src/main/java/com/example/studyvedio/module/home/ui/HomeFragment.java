package com.example.studyvedio.module.home.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyvedio.MainActivity;
import com.example.studyvedio.R;
import com.example.studyvedio.SearchActivity;
import com.example.studyvedio.base.BaseFragment;
import com.example.studyvedio.base.BasePresenter;
import com.example.studyvedio.module.home.adapter.MyPagerAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvRandom)
    TextView tvRandom;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.tablayout)
    QMUITabSegment tablayout;
    @BindView(R.id.ivLive)
    ImageView ivLive;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.swipeLayout)
    ConstraintLayout swipeLayout;
    Unbinder unbinder;


    @OnClick({R.id.tvRandom, R.id.ivSearch, R.id.ivLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRandom:
                break;
            case R.id.ivSearch:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.ivLive:
                break;
        }
    }

    private MyPagerAdapter adapter;

    String[] mTitles = new String[]{"关注","推荐","随机"};

    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new HomeListFragment());
        mFragments.add(new HomeListFragment());
        mFragments.add(new HomeListFragment());
        adapter = new MyPagerAdapter(getChildFragmentManager(), mFragments);
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
        //EventBus.getDefault().post(new SwipeBean(viewPager.getCurrentItem()));


    }


    @Override
    protected void initListerner() {
        mTabLayout.setIndicatorWidth(30);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //MyLog.d("position  onPageScrolled : " + position + positionOffset + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //MyLog.d("position : " + position);
                //EventBus.getDefault().post(new SwipeBean(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //MyLog.d("position  onPageScrollStateChanged : " + state);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }

}

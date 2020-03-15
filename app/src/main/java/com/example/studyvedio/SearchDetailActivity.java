package com.example.studyvedio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.module.home.adapter.MyPagerAdapter;
import com.example.studyvedio.module.home.ui.SearchSynthesizeFragment;
import com.example.studyvedio.module.home.ui.SearchVedioFragment;
import com.example.studyvedio.module.my.ui.WorkListFragment;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchDetailActivity extends BaseActivity {

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tablayout)
    QMUITabSegment tablayout;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;

    private MyPagerAdapter adapter;

    String[] mTitles = new String[]{"综合", "视频", "用户", "音乐", "暂无"};

    @Override
    protected void initView() {
        searchView.setIconified(false);

        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new SearchSynthesizeFragment());
        mFragments.add(new SearchVedioFragment());
        mFragments.add(new WorkListFragment(WorkListFragment.USER_LIST));
        mFragments.add(new WorkListFragment());
        mFragments.add(new WorkListFragment());
        adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments);
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
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_detail;
    }




    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }
}

package com.example.studyvedio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.studyvedio.module.home.adapter.MyPagerAdapter;
import com.example.studyvedio.module.home.ui.HomeFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//tab切换实验
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.ivBottom)
    ImageView ivBottom;

    private MyPagerAdapter mMyPagerAdapter;
    private String[] mStrings = {"1", "2", "", "3", "4"};
    private ArrayList<CustomTabEntity> mCustomTabEntities = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());

        mMyPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(mMyPagerAdapter);

        for (int i = 0; i < mStrings.length; i++) {
            final int finalI = i;
            mCustomTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mStrings[finalI];
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
        }
        mTabLayout.setTabData(mCustomTabEntities);
    }

}

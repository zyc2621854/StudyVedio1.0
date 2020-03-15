package com.example.studyvedio;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aserbao.androidcustomcamera.whole.record.RecorderActivity;
import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.module.home.adapter.MyPagerAdapter;
import com.example.studyvedio.module.home.ui.HomeFragment;
import com.example.studyvedio.module.message.ui.MessageFragment;
import com.example.studyvedio.module.my.ui.UserFragment;
import com.example.studyvedio.utils.DisplayUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
  
    @BindView(R.id.tvCard)
    TextView tvCard;
    @BindView(R.id.tvPentagram)
    TextView tvPentagram;
    @BindView(R.id.tvWallet)
    TextView tvWallet;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.tvShop)
    TextView tvShop;
    @BindView(R.id.tvLifeOrder)
    TextView tvLifeOrder;
    @BindView(R.id.tvDOU)
    TextView tvDOU;
    @BindView(R.id.tvFlow)
    TextView tvFlow;
    @BindView(R.id.llService)
    LinearLayout llService;
    @BindView(R.id.viewService)
    View viewService;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.tvMinor)
    TextView tvMinor;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    @BindView(R.id.tvApplets)
    TextView tvApplets;
    @BindView(R.id.tvHeadline)
    TextView tvHeadline;
    @BindView(R.id.llApplets)
    LinearLayout llApplets;
    @BindView(R.id.right_Linear)
    LinearLayout rightLinear;
    @BindView(R.id.side_right)
    ScrollView sideRight;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.random_shoot)
    LinearLayout randomShoot;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.ivBottom)
    ImageView ivBottom;
    @BindView(R.id.container)
    android.support.constraint.ConstraintLayout container;

    private MyPagerAdapter mMyPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{"首页", "教学","","消息", "我"};
    //private boolean TabFlag = true;




    /**
      * @CreateDate : created at 2019/11/27 0027 下午 5:41
      * @Description : 曾经使用baseActivity的initView Tab无法正常加载
      * 原因是重写onCreate()方法时，再次使用了setContentView方法，导致页面重新加载，故在此之前关于界面的函数失效。
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initView() {
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new Fragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new UserFragment());

        mMyPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(mMyPagerAdapter);

        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        Log.d(TAG, "initView: Tabadd");
        for(int i = 0; i < mTitles.length; i++){
            final int finalI = i;
            mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    Log.d(TAG, "getTabTitle: "+mTitles[finalI]);
                    return mTitles[finalI];
                }

                @Override
                public int getTabSelectedIcon() {
                    return R.mipmap.icon_music1 ;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
            //if (i == mTitles.length - 1) TabFlag = false;
        }
//        while(TabFlag){
//            Log.d(TAG, "initView: "+mTabEntities.size());
        Log.d(TAG, "initView: addTabLayout"+mTabEntities.size());
        Log.d(TAG, "initView: "+mTabEntities.get(0).getTabTitle());
        mTabLayout.setTabData(mTabEntities);
//        }

    }

    @Override
    protected void initListener() {
        TextView textView = mTabLayout.getTitleView(0);
        String text = textView.getText().toString().trim();
        TextPaint textPaint = textView.getPaint();
        int textPaintWidth = (int) textPaint.measureText(text);
        mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //设置viewpager和Tab
                viewPager.setCurrentItem(position);
                TextView textView = mTabLayout.getTitleView(position);
                String text = textView.getText().toString().trim();
                TextPaint textPaint = textView.getPaint();
                int textPaintWidth = (int) textPaint.measureText(text);
                mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
                if (position == 0) {     //首页

                } else if (position == 4) {   //我的

                } else {

                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    //录制界面
    @OnClick(R.id.ivBottom)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, RecorderActivity.class));
    }
}

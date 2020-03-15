package com.example.studyvedio;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.module.home.adapter.DouYinAdapter;
import com.example.studyvedio.widget.VerticalViewPager;
import com.example.studyvedio.widget.media.VideoListener;
import com.example.studyvedio.widget.media.VideoPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;
//视频滑动
public class TextActivity6 extends BaseActivity implements VideoListener {
    private VideoPlayer videoPlayer;
    private VideoPlayer videoPlayer2;
    private VerticalViewPager mVerticalViewPager;
    private DouYinAdapter mDouYinAdapter;
    private List<View> mViews = new ArrayList<>();


    private int position;
    private int mCurrentItem;
    private int mPlayingPosition;
    //已成功在viewpager中加载视频  问题原因是布局文件中tools:contxt限定了context，致使context错误
    //目前无法解决在两个pager中加载两个视频


    @Override
    protected void initData() {
        position=0;
        mCurrentItem=position;
       //videoPlayer =new VideoPlayer(this);

        mVerticalViewPager=findViewById(R.id.viewPager);
        View view = LayoutInflater.from(this).inflate(R.layout.item_video_test, null);
        videoPlayer = view.findViewById(R.id.video);
        videoPlayer.setVideoListener(this);
        videoPlayer.setPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        try {
            videoPlayer.load();
        } catch (IOException e) {
            Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        videoPlayer.start();

        View view2 =LayoutInflater.from(this).inflate(R.layout.item_video_test, null);
        videoPlayer2 = view2.findViewById(R.id.video);
        videoPlayer2.setVideoListener(this);
        //videoPlayer.setPath("http://ipfs.ztgame.com.cn/QmRRGU4aUZEqJsHxKzBb1ns97GHw45eCRRZFe6Eu8GCmZ4.m3u8");
        videoPlayer2.setPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        try {
            videoPlayer2.load();
        } catch (IOException e) {
            Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        videoPlayer2.start();
        mViews.add(view);
        mViews.add(view2);

    }

    @Override
    protected void initView() {
        mDouYinAdapter = new DouYinAdapter(mViews);
        mVerticalViewPager.setAdapter(mDouYinAdapter);
        if (position != -1) {
            mVerticalViewPager.setCurrentItem(position);
        }
    }

    @Override
    protected void initListener() {
        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if(state == VerticalViewPager.SCROLL_STATE_IDLE){
//                    videoPlayer.release();
//                    ViewParent parent = videoPlayer.getParent();
//                    if(parent!=null && parent instanceof FrameLayout){
//                        ((FrameLayout)parent).removeView(videoPlayer);
//                    }
//                    startPlay();
//                }
            }
        });
    }

    private void startPlay(){
        View view = mViews.get(mCurrentItem);
        FrameLayout frameLayout = view.findViewById(R.id.video);
        ViewGroup parent = (ViewGroup) videoPlayer.getParent();
        if(parent!=null){
            parent.removeAllViews();
        }
        frameLayout.addView(videoPlayer);
        videoPlayer.setVideoListener(this);
        videoPlayer.setPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        try {
            videoPlayer2.load();
        } catch (IOException e) {
            Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        videoPlayer.start();

        mPlayingPosition = mCurrentItem;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_text6;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        videoPlayer.start();
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}

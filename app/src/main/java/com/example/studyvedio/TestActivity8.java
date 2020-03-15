package com.example.studyvedio;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.widget.media.VideoListener;
import com.example.studyvedio.widget.media.VideoPlayer;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;

//因未知原因BaseActivity的子类无法使用播放器
//已解决：未在函数中添加videoPlayer.start();
//成功实现在两个布局中轮流加载播放器
public class TestActivity8 extends BaseActivity implements VideoListener {


    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.fm2)
    FrameLayout fm2;
    @BindView(R.id.fm1)
    FrameLayout fm1;
    @BindView(R.id.activity_7_ll)
    ConstraintLayout activity7Ll;
    private VideoPlayer videoPlayer;

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        videoPlayer = new VideoPlayer(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test8;
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

    //这个函数中需要添加videoPlayer.start();！！！！！！！！！！！！！！！！！！！！！！！！！
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

    @OnClick({R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        ConstraintLayout constraintLayout = findViewById(R.id.activity_7_ll);
        FrameLayout frameLayout1 = findViewById(R.id.fm1);
        FrameLayout frameLayout2 = findViewById(R.id.fm2);
        switch (view.getId()) {
            case R.id.bt1:
                frameLayout2.removeAllViews();
                videoPlayer.release();
                frameLayout1.removeAllViews();
                frameLayout1.addView(videoPlayer);
                videoPlayer.setVideoListener(this);
                videoPlayer.setPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
                try {
                    videoPlayer.load();
                    }catch (IOException e) {
                        Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
                        e.printStackTrace();
                    }
                    videoPlayer.start();

                break;
            case R.id.bt2:
                frameLayout1.removeAllViews();
                videoPlayer.release();
                frameLayout1.removeAllViews();
                frameLayout2.addView(videoPlayer);
                videoPlayer.setVideoListener(this);
                videoPlayer.setPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
                try {
                    videoPlayer.load();
                }catch (IOException e) {
                    Toast.makeText(this,"播放失败",Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
                videoPlayer.start();
                break;
        }
    }
}

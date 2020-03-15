package com.example.studyvedio;

import android.content.Intent;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.utils.MyLog;
import com.example.studyvedio.widget.CommentDialog;
import com.example.studyvedio.widget.media.DouYinController;

/** 集成播放器实验 **/
/** 评论框实验 **/
public class TestActivity7 extends BaseActivity  {

    private IjkVideoView mIjkVideoView;
    private DouYinController mDouYinController;

    private CommentDialog.Builder builder;
    @Override
    protected void initData() {
        mIjkVideoView = findViewById(R.id.video);
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(this);
        mIjkVideoView.setVideoController(mDouYinController);
        mIjkVideoView.setUrl("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_DEFAULT);
        mIjkVideoView.start();

        MyLog.d("builder : " + builder);
        if (builder != null) {
            builder.show();
        } else {
            builder = new CommentDialog.Builder(this).setOnDialogListener(new CommentDialog.Builder.OnDialogListener() {
                @Override
                public void emojiClick() {
                    //startActivity(new Intent(this, EmojiActivity.class));
                }

                @Override
                public void atClick() {
                }

                @Override
                public void commentClick() {
                    //startActivity(new Intent(this, EmojiActivity.class));
                }
            });
            builder.show();
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_test7;
    }

    
}

package com.example.studyvedio.module.home.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.example.studyvedio.MainActivity;
import com.example.studyvedio.R;
import com.example.studyvedio.base.BaseFragment;
import com.example.studyvedio.base.BasePresenter;
import com.example.studyvedio.bean.MediaListBean;
import com.example.studyvedio.bean.MyCallBack;
import com.example.studyvedio.http.API;
import com.example.studyvedio.module.home.adapter.DouYinAdapter;
import com.example.studyvedio.utils.Constants;
import com.example.studyvedio.widget.CommentDialog;
import com.example.studyvedio.widget.VerticalViewPager;
import com.example.studyvedio.widget.media.DouYinController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 存在问题：滑动后才开始加载
 **/
public class HomeListFragment extends BaseFragment {




    Unbinder unbinder;
    private IjkVideoView mIjkVideoView;
    private DouYinController mDouYinController;
    private VerticalViewPager mVerticalViewPager;
    private DouYinAdapter mDouYinAdapter;
    private List<View> mViews = new ArrayList<>();
    private CommentDialog.Builder builder;

    private int position;
    private int mCurrentItem;
    private int mPlayingPosition;
    private MediaListBean mMediaListBean = new MediaListBean();

    @Override
    protected int setView() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        position = 0;
        mCurrentItem = position;
        mIjkVideoView = new IjkVideoView(getActivity());
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(getActivity());
        mIjkVideoView.setVideoController(mDouYinController);
        mVerticalViewPager = view.findViewById(R.id.verticalviewpager);
        //无法用这种方法创建两个view，猜测是view的context被覆盖。
        for (int i = 0; i < 4; i++) {
            View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_home, null);

            MaterialRatingBar materialRatingBar =view1.findViewById(R.id.ratingBar);
            materialRatingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
                @Override
                public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                    Toast.makeText(getActivity(), "视频获得"+rating+"分", Toast.LENGTH_SHORT).show();
                }
            });
            TextView textView = view1.findViewById(R.id.tvComment);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (builder != null) {
                        builder.show();
                    } else {
                        builder = new CommentDialog.Builder(getActivity()).setOnDialogListener(new CommentDialog.Builder.OnDialogListener() {
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
            });
            mViews.add(view1);
        }

        mDouYinAdapter = new DouYinAdapter(mViews);
        mVerticalViewPager.setAdapter(mDouYinAdapter);
        if (position != -1) {
            mVerticalViewPager.setCurrentItem(position);
        }
    }

    @Override
    protected void initListerner() {
        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: =================================================================" + position);
                mCurrentItem = position;
                mIjkVideoView.pause();
//                Log.d(TAG, "onPageSelected: "+mMediaListBean.getRecords().get(position).getVedioUrl());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    //释放之前的播放器所有资源
                    mIjkVideoView.release();
                    ViewParent parent = mIjkVideoView.getParent();
                    if (parent != null && parent instanceof FrameLayout) {
                        ((FrameLayout) parent).removeAllViews();
                    }
                    startPlay();
                }
            }
        });
    }

    private void startPlay() {
        View view = mViews.get(mCurrentItem);
        FrameLayout frameLayout = view.findViewById(R.id.video);
        frameLayout.removeAllViews();
//        ViewGroup parent = (ViewGroup) videoPlayer.getParent();
//        if(parent!=null){
//            parent.removeAllViews();
//        }
        frameLayout.addView(mIjkVideoView);
        //mIjkVideoView.setUrl("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
        Log.d(TAG, "startPlay: ========================================================================================" + Integer.toString(mCurrentItem));
        mIjkVideoView.setUrl(Constants.BASE_URL + "/download/" + mMediaListBean.getRecords().get(mCurrentItem).getVedioUrl());
        //mIjkVideoView.setUrl(Constants.BASE_URL+"/download/"+mMediaListBean.getRecords().get(0).getVedioUrl());
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_DEFAULT);
        mIjkVideoView.start();

        mPlayingPosition = mCurrentItem;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Log.d(TAG, "initData: start");
        API.getInstance().getVideoList(getActivity(), 1, 4, new MyCallBack<MediaListBean>() {

            @Override
            public void onSuccess(MediaListBean data) {
                //Log.d(TAG, "onSuccess: ===========================================================================================");
                //Log.d(TAG, "onSuccess: "+data.getRecords().get(1).getVedioUrl());
                mMediaListBean = data;
                /** 在initListener使用该函数不行 由此可见 **/
                startPlay();
            }

            @Override
            public void onError(String msg) {
                Log.d(TAG, "onError: ==============================================================================================");
            }
        });
        Log.d(TAG, "initData: end");
    }

    @Override
    protected void Load() {

    }


}

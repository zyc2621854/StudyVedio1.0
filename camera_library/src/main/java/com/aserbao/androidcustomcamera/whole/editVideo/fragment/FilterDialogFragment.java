package com.aserbao.androidcustomcamera.whole.editVideo.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aserbao.androidcustomcamera.R;
import com.aserbao.androidcustomcamera.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * description:
 * Created by aserbao on 2018/1/27.
 */


public class FilterDialogFragment extends DialogFragment {


    @BindView(R2.id.pop_filter_tv)
    TextView mPopFilterTv;
    @BindView(R2.id.origin)
    TextView mOrigin;
    @BindView(R2.id.delta)
    TextView mDelta;
    @BindView(R2.id.electric)
    TextView mElectric;
    @BindView(R2.id.slowlived)
    TextView mSlowlived;
    @BindView(R2.id.tokyo)
    TextView mTokyo;
    @BindView(R2.id.warm)
    TextView mWarm;
    @BindView(R2.id.pop_filter_sv)
    HorizontalScrollView mPopFilterSv;
    @BindView(R2.id.none_iv)
    ImageView mNoneIv;
    @BindView(R2.id.one_tv)
    TextView mOneTv;
    @BindView(R2.id.two_tv)
    TextView mTwoTv;
    @BindView(R2.id.three_tv)
    TextView mThreeTv;
    @BindView(R2.id.four_tv)
    TextView mFourTv;
    @BindView(R2.id.five_tv)
    TextView mFiveTv;
    @BindView(R2.id.pop_beauty_ll)
    LinearLayout mPopBeautyLl;
    @BindView(R2.id.pop_filter_ll)
    LinearLayout mPopFilterLl;
    @BindView(R2.id.mking_tv)
    TextView mMkingTv;
    @BindView(R2.id.filter_tv)
    TextView mFilterTv;
    @BindView(R2.id.beauty_tv)
    TextView mBeautyTv;


    private int making = 0,mBeauty = 0,mFilter = 0;//美型，美颜,滤镜类型
    private boolean mIsMaking = true,mIsBeauty;
    private Dialog mDialog;
    private Context mContext;
    private String[] mFilterType = {"origin", "delta", "electric", "slowlived", "tokyo", "warm"};
    private List<View> mBeautyTvList = new ArrayList<>();
    private List<View> mFilterTvList = new ArrayList<>();
    private ResultListener mResultListener;
    public int mComeFrom;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity(), R.style.BottomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        mDialog.setContentView(R.layout.pop_filter_camera);
        mDialog.setCanceledOnTouchOutside(true); // 外部点击取消
        mDialog.getWindow().setWindowAnimations(R.style.expression_dialog_anim_style);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度持平
        window.setAttributes(lp);
        ButterKnife.bind(this, mDialog);
        mContext = mDialog.getContext();
        initData();
        initView();
        return mDialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.e("atest", "dismiss: " );
    }

    private void initData() {
        Bundle bundle = getArguments();

        mBeautyTvList.add(mNoneIv);
        mBeautyTvList.add(mOneTv);
        mBeautyTvList.add(mTwoTv);
        mBeautyTvList.add(mThreeTv);
        mBeautyTvList.add(mFourTv);
        mBeautyTvList.add(mFiveTv);

        mFilterTvList.add(mOrigin);
        mFilterTvList.add(mDelta);
        mFilterTvList.add(mElectric);
        mFilterTvList.add(mSlowlived);
        mFilterTvList.add(mTokyo);
        mFilterTvList.add(mWarm);

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mResultListener.result(making,mFilter,mBeauty,true);
    }

    private void initView() {
        switch (mComeFrom){
            case 0://从相机进入
                clickBeauty(making);
                break;
            case 1://本地视频编辑
                mPopFilterLl.setVisibility(View.GONE);
                clickFilter(mFilter);
                mPopBeautyLl.setVisibility(View.GONE);
                mPopFilterSv.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R2.id.origin, R2.id.delta, R2.id.electric, R2.id.slowlived, R2.id.tokyo, R2.id.warm, R2.id.none_iv, R2.id.one_tv, R2.id.two_tv, R2.id.three_tv, R2.id.four_tv, R2.id.five_tv,  R2.id.mking_tv, R2.id.filter_tv, R2.id.beauty_tv})

    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.origin) {clickFilter(0);
        } else if (i == R.id.delta) {clickFilter(1);
        } else if (i == R.id.electric) {clickFilter(2);
        } else if (i == R.id.slowlived) {clickFilter(3);
        } else if (i == R.id.tokyo) {clickFilter(4);
        } else if (i == R.id.warm) {clickFilter(5);
        } else if (i == R.id.none_iv) {clickBeauty(0);
        } else if (i == R.id.one_tv) {clickBeauty(1);
        } else if (i == R.id.two_tv) {clickBeauty(2);
        } else if (i == R.id.three_tv) {clickBeauty(3);
        } else if (i == R.id.four_tv) {clickBeauty(4);
        } else if (i == R.id.five_tv) {clickBeauty(5);
        } else if (i == R.id.mking_tv) { mMkingTv.setTextColor(Color.parseColor("#FFEB58"));
            mFilterTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            mBeautyTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            mIsBeauty = false;
            mIsMaking = true;
            clickBeauty(making);
            mPopBeautyLl.setVisibility(View.VISIBLE);
            mPopFilterSv.setVisibility(View.GONE);
        } else if (i == R.id.filter_tv) {mMkingTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            mFilterTv.setTextColor(Color.parseColor("#FFEB58"));
            mBeautyTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            clickFilter(mFilter);
            mPopBeautyLl.setVisibility(View.GONE);
            mPopFilterSv.setVisibility(View.VISIBLE);
        } else if (i == R.id.beauty_tv) {mBeautyTv.setTextColor(Color.parseColor("#FFEB58"));
            mFilterTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            mMkingTv.setTextColor(Color.parseColor("#7FFFFFFF"));
            mIsBeauty = true;
            mIsMaking = false;
            clickBeauty(mBeauty);
            mPopBeautyLl.setVisibility(View.VISIBLE);
            mPopFilterSv.setVisibility(View.GONE);
        }
    }


    public void clickBeauty(int position){
        if(mIsMaking){
            making = position;
        }
        if(mIsBeauty){
            mBeauty = position;
        }
        for (int i = 0; i < mBeautyTvList.size(); i++) {
            View view = mBeautyTvList.get(i);
            if(i == position){
                if(i == 0) {
                    ((ImageView) view).setImageResource(R.drawable.bigicon_no_light);
                }else{
                    ((TextView) view).setTextColor(Color.parseColor("#ffffff"));
                }
                view.setBackgroundResource(R.drawable.tv_circle_white40_bg);
            }else{
                if(i == 0){
                    ((ImageView) view).setImageResource(R.drawable.bigicon_no);
                }else{
                    ((TextView) view).setTextColor(Color.parseColor("#7fffffff"));
                }
                view.setBackgroundResource(R.drawable.tv_circle_white10_bg);
            }
        }
        mResultListener.result(making,mFilter,mBeauty,false);
    }

    public void clickFilter(int position){
        mFilter = position;
        for (int i = 0; i < mFilterTvList.size(); i++) {
            if(position == i){
                mFilterTvList.get(i).setAlpha(1);
                ((TextView) mFilterTvList.get(i)).setTextColor(Color.parseColor("#FFEB58"));
            }else{
                ((TextView) mFilterTvList.get(i)).setTextColor(getResources().getColor(R.color.colorWhite40));
                mFilterTvList.get(i).setAlpha(0.5f);
            }
        }
        if(mComeFrom == 1) {
            mDialog.dismiss();
        }else {
            mResultListener.result(making, mFilter, mBeauty, false);
        }
    }

    public void setResultListener(ResultListener resultListener) {
        mResultListener = resultListener;
    }
    public interface ResultListener{
        void result(int making, int mFilterType, int mBeauty, boolean isDismiss);
    }
}

package com.example.studyvedio.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyvedio.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView{
    protected BaseActivity mActivity;
    protected View mRootView;
    protected final String TAG = this.getClass().getSimpleName();

    protected P mPresenter;
    private Unbinder mUnbinder;
    /**
     * 是否显示
     */
    private boolean isFragmentVisible;
    /**
     * 是否是第一次显示
     */
    private boolean isFirstVisible;
    /**
     * 设置是否使用 view 的复用，默认开启
     */
    private boolean isReuseView = true;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setView() == 0 ? null :inflater.inflate(setView(), container, false);
        //绑定到butterknife
        mUnbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
//        mPresenter = initPresenter();
//        if (mPresenter != null){
//            mPresenter.attachView(this);
//        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        initView(view);
        initData(savedInstanceState);
        initListerner();
        if (mRootView == null) {
            mRootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }else {
                    onFragmentVisibleChange(true);
                }
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? mRootView : view, savedInstanceState);
    }

    protected abstract void initListerner();

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if(mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(int event) {
        if (event == Constants.QUIT_ACTION){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        isReuseView = true;

        this.mPresenter = null;
        this.mActivity = null;
        this.mRootView = null;
        this.mUnbinder = null;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView == null) {
            return;
        }

        boolean isVisible = getUserVisibleHint();
        if (isFirstVisible && isVisible) {
            onFragmentFirstVisible();
            isFirstVisible = false;
            return;
        }
        if (isVisible) {
            isFragmentVisible = true;
            onFragmentVisibleChange(true);
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }
    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    private void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            Load();
        }else {
            stopLoad();
        }
    }


    /**
     * 是否是第一次加载
     * @return
     */
    protected boolean isFirstVisible(){
        return isFirstVisible;
    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }
    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    /**
     * 接口
     */
    /**
     * 依赖注入的入口
     */
    protected abstract int setView();

    protected abstract P initPresenter();

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void Load();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
}

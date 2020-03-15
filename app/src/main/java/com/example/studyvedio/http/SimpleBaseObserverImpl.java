package com.example.studyvedio.http;

import android.content.Context;
import android.util.Log;

import com.example.studyvedio.bean.MyCallBack;
import com.example.studyvedio.bean.MyResponse;

public  class SimpleBaseObserverImpl<T extends MyResponse> extends BaseObserver<T> {

    private MyCallBack mCallback;
    private Context mContext;

    public SimpleBaseObserverImpl(Context ctx, MyCallBack callback){
        mCallback = callback;
        mContext = ctx;
    }

    @Override
    public void onNext(T t) {
        //processResponse(mContext, t,mCallback);
    }


    @Override
    public void onError(Throwable e) {
        mCallback.onError("网络异常");
    }



    /**
     * 处理正常情况,或者错误编码为 0 的失败情况,此时都会 return false.代表没有特殊错误.
     * 返回 true 代表有特殊错误.要另外处理
     *
     * @param context
     * @param response
     * @param callBack
     * @return
     */
//    private void processResponse(Context context, MyResponse response, MyCallBack callBack) {
//
//        /**
//         * 错误
//         */
//        if (response == null) {
//            callBack.onError(getStr("获取数据失败"));
//            return;
//        }
//
//        /**
//         * 成功处理
//         */
//        if (response.getCode()==MyResponse.CODE_SUCCESS) {
//            callBack.onSuccess(response.getData());
//            return;
//        }
//
//        /**
//         * 登录超时
//         */
//        if (MyResponse.CODE_LOGIN_TIMEOUT == response.getCode()) {
//            ((BaseActivity) context).showLoginTimeOutDialog();
//            return;
//        }
//
//        Log.e(AppConfig.LOG_TAG,"### other processResponse..."+response.toString());
//        callBack.onError(response.getMsg());
//
//    }
}

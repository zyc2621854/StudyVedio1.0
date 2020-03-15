package com.aserbao.androidcustomcamera.http;

import android.content.Context;
import android.util.Log;

import com.aserbao.androidcustomcamera.base.beans.MyCallBack;
import com.aserbao.androidcustomcamera.base.beans.MyResponse;
import com.aserbao.androidcustomcamera.base.beans.Userbean;
import com.aserbao.androidcustomcamera.http.itf.RetrofitService;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class API {
    private String TAG = API.class.getSimpleName();

    private static final API INSTANCE = new API();

    public static API getInstance() {
        return INSTANCE;
    }
    private RetrofitService mRetrofitService;

    private API() {
        this.mRetrofitService = RetrofitHelper.getInstance().getServer();
    }





    public void upLoadFileList(Context context,File file ,MyCallBack<String> callback){

        /** 上传文件 之前由于文件路径不正确 导致requestbody无法正常发送 目前已解决**/
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        mRetrofitService.upLoadFileList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==200)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
    }


}

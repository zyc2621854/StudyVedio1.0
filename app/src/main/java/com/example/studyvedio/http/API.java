package com.example.studyvedio.http;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import com.example.studyvedio.bean.MediaBean;
import com.example.studyvedio.bean.MediaListBean;
import com.example.studyvedio.bean.MyCallBack;
import com.example.studyvedio.bean.MyResponse;
import com.example.studyvedio.bean.UserListbean;
import com.example.studyvedio.bean.Userbean;
import com.example.studyvedio.http.itf.RetrofitService;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

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

    public void addUser(Context context, String user_account, String user_password, MyCallBack<String> callback){
        mRetrofitService.addUser(user_account,user_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });

    }

    public void deleteUser(Context context, int user_id, MyCallBack<String> callback){
        mRetrofitService.deleteUser(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
    }


    /**
      * @Method :
      * @Params :
      * @Return :
      * @Author : Administrator
      * @CreateDate : created at 2019/12/5 0005 下午 3:54
      * @Description :  参数可为空如何设置
     */
    public void updateUser(Context context, int user_id, String user_account, String user_password,MyCallBack<String> callback){
        mRetrofitService.updateUser(user_id,user_account,user_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
    }

    public void getUser(Context context, int user_id,MyCallBack<Userbean> callback){
        mRetrofitService.getUser(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getData());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
    }

    public void login(Context context,String username,String password,String code,String verifyToken,MyCallBack<String> callback){
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("code", code);
        map.put("verifyToken", verifyToken);
        String jsonBody = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonBody);
        mRetrofitService.login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
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
                        if (userbeanMyResponse.getCode()==0)callback.onSuccess(userbeanMyResponse.getMsg());
                        else callback.onError(userbeanMyResponse.getMsg());
                    }
                });
    }

    public void getUserInfo(Context context, int user_id,MyCallBack<Userbean> callback){
        mRetrofitService.getUserInfo(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<Userbean>>() {
                    @Override
                    public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                    //public void onNext(MyResponse<Userbean> userbeanMyResponse) {
                        Log.d(TAG, "onNext: start");
                        if (userbeanMyResponse.getCode()==200)callback.onSuccess(userbeanMyResponse.getData());
                        else callback.onError(userbeanMyResponse.getMsg());
                        Log.d(TAG, "onNext: finish"+userbeanMyResponse.getData().getFollowCount());
                    }
                });
    }

    /** 解决方案：接口已改 该接口用来替代获取视频列表  **/
    /** 可证明该获取列表写法没问题 **/
    public void getUserList(Context context, MyCallBack<UserListbean> callBack){
        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<>();
        List<HashMap<String, Object>> sonList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> sonMap = new HashMap<>();
        //map.put("username", 1);
        map.put("current", 1);
        map.put("keyword", "");
        sonMap.put("asc", true);
        sonMap.put("column", "");
        sonList.add(sonMap);
        map.put("orders", sonList);
        map.put("size", 3);
        String jsonBody = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonBody);
        mRetrofitService.getUserList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MyResponse<UserListbean>>() {
                    @Override
                    public void onNext(MyResponse<UserListbean> userListbeanMyResponse) {
                        Log.d(TAG, "onNext: " + userListbeanMyResponse.getCode());
                        Log.d(TAG, "onNext: start");
                        if (userListbeanMyResponse.getCode() == 200) {
                            Log.d(TAG, "onNext: success 测试成功");
                            callBack.onSuccess(userListbeanMyResponse.getData());
                        } else {
                            Log.d(TAG, "onNext: fail");
                        }
                        Log.d(TAG, "onNext: end");
                    }
                });
        Log.d(TAG, "getUserLidt: end");
    }



    public void getVideoList(Context context, int current, int size, MyCallBack<MediaListBean> callBack) {
        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<>();
        List<HashMap<String, Object>> sonList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> sonMap = new HashMap<>();
        //map.put("username", current);
        map.put("current", current);
        map.put("keyword", "");
        sonMap.put("asc", true);
        sonMap.put("column", "");
        sonList.add(sonMap);
        map.put("orders", sonList);
        map.put("size", size);
        String jsonBody = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonBody);
        mRetrofitService.getVideoList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /** 已成功访问 但目前因为未知原因 无法执行onNext方法 或是被跳过 导致前台无法获取数据 **/
                .subscribe(new BaseObserver<MyResponse<MediaListBean>>() {
                    @Override
                    public void onNext(MyResponse<MediaListBean> mediaListBeanMyResponse) {
                        Log.d(TAG, "onNext: " + mediaListBeanMyResponse.getCode());
                        Log.d(TAG, "onNext: start");
                        if (mediaListBeanMyResponse.getCode() == 200) {
                            callBack.onSuccess(mediaListBeanMyResponse.getData());
                            Log.d(TAG, "onNext: success");
                        } else {
                            callBack.onError(mediaListBeanMyResponse.getMsg());
                            Log.d(TAG, "onNext: fail");
                        }
                        Log.d(TAG, "onNext: end");
                    }
                });
        Log.d(TAG, "getVideoList: end");

    }

}

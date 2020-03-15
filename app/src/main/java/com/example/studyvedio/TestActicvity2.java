package com.example.studyvedio;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyvedio.base.BaseActivity;
import com.example.studyvedio.bean.MovieEntity;
import com.example.studyvedio.bean.MyCallBack;
import com.example.studyvedio.bean.Userbean;
import com.example.studyvedio.http.API;
import com.example.studyvedio.http.itf.GetRequest_Interface;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//网络框架实验
public class TestActicvity2 extends BaseActivity {

    String assetPath;

    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    @BindView(R.id.result_TV)
    TextView resultTV;
    @OnClick(R.id.click_me_BN)
    public void onClick(){
//        copyAssetAndWrite("vedio.flv");
//        File dataFile=new File(getCacheDir(),"vedio.flv");
//        Log.d(TAG,"filePath:" + dataFile.getAbsolutePath());
//        upLoadFileList(dataFile);
        resultTV.setText(MyJni.getString());
    }



    /**
     * 将asset文件写入缓存
     */private boolean copyAssetAndWrite(String fileName){    try {
        File cacheDir=getCacheDir();        if (!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        File outFile =new File(cacheDir,fileName);        if (!outFile.exists()){            boolean res=outFile.createNewFile();            if (!res){                return false;
        }
        }else {            if (outFile.length()>10){//表示已经写入一次
            return true;
        }
        }
        InputStream is=getAssets().open(fileName);
        FileOutputStream fos = new FileOutputStream(outFile);        byte[] buffer = new byte[1024];        int byteCount;        while ((byteCount = is.read(buffer)) != -1) {
            fos.write(buffer, 0, byteCount);
        }
        fos.flush();
        is.close();
        fos.close();        return true;
    } catch (IOException e) {
        e.printStackTrace();
    }    return false;
    }


    private void upLoadFileList(File file) {
        Log.d(TAG, "upLoadFileList: start");
        API.getInstance().upLoadFileList(mContext, file,new MyCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                resultTV.setText(data);
            }

            @Override
            public void onError(String msg) {
                resultTV.setText(msg);
            }
        });

    }


    private void getMovie3() {
        API.getInstance().addUser(mContext,"abc","123", new MyCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                resultTV.setText(data);
            }

            @Override
            public void onError(String msg) {
                resultTV.setText(msg);
            }
        });
    }

    private void getMovie4(){
        API.getInstance().login(mContext,"admin","123456","string","string", new MyCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                resultTV.setText(data);
            }

            @Override
            public void onError(String msg) {
                resultTV.setText(msg);
            }
        });
    }

//    private void getMovie2() {
//        String baseUrl =  "https://api.douban.com/v2/moive/";
//
//        Retrofit retrofit = new Retrofit().Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        GetRequest_Interface movieService = retrofit.create(GetRequest_Interface.class);
//
//        movieService.getTopMoive(0,10)
//                .subscribeOn(Scheduler.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Toast.makeText(TestActicvity2.this,"get",Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        resultTV.setText(movieEntity.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        resultTV.setText(t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                })
//    }
//
//    private void getMovie1() {
//        String baseUrl =  "https://api.douban.com/v2/moive/";
//
//        Retrofit retrofit = new Retrofit().Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        GetRequest_Interface movieService = retrofit.create(GetRequest_Interface.class);
//        Call<MovieEntity> call = movieService.getTopMoive(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test_acticvity2;
    }
}

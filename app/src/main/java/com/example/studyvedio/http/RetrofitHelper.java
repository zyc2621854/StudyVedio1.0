package com.example.studyvedio.http;

import android.util.Log;

import com.example.studyvedio.BuildConfig;
import com.example.studyvedio.http.itf.RetrofitService;
import com.example.studyvedio.utils.Constants;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static long CONNECT_TIMEOUT = 10;
    public static long READ_TIMEOUT = 60;
    public static long WRITE_TIMEOUT = 60;
    private String TAG ="RetrofitHelper";


    private OkHttpClient client;
    private OkHttpClient.Builder builder;
    //private final String baseUrl="https://dc4da89c-a6ca-4c3d-b4ef-0f7e6f338947.mock.pstmn.io";
    private final String baseUrl= Constants.BASE_URL;

    private GsonConverterFactory factory = GsonConverterFactory.create();
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit =null;

    public synchronized static RetrofitHelper getInstance(){
        if(instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }

    private RetrofitHelper() {init();}

    private void init() { resetApp();
    }

    private void resetApp() {
        builder =new OkHttpClient.Builder();
                builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request(); //Current Request

                Response response = chain.proceed(originalRequest); //Get response of the request

                /** DEBUG STUFF */
                if (BuildConfig.DEBUG) {
                    //I am logging the response body in debug mode. When I do this I consume the response (OKHttp only lets you do this once) so i have re-build a new one using the cached body
                    String bodyString = response.body().string();
                    Log.d(TAG, "intercept: Sending request "+originalRequest.url()+" with headers "+originalRequest.headers());
                    Log.d(TAG, "intercept: Got response HTTP "+response.code()+" "+response.message()+"\n\n "+"with body"+bodyString+" \n\n "+"with headers "+response.headers());
                    System.out.println(String.format("Sending request %s with headers %s ", originalRequest.url(), originalRequest.headers()));
                    System.out.println(String.format("Got response HTTP %s %s \n\n with body %s \n\n with headers %s ", response.code(), response.message(), bodyString, response.headers()));
                    response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
                }

                return response;
            }
        });
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }
}

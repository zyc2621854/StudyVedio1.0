package com.example.studyvedio.http.itf;

import com.example.studyvedio.bean.MovieEntity;

import java.util.Observable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetRequest_Interface {
    @Headers({"Content-Type:application/json","Accept:application/json","Cache-Control:public,max-age=300"})//需要添加头
    @POST("user/add")
    Call<ResponseBody> addUser(@Body RequestBody Userbean);

//    @GET("")
//    Observable<ResponseBody> getCall();


//    @GET("top250")
//    Call<MovieEntity> getTopMoive(@Query("start") int start,@Query("count") int count);
//
//    @GET("top250")
//    Observable<MovieEntity> getTopMoive(@Query("start") int start, @Query("count") int count);

}

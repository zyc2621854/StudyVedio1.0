package com.aserbao.androidcustomcamera.http.itf;

import com.aserbao.androidcustomcamera.base.beans.MyResponse;
import com.aserbao.androidcustomcamera.base.beans.Userbean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("user/add")
    Observable<MyResponse<Userbean>> addUser(@Field("user_account") String user_account,
                                             @Field("code") String user_password);

    @FormUrlEncoded
    @POST("user/delete")
    Observable<MyResponse<Userbean>> deleteUser(@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("user/update")
    Observable<MyResponse<Userbean>> updateUser(@Field("user_id") int user_id,
                                                @Field("user_account") String user_account,
                                                @Field("user_passsword") String user_password);

    @GET("user/find")
    Observable<MyResponse<Userbean>> getUser(@Query("user_id") int user_id);

    
    /** json格式传递数据 **/
    @Headers("Content-Type:application/json")
    @POST("login")
    Observable<MyResponse<Userbean>> login(@Body RequestBody requestBody);


    /** 上传文件 **/
    @Multipart
    @POST("upload/")
    Observable<MyResponse<Userbean>> upLoadFileList(@Part MultipartBody.Part img);

    @GET("user/info/{user_id}")
    Observable<MyResponse<Userbean>> getUserInfo(@Path("user_id") int user_id);
}

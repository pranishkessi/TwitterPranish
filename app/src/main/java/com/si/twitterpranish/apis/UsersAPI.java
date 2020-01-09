package com.si.twitterpranish.apis;

import com.si.twitterpranish.model.Check;
import com.si.twitterpranish.model.ImageModel;
import com.si.twitterpranish.model.SignUp;
import com.si.twitterpranish.model.Tweets;
import com.si.twitterpranish.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UsersAPI {
    @FormUrlEncoded
    @POST("user/login")
    Call<SignUp> checkUser(@Field("email") String email, @Field("password") String password);


    @POST("user/register")
    Call<SignUp> register(@Body User cud);

    @Multipart
    @POST("upload")
    Call<ImageModel> uploadImage(@Part MultipartBody.Part imageFile);

    @POST("user/check")
    Call<Check> check(@Body User email);

//    @GET("tweet")
//    Call<List<Tweets>> GetTweet();

    @GET("tweet")
    Call<List<Tweets>> GetTweet(@Header("Authorization") String token);

    @GET("user/me")
    Call<User> getUser(@Header("Authorization") String token);
}
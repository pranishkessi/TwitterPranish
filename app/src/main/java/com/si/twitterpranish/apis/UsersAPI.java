package com.si.twitterpranish.apis;

import com.samyam.twitter.model.Check;
import com.samyam.twitter.model.ImageModel;
import com.samyam.twitter.model.SignUp;
import com.samyam.twitter.model.Tweets;
import com.samyam.twitter.model.User;

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
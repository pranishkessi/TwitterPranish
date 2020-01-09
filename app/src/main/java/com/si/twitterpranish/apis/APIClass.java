package com.si.twitterpranish.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClass {
    public  UsersAPI calls(){
        String base_url = "http://10.0.2.2:3000/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI usersAPI=retrofit.create(UsersAPI.class);
        return usersAPI;
    }
}

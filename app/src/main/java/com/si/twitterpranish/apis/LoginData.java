package com.si.twitterpranish.apis;

import com.si.twitterpranish.model.SignUp;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginData {
    boolean isSuccess = false;
    public static String Token;

    public boolean checkUser(String email, String password) {

        APIClass apiClass = new APIClass();
        Call<SignUp> usersCall = apiClass.calls().checkUser(email, password);

        try {
            Response<SignUp> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Successful")) {
                SignUp signUpResponse=loginResponse.body();
                Token=signUpResponse.getToken();

                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

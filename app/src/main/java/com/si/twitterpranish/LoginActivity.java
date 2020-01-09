package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samyam.twitter.apis.LoginData;
import com.samyam.twitter.model.User;
import com.samyam.twitter.strictMode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
//    ImageButton ib_show_P;
    TextView forget;
    Button login;
    public static String Token="Bearer ";
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        forget = findViewById(R.id.forgot_password);
        login= findViewById(R.id.log_login);
        forget = findViewById(R.id.forgot_password);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this,MainActivity.class );
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty( email.getText().toString() )) {
                    if (!TextUtils.isEmpty( password.getText().toString() )) {
                        User user = new User(email.getText().toString(),password.getText().toString());
                        loginCheck(user);
                    } else {
                        password.setError( "Please Provide Password" );
                    }
                } else {
                    email.setError( "Please Provide Email" );
                }
            }
        });
    }
    public void loginCheck(User user){
        LoginData loginData = new LoginData();
        StrictModeClass.StrictMode();
        if (loginData.checkUser(user.getEmail(), user.getPassword())) {
            Intent intent = new Intent( LoginActivity.this,HomeActivity.class );
            Token= loginData.Token;
            startActivity( intent );
        } else {
            Toast.makeText(this, "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}

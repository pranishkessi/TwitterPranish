package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        login.append(Html.fromHtml("<font color='#3897F0'> Log in</font>"));
        register = findViewById(R.id.btnRegister);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return;

            case R.id.btnRegister:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                return;
        }
    }
}

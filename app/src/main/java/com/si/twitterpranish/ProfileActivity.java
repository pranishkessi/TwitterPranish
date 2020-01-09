package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    Button next;
    EditText password;
    String email="", username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        password = findViewById(R.id.password);
        next = findViewById(R.id.pass_next);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString( "email" );
            username = bundle.getString( "username" );
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().isEmpty()){
                    Toast.makeText( ProfileActivity.this, "check password", Toast.LENGTH_SHORT ).show();
                    return;
                }
                else{
                    Intent intent = new Intent( ProfileActivity.this, ImageActivity.class);
                    intent.putExtra( "email",email );
                    intent.putExtra( "username",username );
                    intent.putExtra( "password",password.getText().toString() );
                    startActivity( intent );
                    Toast.makeText(ProfileActivity.this, ""+password.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

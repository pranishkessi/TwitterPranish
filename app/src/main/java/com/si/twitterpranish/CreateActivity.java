package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    Button next;
    String email="";
    String username="";
    EditText f_email, f_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        next = findViewById(R.id.final_signup);
        f_email = findViewById(R.id.email_final);
        f_name = findViewById(R.id.username_final);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email= bundle.getString( "email" );
            username= bundle.getString( "username" );

            f_email.setText( bundle.getString( "email" ) );
            f_name.setText( bundle.getString( "username" ) );
        }
        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CreateActivity.this, VerifyActivity.class );
                intent.putExtra( "email",email );
                intent.putExtra( "username",username );
                startActivity( intent );
            }
        } );
        f_name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnto();
            }
        } );
        f_email.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnto();
            }
        } );

    }
    void returnto(){
        Intent intent=new Intent( CreateActivity.this,RegisterActivity.class );
        intent.putExtra( "email",email );
        intent.putExtra( "username" ,username);
        startActivity( intent );
    }
}

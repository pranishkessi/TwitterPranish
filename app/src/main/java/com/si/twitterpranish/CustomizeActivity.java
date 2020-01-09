package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomizeActivity extends AppCompatActivity {
    String email,username;
    Button btnnexts;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        btnnexts=findViewById(R.id.btn_next);

        btnback=findViewById(R.id.customize_back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(CustomizeActivity.this, RegisterActivity.class);
                startActivity(back);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email= bundle.getString( "email" );
            username= bundle.getString( "username" );
        }

        btnnexts.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CustomizeActivity.this, CreateActivity.class );
                intent.putExtra( "email",email );
                intent.putExtra( "username",username );
                startActivity( intent );
            }
        } );
    }
}

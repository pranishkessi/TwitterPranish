package com.si.twitterpranish;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerifyActivity extends AppCompatActivity {

    Button next;
    TextView code;
    String email="", username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        code = findViewById(R.id.code);
        next = findViewById(R.id.verify_next);

        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep( 1000 );
                    code.setText( "4,0,1,9,2,4" );
                    Bundle bundle = getIntent().getExtras();
                    if (bundle != null) {
                        email = bundle.getString( "email" );
                        username = bundle.getString( "username" );
                        Log.d( "email", email );
                        Log.d( "username", username );

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().toString().isEmpty()) {
                    Toast.makeText( VerifyActivity.this, "Wait", Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent( VerifyActivity.this, ProfileActivity.class );
                intent.putExtra( "email", email );
                intent.putExtra( "username", username );
                startActivity( intent );
            }
        } );


    }
}

package com.si.twitterpranish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samyam.twitter.apis.APIClass;
import com.samyam.twitter.model.Check;
import com.samyam.twitter.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText email, username;
    ImageView back;
    Button next;
    int countUsername = 0;
    String Emailup = "";
    String Usernameup = "";
    boolean chekU = false;
    boolean chekE = false;
    TextView un_error, email_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email_first);
        username = findViewById(R.id.username_first);
        back = findViewById(R.id.r_back);

        email_error = findViewById(R.id.email_error);
        un_error = findViewById(R.id.username_count);

        next = findViewById(R.id.check_next);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Emailup = bundle.getString("email");
            Usernameup = bundle.getString("username");
            email.setText(bundle.getString("email"));
            username.setText(bundle.getString("username"));
        }
        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent back = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(back);
                                    }
                                }
        );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chekE == true && chekU == true) {
                    User user = new User(Emailup);
                    Checkuser(user);

                } else {
                    Toast.makeText(RegisterActivity.this, "fill require field with valid information", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countL = username.length();
                if (count > 0) {
                    if (countUsername >= 0) {
                        countUsername = 50 - countL;
                        un_error.setTextColor(Color.BLACK);
                        un_error.setText("" + countUsername);

                        username.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked, 0);

                        chekU = true;
                        Usernameup = username.getText().toString();
                        return;
                    } else if (countUsername < 0) {
                        countUsername = 50 - countL;
                        un_error.setTextColor(Color.RED);
                        un_error.setText("Must be 50 characters or fewer.");
                        un_error.append("      " + countUsername);
                        username.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                        chekU = false;
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email_error.setText("");
                if ((email.getText().toString().toLowerCase().contains("@")) && (email.getText().toString().toLowerCase().contains(".com"))) {
                    email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checked, 0);
                    Emailup = email.getText().toString();
                    chekE = true;
                } else {
                    email_error.setText("Invalid Email Address");
                    email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                    chekE = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Checkuser(User user) {
        APIClass apiClass = new APIClass();
        Call<Check> checkCall = apiClass.calls().check(user);
        checkCall.enqueue(new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "error" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("error", "error" + response.code());
                    return;
                }
                Check check = response.body();
                Toast.makeText( RegisterActivity.this, "user " + check.getCheck(), Toast.LENGTH_SHORT ).show();
                if (check.getCheck().equals("OK")) {
                    Intent next = new Intent(RegisterActivity.this, CustomizeActivity.class);
                    next.putExtra("email", Emailup);
                    next.putExtra("username", Usernameup);
                    startActivity(next);
                    return;
                } else {
                    //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                    email_error.setText("Email already exists.");
                    email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                    email.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", "error   " + t.getLocalizedMessage());

            }
        });
    }
}
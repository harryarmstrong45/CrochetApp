package com.example.android.roomwordssample.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.roomwordssample.R;

public class UserLoginActivity extends AppCompatActivity {

    private EditText Username, Password;
    private Button login_button;
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Username = findViewById(R.id.editText_Username);
        Password = findViewById(R.id.editText_Password);
        login_button = findViewById(R.id.button_Login);


    }
}
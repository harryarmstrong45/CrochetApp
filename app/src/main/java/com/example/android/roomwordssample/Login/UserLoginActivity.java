package com.example.android.roomwordssample.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        login_button = findViewById(R.id.login);

        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mLoginViewModel.getLogInFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                login_button.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    Username.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    Password.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                mLoginViewModel.loginDataChanged(Username.getText().toString(),
                        Password.getText().toString());
            }
        };
        Username.addTextChangedListener(afterTextChangedListener);
        Password.addTextChangedListener(afterTextChangedListener);

    }
}
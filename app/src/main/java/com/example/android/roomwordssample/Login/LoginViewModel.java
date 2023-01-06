package com.example.android.roomwordssample.Login;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.roomwordssample.CrochetPattern;
import com.example.android.roomwordssample.CrochetRepository;
import com.example.android.roomwordssample.R;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    private final LoginRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private final LiveData<List<Login>> mAllItems;

    LiveData<LoginFormState> getLogInFormState() {
        return loginFormState;
    }

    public LoginViewModel(Application application) {
        super(application);
        mRepository = new LoginRepository(application);
        mAllItems = mRepository.getAllItems();
    }


    LiveData<List<Login>> getAllItems() {
        return mAllItems;
    }

    void insert(Login item) {
        mRepository.insert(item);
    }

    void update(Login item) {
        mRepository.update(item);
    }


    void deleteAll() {
        mRepository.deleteAll();
    }

    public void loginDataChanged(String username, String password) {
        if (!ValidName(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!ValidPassword(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }


    private boolean ValidName(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    private boolean ValidPassword(String password) {
        return password != null && password.trim().length() > 8;
    }
}



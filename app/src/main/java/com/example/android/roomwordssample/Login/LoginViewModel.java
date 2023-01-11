package com.example.android.roomwordssample.Login;

import android.app.Application;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.roomwordssample.R;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LogInResult> loginResult = new MutableLiveData<>();

    private final LoginRepository mRepository;

    private final LiveData<List<Login>> mAllItems;

    LiveData<LogInResult> getLoginResult() {
        return loginResult;
    }

    LiveData<LoginFormState> getLogInFormState() {
        return loginFormState;
    }


    public void login(String username, String password) {
         //can be launched in a separate asynchronous job
//        Result<LoggedInUser> result = mRepository.login(username, password);
//
//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }

        Login login = new Login(username,password);
        insert(login);
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



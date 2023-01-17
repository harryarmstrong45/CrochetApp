package com.example.android.roomwordssample.Login;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.roomwordssample.CrochetPattern;
import com.example.android.roomwordssample.Login.LoginDAO;

import java.util.List;

public class LoginRepository {

    private LoginDAO loginDAO;
    private LiveData<List<Login>> mAllItems;

    // Note that in order to unit test the ItemRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    LoginRepository(Application application) {
        LoginRoomDatabase db = LoginRoomDatabase.getDatabase(application);
        loginDAO = db.loginDAO();
        mAllItems = loginDAO.getAlphabetizedItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Login>> getAllItems() {
        return mAllItems;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    // Adds a new user
    void insert(Login user) {
        LoginRoomDatabase.databaseWriteExecutor.execute(() -> {
            loginDAO.insert(user);
        });
    }

    void update(Login user) {
        LoginRoomDatabase.databaseWriteExecutor.execute(() -> {
            loginDAO.update(user);
        });
    }

    void deleteAll() {
        LoginRoomDatabase.databaseWriteExecutor.execute(() -> {
            loginDAO.deleteAll();
        });
    }

    int hasItem(String username, String password){
        return loginDAO.hasItem(username,password);
    }
}

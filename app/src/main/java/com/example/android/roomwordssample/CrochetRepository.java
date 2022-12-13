package com.example.android.roomwordssample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CrochetRepository {
    private CrochetDao mCrochetDao;
    private LiveData<List<CrochetPattern>> mAllItems;

    // Note that in order to unit test the ItemRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    CrochetRepository(Application application) {
        CrochetRoomDatabase db = CrochetRoomDatabase.getDatabase(application);
        mCrochetDao = db.itemDao();
        mAllItems = mCrochetDao.getAlphabetizedItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<CrochetPattern>> getAllItems() {
        return mAllItems;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(CrochetPattern crochetpattern) {
        CrochetRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCrochetDao.insert(crochetpattern);
        });
    }

    void deleteAll() {
        CrochetRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCrochetDao.deleteAll();
        });
    }
}

package com.example.android.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all Items.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private final CrochetRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private final LiveData<List<CrochetPattern>> mAllItems;

    public MainActivityViewModel(Application application) {
        super(application);
        mRepository = new CrochetRepository(application);
        mAllItems = mRepository.getAllItems();
    }

    LiveData<List<CrochetPattern>> getAllItems() {
        return mAllItems;
    }

    void insert(CrochetPattern item) {mRepository.insert(item);}
    void update(CrochetPattern item) {mRepository.update(item);}


    void deleteAll() {
        mRepository.deleteAll();
    }
}

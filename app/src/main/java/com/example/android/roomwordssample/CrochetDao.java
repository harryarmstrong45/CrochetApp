package com.example.android.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrochetDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * FROM crochetTable_table ORDER BY name ASC")
    LiveData<List<CrochetPattern>> getAlphabetizedItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CrochetPattern item);

    @Query("DELETE FROM crochetTable_table")
    void deleteAll();
}

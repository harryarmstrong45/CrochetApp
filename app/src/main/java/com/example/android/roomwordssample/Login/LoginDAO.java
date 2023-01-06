package com.example.android.roomwordssample.Login;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.roomwordssample.CrochetPattern;

import java.util.List;

@Dao
public interface LoginDAO {
    @Query("SELECT * FROM login_table ORDER BY Username ASC")
    LiveData<List<Login>> getAlphabetizedItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Login item);

    @Query("DELETE FROM login_table")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Login login);
}

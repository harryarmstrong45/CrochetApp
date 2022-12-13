package com.example.android.roomwordssample;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "crochetTable_table")


public class CrochetPattern implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    public CrochetPattern(@NonNull String mName, @NonNull String mDescription) {
        this.mName = mName;
        this.mDescription = mDescription;
    }

    @NonNull
    public String getName() {
        return mName;
    }


    @NonNull
    public String getDescription() {
        return mDescription;
    }
}

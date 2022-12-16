package com.example.android.roomwordssample;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "crochetTable_table")


public class CrochetPattern implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int ID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    public CrochetPattern( @NonNull String mName, @NonNull String mDescription) {
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

    @NonNull
    public int getID() {return ID;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setDescription(@NonNull String mDescription) {
        this.mDescription = mDescription;
    }
}

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


    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "image")
    private byte[] mImage;

    public CrochetPattern( @NonNull String mName, @NonNull String mDescription, @NonNull byte[] mImage) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mImage = mImage;
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

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] mImage) {
        this.mImage = mImage;
    }

}

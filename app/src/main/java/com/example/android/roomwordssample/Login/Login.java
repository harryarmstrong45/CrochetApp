package com.example.android.roomwordssample.Login;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "login_table")

public class Login implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int ID;

    @NonNull
    @ColumnInfo(name = "Username")
    private String mUsername;

    @NonNull
    @ColumnInfo(name = "Password")
    private String mPassword;

    public Login(@NonNull String mUsername, @NonNull String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getUsername() {
        return mUsername;
    }

    @NonNull
    public String getPassword() {
        return mPassword;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(@NonNull String mUsername) {
        this.mUsername = mUsername;
    }

    public void setPassword(@NonNull String mPassword) {
        this.mPassword = mPassword;
    }
}

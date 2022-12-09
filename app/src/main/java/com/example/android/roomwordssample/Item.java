package com.example.android.roomwordssample;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")


public class Item {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "location")
    private String mLocation;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    public Item(@NonNull String mName, @NonNull String mLocation, @NonNull String mDescription) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mDescription = mDescription;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getLocation() {
        return mLocation;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }
}

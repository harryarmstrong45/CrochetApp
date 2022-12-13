package com.example.android.roomwordssample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CrochetPattern.class}, version = 1, exportSchema = false)
abstract class CrochetRoomDatabase extends RoomDatabase {

    abstract CrochetDao itemDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CrochetRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CrochetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CrochetRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CrochetRoomDatabase.class,
                                    "crochetTable_table")
                            .addCallback(mCrochetDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback mCrochetDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CrochetDao dao = INSTANCE.itemDao();
                dao.deleteAll();

                CrochetPattern item = new CrochetPattern("Name", "Description");
                dao.insert(item);
                item = new CrochetPattern("Name 2", "Description 2");
                dao.insert(item);
            });
        }
    };
}

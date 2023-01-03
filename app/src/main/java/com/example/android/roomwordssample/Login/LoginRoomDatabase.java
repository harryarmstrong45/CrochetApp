package com.example.android.roomwordssample.Login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Login.class}, version = 1, exportSchema = false)
abstract public class LoginRoomDatabase extends RoomDatabase {
    abstract LoginDAO loginDAO();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile LoginRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LoginRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LoginRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    LoginRoomDatabase.class,
                                    "login_table")
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
                LoginDAO dao = INSTANCE.loginDAO();
                dao.deleteAll();

                Login item = new Login("Username", "Password");
                dao.insert(item);

                item = new Login("Name 2", "Description 2");
                dao.insert(item);
            });
        }
    };

}

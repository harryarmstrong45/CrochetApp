package com.example.android.roomwordssample;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.android.roomwordssample.Login.Login;
import com.example.android.roomwordssample.Login.LoginDAO;
import com.example.android.roomwordssample.Login.LoginRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class LoginDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LoginDAO loginDAO;
    private LoginRoomDatabase loginDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        loginDatabase = Room.inMemoryDatabaseBuilder(context, LoginRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        loginDAO = loginDatabase.loginDAO();
    }

    @After
    public void closeDb() {
        loginDatabase.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        Login login = new Login("Username", "Password");
        loginDAO.insert(login);
        List<Login> allWords = LiveDataTestUtil.getValue(loginDAO.getAlphabetizedItems());
        assertEquals(allWords.get(0).getUsername(), login.getUsername());
    }

    @Test
    public void getAllWords() throws Exception {
        Login login1 = new Login("Username", "Password");
        loginDAO.insert(login1);
        Login login2 = new Login("Username2", "Password2");
        loginDAO.insert(login2);
        List<Login> allWords = LiveDataTestUtil.getValue(loginDAO.getAlphabetizedItems());
        assertEquals(allWords.get(0).getUsername(), login1.getUsername());
        assertEquals(allWords.get(1).getUsername(), login2.getUsername());
    }

    @Test
    public void deleteAll() throws Exception {
        Login login = new Login("Username", "Password");
        loginDAO.insert(login);
        Login login2 = new Login("Username2", "Password2");
        loginDAO.insert(login2);
        loginDAO.deleteAll();
        List<Login> allWords = LiveDataTestUtil.getValue(loginDAO.getAlphabetizedItems());
        assertTrue(allWords.isEmpty());
    }
}

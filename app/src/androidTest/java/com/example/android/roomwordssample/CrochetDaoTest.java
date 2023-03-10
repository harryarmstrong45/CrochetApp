package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;


@RunWith(AndroidJUnit4.class)
public class CrochetDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CrochetDao mWordDao;
    private CrochetRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, CrochetRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mWordDao = mDb.itemDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        CrochetPattern word = new CrochetPattern("Pattern", "1", null);
        mWordDao.insert(word);
        List<CrochetPattern> allWords = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedItems());
        assertEquals(allWords.get(0).getName(), word.getName());
    }

    @Test
    public void getAllWords() throws Exception {
        CrochetPattern word = new CrochetPattern("aaa", "aaa", null);
        mWordDao.insert(word);
        CrochetPattern word2 = new CrochetPattern("bbb", "bbb", null);
        mWordDao.insert(word2);
        List<CrochetPattern> allWords = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedItems());
        assertEquals(allWords.get(0).getName(), word.getName());
        assertEquals(allWords.get(1).getName(), word2.getName());
    }

    @Test
    public void deleteAll() throws Exception {
        CrochetPattern word = new CrochetPattern("ccc", "ccc", null);
        mWordDao.insert(word);
        CrochetPattern word2 = new CrochetPattern("ddd", "ddd", null);
        mWordDao.insert(word2);
        mWordDao.deleteAll();
        List<CrochetPattern> allWords = LiveDataTestUtil.getValue(mWordDao.getAlphabetizedItems());
        assertTrue(allWords.isEmpty());
    }
}

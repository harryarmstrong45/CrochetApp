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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_Item_ACTIVITY_REQUEST_CODE = 1;

    private ItemViewModel mItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mItemViewModel.getAllItems().observe(this, Items -> {
            // Update the cached copy of the Items in the adapter.
            adapter.submitList(Items);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        //mItemViewModel.deleteAll(); Delete all data

        Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
        startActivityForResult(intent, NEW_Item_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_Item_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(
                    data.getStringExtra(NewItemActivity.EXTRA_REPLY_NAME),
                    data.getStringExtra(NewItemActivity.EXTRA_REPLY_DESC),
                    data.getStringExtra(NewItemActivity.EXTRA_REPLY_LOC)
            );
            mItemViewModel.insert(item);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}

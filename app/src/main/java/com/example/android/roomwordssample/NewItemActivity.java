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
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for entering a word.
 */

public class NewItemActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "com.example.android.itemlistsql.REPLY.NAME";
    public static final String EXTRA_REPLY_DESC = "com.example.android.itemlistsql.REPLY.DESCRIPTION";
    public static final String EXTRA_REPLY_LOC = "com.example.android.itemlistsql.REPLY.LOCATION";

    private EditText mEditItemName, mEditItemDesc, mEditItemLoc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        mEditItemName = findViewById(R.id.edit_name);
        mEditItemDesc = findViewById(R.id.edit_Description);
        mEditItemLoc = findViewById(R.id.edit_Location);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditItemName.getText())
                    || TextUtils.isEmpty(mEditItemDesc.getText())
                    || TextUtils.isEmpty(mEditItemLoc.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            }

            else {
                String Name = mEditItemName.getText().toString();
                String Location = mEditItemLoc.getText().toString();
                String Description = mEditItemDesc.getText().toString();

                replyIntent.putExtra(EXTRA_REPLY_NAME, Name);
                replyIntent.putExtra(EXTRA_REPLY_DESC, Description);
                replyIntent.putExtra(EXTRA_REPLY_LOC, Location);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}


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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Activity for entering a word.
 */

public class NewCrochetActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_CROCHET = "com.example.android.itemListSQL.REPLY.ITEM";

    private EditText mEditItemName, mEditItemDesc;

    private ImageView image_View;
    private static final int Result_Load_Img = 100;
    private byte[] selected_Image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item);
        mEditItemName = findViewById(R.id.edit_name);
        mEditItemDesc = findViewById(R.id.edit_Description);
        image_View = findViewById(R.id.imageView);


        final Button button = findViewById(R.id.button_save);
        final Button addimg = findViewById(R.id.button_uploadImage);

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (checkIfTextFieldsEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            }

            else {
                String Name = getStringOfTextView(mEditItemName);

                String Description = getStringOfTextView(mEditItemDesc);

                CrochetPattern item = new CrochetPattern(Name,Description,selected_Image);

                replyIntent.putExtra(EXTRA_REPLY_CROCHET, item);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        addimg.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, Result_Load_Img);
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap BitMapImage = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BitMapImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                selected_Image = outputStream.toByteArray();
                image_View.setImageBitmap(BitMapImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public static byte[] bitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream output= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);
        return output.toByteArray();
    }

    private String getStringOfTextView(EditText editText) {
        return editText.getText().toString();
    }

    private boolean checkIfTextFieldsEmpty() {
        return (
            TextUtils.isEmpty(mEditItemName.getText())
            || TextUtils.isEmpty(mEditItemDesc.getText())
        );
    }
}


package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class EditCrochetActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_CROCHET = "com.example.android.itemListSQL.REPLY.ITEM";
    public static final String ITEM_TO_UPDATE = "Update Item";
    private CrochetPattern oldPattern;

    private EditText mEditItemName, mEditItemDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_crochet);
        setTitle("Edit Crochet Pattern");

        Intent intent = getIntent();
        oldPattern = (CrochetPattern) intent.getSerializableExtra(ITEM_TO_UPDATE);

        fillOutFields();

        mEditItemName = findViewById(R.id.edit_name);
        mEditItemDesc = findViewById(R.id.edit_Description);

        final Button button = findViewById(R.id.button_save);

        Intent replyIntent = new Intent();
        setResult(RESULT_CANCELED, replyIntent);

        button.setOnClickListener(view -> {

            if (checkIfTextFieldsEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            }

            else {
                String Name = getStringOfTextView(mEditItemName);

                String Description = getStringOfTextView(mEditItemDesc);

                oldPattern.setName(Name);
                oldPattern.setDescription(Description);

                replyIntent.putExtra(EXTRA_REPLY_CROCHET, oldPattern);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
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

    private void fillOutFields() {
        mEditItemName = findViewById(R.id.edit_name);
        mEditItemDesc = findViewById(R.id.edit_Description);

        mEditItemName.setText(oldPattern.getName());
        mEditItemDesc.setText(oldPattern.getDescription());
    }
}
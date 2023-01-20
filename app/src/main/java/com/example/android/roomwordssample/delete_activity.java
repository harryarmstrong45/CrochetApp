package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class delete_activity extends AppCompatActivity {

    private CrochetPattern Pattern;
    public static final String Extra_Reply_Item = "REPLY.ITEM";
    public static final String Item_To_Delete = "Delete_Item";
    boolean clicked = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        setTitle("Delete");

        Intent intent = getIntent();
        Pattern = (CrochetPattern) intent.getSerializableExtra(Item_To_Delete);

        final Button confirm = findViewById(R.id.button_confirm);
        final Button cancel = findViewById(R.id.button_cancel);

        Intent replyIntent = new Intent();
        setResult(RESULT_CANCELED, replyIntent);

        confirm.setOnClickListener(view -> {
            clicked = true;

            replyIntent.putExtra(Extra_Reply_Item, Pattern);
            setResult(RESULT_OK, replyIntent);
            finish();
        });

        cancel.setOnClickListener(view -> {
            clicked = true;

            setResult(RESULT_CANCELED, replyIntent);
            finish();
        });
    }
}
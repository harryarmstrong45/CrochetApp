package com.example.android.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CrochetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final TextView ItemViewName;
    private final TextView ItemViewDesc;
    private CrochetPattern CrochetCurrent;
    public static final String ITEM_TO_UPDATE = "Update Item";
    public static final String ITEM_TO_DELETE = "Delete_Item";
    private final ImageView Crochet_Image;


    private CrochetViewHolder(View itemView) {
        super(itemView);
        ItemViewName = itemView.findViewById(R.id.textView_Name);
        ItemViewDesc = itemView.findViewById(R.id.textView_Description);
        Crochet_Image = itemView.findViewById(R.id.imageView3);
        itemView.setOnClickListener(this::onClick);
        itemView.setOnLongClickListener(getOnLongClickListener());
    }

    private View.OnLongClickListener getOnLongClickListener() {
        return view -> {
            Intent intent = new Intent(view.getContext(), delete_activity.class);
            intent.putExtra(ITEM_TO_DELETE, CrochetCurrent);
            ((Activity) view.getContext()).startActivityForResult(intent,3);
            return false;
        };
    }

    public void bind(CrochetPattern crochetPattern) {
        ItemViewName.setText(crochetPattern.getName());
        ItemViewDesc.setText(crochetPattern.getDescription());
        CrochetCurrent=crochetPattern;

        try {
            Crochet_Image.setImageBitmap(BitmapFactory.decodeByteArray(
                    crochetPattern.getImage(),
                    0,
                    crochetPattern.getImage().length));
        } catch (Exception e) {
            Crochet_Image.setImageBitmap(null);
        }
    }

    static CrochetViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CrochetViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), EditCrochetActivity.class);
        intent.putExtra(ITEM_TO_UPDATE, CrochetCurrent);
        ((Activity) view.getContext()).startActivityForResult(intent,2);
    }
}

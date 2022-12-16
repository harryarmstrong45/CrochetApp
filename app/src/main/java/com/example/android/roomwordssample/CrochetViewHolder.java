package com.example.android.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CrochetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final TextView ItemViewName;
    private final TextView ItemViewDesc;
    private CrochetPattern CrochetCurrent;
    public static final String ITEM_TO_UPDATE = "Update Item";


    private CrochetViewHolder(View itemView) {
        super(itemView);
        ItemViewName = itemView.findViewById(R.id.textView_Name);
        ItemViewDesc = itemView.findViewById(R.id.textView_Description);
        itemView.setOnClickListener(this::onClick);
    }

    public void bind(CrochetPattern crochetPattern) {
        ItemViewDesc.setText(crochetPattern.getDescription());
        ItemViewName.setText(crochetPattern.getName());
        CrochetCurrent=crochetPattern;
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

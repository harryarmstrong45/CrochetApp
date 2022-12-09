package com.example.android.roomwordssample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView ItemViewName;
    private final TextView ItemViewDesc;
    private final TextView ItemViewLoc;

    private ItemViewHolder(View itemView) {
        super(itemView);
        ItemViewName = itemView.findViewById(R.id.textView_Name);
        ItemViewDesc = itemView.findViewById(R.id.textView_Description);
        ItemViewLoc = itemView.findViewById(R.id.textView_location);
    }

    public void bind(String textName, String textDesc, String textLoc) {
        ItemViewDesc.setText(textDesc);
        ItemViewName.setText(textName);
        ItemViewLoc.setText(textLoc);
    }

    static ItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }
}

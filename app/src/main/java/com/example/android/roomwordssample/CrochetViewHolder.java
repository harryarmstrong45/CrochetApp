package com.example.android.roomwordssample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CrochetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final TextView ItemViewName;
    private final TextView ItemViewDesc;

    private CrochetViewHolder(View itemView) {
        super(itemView);
        ItemViewName = itemView.findViewById(R.id.textView_Name);
        ItemViewDesc = itemView.findViewById(R.id.textView_Description);
        itemView.setOnClickListener(this::onClick);
    }

    public void bind(String textName, String textDesc) {
        ItemViewDesc.setText(textDesc);
        ItemViewName.setText(textName);
    }

    static CrochetViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CrochetViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        int mPosition = getLayoutPosition() + 1;
        Toast.makeText(
                view.getContext(),
                 "You Selected: " + mPosition,
                Toast.LENGTH_SHORT
        ).show();
    }
}

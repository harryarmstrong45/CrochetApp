package com.example.android.roomwordssample;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CrochetListAdapter extends ListAdapter<CrochetPattern, CrochetViewHolder> {

    public CrochetListAdapter(@NonNull DiffUtil.ItemCallback<CrochetPattern> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CrochetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CrochetViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CrochetViewHolder holder, int position) {
        CrochetPattern current = getItem(position);
        holder.bind(current);
    }

    static class CrochetDiff extends DiffUtil.ItemCallback<CrochetPattern> {

        @Override
        public boolean areItemsTheSame(@NonNull CrochetPattern oldItem, @NonNull CrochetPattern newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CrochetPattern oldItem, @NonNull CrochetPattern newItem) {
            return oldItem.getName().equals(newItem.getName()); //TODO: Compare all attributes
        }
    }
}


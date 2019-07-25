package com.example.max.fragment2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.max.fragment2.model.Model;


import java.util.ArrayList;

public class ClickRecyclerAdapter extends CategoryAdapter implements View.OnClickListener {

    public ClickRecyclerAdapter(Context context, ArrayList<Model> models, OnItemClickListener mClickListener) {
        super(context, models);
        this.mClickListener = mClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private final OnItemClickListener mClickListener;


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = super.onCreateViewHolder(parent, viewType);
        holder.itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        mClickListener.onItemClick(v, position);
    }
}

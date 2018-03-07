package com.fuyin.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/4  23:01
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    private Context context;
    private List<T> list;

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createBaseHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindViewHolder(list.get(position),position);
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position, List<Object> payloads) {
        holder.bindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getHolderType(position);
    }


    public abstract int getHolderType(int position);
    public abstract BaseHolder createBaseHolder(ViewGroup parent,int viewType);

}

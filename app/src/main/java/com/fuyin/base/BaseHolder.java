package com.fuyin.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:46
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    private BaseAdapter<T> baseAdapter;

    public BaseHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    public abstract void initView(View view);

    public abstract void bindViewHolder(T t, int position);

    public abstract void bindViewHolder(T t, int position, List<Object> payloads);

    public BaseAdapter<T> getBaseAdapter() {
        return baseAdapter;
    }

    public void setBaseAdapter(BaseAdapter<T> baseAdapter) {
        this.baseAdapter = baseAdapter;
    }


}

package com.fuyin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fuyin.R;
import com.fuyin.base.BaseAdapter;
import com.fuyin.base.BaseHolder;
import com.fuyin.holder.TextHolder;
import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.model.Girl;

import java.util.List;


/**
 * Description
 * Created by Administrator
 * Time 2018/1/2  22:00
 */

public class HomeIndexAdapter extends BaseAdapter<Girl> {

    private List<Girl> list;
    private Context context;
    private final int ITEM_TEXT=0;
    private final int ITEM_EMPTY=1;
    private OnItemPictureClickListener listener;
    public HomeIndexAdapter(Context context, List<Girl> list,OnItemPictureClickListener listener) {
        super(context, list);
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getHolderType(int position) {
        return ITEM_TEXT;
    }



    @Override
    public BaseHolder createBaseHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        switch (viewType){
            case ITEM_TEXT:
                holder = new TextHolder(LayoutInflater.from(context).inflate(R.layout.item_text,parent,false),listener);
                break;
        }
        return holder;
    }


}

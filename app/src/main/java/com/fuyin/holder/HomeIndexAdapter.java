package com.fuyin.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fuyin.R;
import com.fuyin.base.BaseAdapter;
import com.fuyin.base.BaseHolder;
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

    public HomeIndexAdapter(Context context, List<Girl> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getHolderType(int position) {
        Girl girl = list.get(position);
        if(girl.getType()==0){
            return ITEM_TEXT;
        }else {
            return ITEM_EMPTY;
        }
    }



    @Override
    public BaseHolder createBaseHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        switch (viewType){
            case ITEM_EMPTY:
                holder = new EmptyHolder(LayoutInflater.from(context).inflate(R.layout.item_empty,parent,false));
                break;
            case ITEM_TEXT:
                holder = new TextHolder(LayoutInflater.from(context).inflate(R.layout.item_text,parent,false));
                break;
        }
        return holder;
    }
}

package com.fuyin.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/4  23:32
 */

public class BaseHelper {
    private static void setLinearLayoutManager(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter,int direction){
        if(context==null||recyclerView==null||adapter==null){
            throw new IllegalArgumentException("setLinearLayoutManagerVertical has null params!");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(direction);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setLinearLayoutManagerVertical(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter){
        setLinearLayoutManager(context,recyclerView,adapter,LinearLayoutManager.VERTICAL);
    }
    public static void setLinearLayoutManagerHorizontal(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter){
        setLinearLayoutManager(context,recyclerView,adapter,LinearLayoutManager.HORIZONTAL);
    }
}

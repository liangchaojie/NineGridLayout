package com.fuyin.demo.ninegrid;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fuyin.R;
import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    private Context context;
    private int itemPosition;
    private OnItemPictureClickListener listener;

    public NineGridTestLayout(Context context) {
        this(context,null);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        return true;
    }

    @Override
    protected void displayImage(int position,RatioImageView imageView, String url) {
        if(context!=null){
            Glide.with(context).load(url).into(imageView);
            imageView.setTransitionName(Utils.getNameByPosition(itemPosition,position));
            imageView.setTag(Utils.getNameByPosition(itemPosition,position));
        }
    }

    @Override
    protected void onClickImage(int imageIndex, String url, List<String> urlList, ImageView imageView) {
        listener.onItemPictureClick(itemPosition,imageIndex,url,urlList,imageView);
    }


    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public void setListener(OnItemPictureClickListener listener) {
        this.listener = listener;
    }
}

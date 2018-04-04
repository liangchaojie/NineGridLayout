package com.fuyin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.utils.Utils;
import com.squareup.picasso.Picasso;

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
            Picasso.with(context).load(url).into(imageView);
            imageView.setTag(Utils.getNameByPosition(itemPosition,position));
            imageView.setTransitionName(Utils.getNameByPosition(itemPosition,position));
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

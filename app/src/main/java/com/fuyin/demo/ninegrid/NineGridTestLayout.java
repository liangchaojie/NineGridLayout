package com.fuyin.demo.ninegrid;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fuyin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;
    private Context context;
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setTransitionName(getNameByPosition(position));
            }
            Glide.with(context).load(url).into(imageView);
        }
    }

    @Override
    protected void onClickImage(int i, String url, List<String> urlList, ImageView imageView) {

        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
        intent.putExtra("index", i);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) context, imageView, getNameByPosition(i));
            context.startActivity(intent, options.toBundle());
        }else {
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.activity_zoom_open, 0);
        }
    }

    private String getNameByPosition(int i) {
        String name = Name.IMAGE_1;
        switch (i){
            case 0:
                name = Name.IMAGE_1;
                break;
            case 1:
                name = Name.IMAGE_2;
                break;
            case 2:
                name = Name.IMAGE_3;
                break;
            case 3:
                name = Name.IMAGE_4;
                break;
            case 4:
                name = Name.IMAGE_5;
                break;
            case 5:
                name = Name.IMAGE_6;
                break;
            case 6:
                name = Name.IMAGE_7;
                break;
            case 7:
                name = Name.IMAGE_8;
                break;
            case 8:
                name = Name.IMAGE_9;
                break;

        }
        return name;
    }
}

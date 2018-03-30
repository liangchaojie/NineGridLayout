package com.fuyin.demo.ninegrid;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.Toast;


import com.bumptech.glide.Glide;

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
    protected void displayImage(RatioImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    protected void onClickImage(int i, String url, List<String> urlList) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
        intent.putExtra("index", i);
        context.startActivity(intent);
    }
}

package com.fuyin.demo.ninegrid;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * @Description:
 * @Author: Liangchaojie
 * @Create On 2018/3/30 10:33
 */
public class ImagePreviewAdapter extends PagerAdapter {
    private Context context;
    private List<String> imageList;

    public ImagePreviewAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList==null?0:imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final PhotoView image = new PhotoView(context);
        // 开启图片缩放功能
        image.setEnabled(true);
        // 设置缩放类型
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setMaximumScale(2.0F);
        image.setMinimumScale(0.8F);
        // 加载图片
        Glide.with(context).load(imageList.get(position)).into(image);
        // 单击图片，返回
       image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               image.setEnabled(false);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   image.setTransitionName(getNameByPosition(position));
                   ((Activity)context).onBackPressed();
               }
           }
       });
        container.addView(image);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
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

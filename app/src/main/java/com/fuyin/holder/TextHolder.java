package com.fuyin.holder;

import android.view.View;
import android.widget.TextView;

import com.fuyin.R;
import com.fuyin.base.BaseHolder;
import com.fuyin.demo.ninegrid.NineGridTestLayout;
import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.model.Girl;


/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:49
 */

public class TextHolder extends BaseHolder<Girl> {
    private TextView textView;
    private NineGridTestLayout nineGridTestLayout;
    private OnItemPictureClickListener listener;
    public TextHolder(View view, OnItemPictureClickListener listener) {
        super(view);
        this.listener = listener;
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.tv);
        nineGridTestLayout = view.findViewById(R.id.nineLayout);
    }

    @Override
    public void bindViewHolder(Girl girl, int position) {
        textView.setText(girl.getName());
        nineGridTestLayout.setListener(listener);
        nineGridTestLayout.setItemPosition(position);
        nineGridTestLayout.setIsShowAll(false); //当传入的图片数超过9张时，是否全部显示
        nineGridTestLayout.setSpacing(5); //动态设置图片之间的间隔
        nineGridTestLayout.setUrlList(girl.getImageList());
    }


}

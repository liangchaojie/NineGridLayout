package com.fuyin.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuyin.R;
import com.fuyin.base.BaseHolder;
import com.fuyin.views.NineGridTestLayout;
import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.model.Girl;


/**
 * Description 微信朋友圈九宫格图片Holder
 * Created by Administrator
 * Time 2018/1/3  21:49
 */

public class MomentHolder extends BaseHolder<Girl> {
    private ImageView imageViewUserIcon;
    private TextView tv_name;
    private NineGridTestLayout nineGridTestLayout;
    private OnItemPictureClickListener listener;
    public MomentHolder(View view, OnItemPictureClickListener listener) {
        super(view);
        this.listener = listener;
    }

    @Override
    public void initView(View view) {
        imageViewUserIcon = view.findViewById(R.id.user_icon);
        tv_name = view.findViewById(R.id.user_name);
        nineGridTestLayout = view.findViewById(R.id.nineTestlayout);
    }

    @Override
    public void bindViewHolder(Girl girl, int position) {
        tv_name.setText(girl.getName());
        nineGridTestLayout.setListener(listener);
        nineGridTestLayout.setItemPosition(position);
        nineGridTestLayout.setSpacing(15);
        nineGridTestLayout.setUrlList(girl.getImageList());

        imageViewUserIcon.setImageResource(R.mipmap.head);
    }


}

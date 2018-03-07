package com.fuyin.holder;

import android.view.View;
import android.widget.TextView;

import com.fuyin.R;
import com.fuyin.base.BaseHolder;
import com.fuyin.model.Girl;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:49
 */

public class EmptyHolder extends BaseHolder<Girl> {
    public TextView textView;

    public EmptyHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView(View itemView) {
        textView = itemView.findViewById(R.id.tv);
    }

    @Override
    public void bindViewHolder(Girl girl, int position) {
        textView.setText(girl.getName());
    }

}

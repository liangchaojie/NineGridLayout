package com.fuyin.holder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fuyin.R;
import com.fuyin.base.BaseHolder;
import com.fuyin.model.Girl;

import java.util.List;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:49
 */

public class EmptyHolder extends BaseHolder<Girl> {
    public TextView textView;
    public Button button;

    public EmptyHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView(View itemView) {
        textView = itemView.findViewById(R.id.tv);
        button = itemView.findViewById(R.id.button);
    }

    @Override
    public void bindViewHolder(final Girl girl, int position) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girl.setName("哈哈 我是异类!");
                getBaseAdapter().notifyItemChanged(2,2);
            }
        });
    }

    @Override
    public void bindViewHolder(Girl girl, int position, List<Object> payloads) {
        if(payloads!=null){
            Log.i("TAG", "局部刷新: ");
        }
    }

}

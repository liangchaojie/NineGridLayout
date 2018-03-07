package com.fuyin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;


import com.fuyin.base.BaseHelper;
import com.fuyin.holder.HomeIndexAdapter;
import com.fuyin.model.Girl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Girl> list;
    private HomeIndexAdapter recyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        doLogic();
    }

    private void doLogic() {
        BaseHelper.setLinearLayoutManagerVertical(this,recyclerView,recyAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Girl girl = new Girl();
            girl.setName("我是第"+i+"个girl");
            girl.setType(i%2==0?0:1);
            list.add(girl);
        }
        recyAdapter = new HomeIndexAdapter(this,list);
    }


    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
    }
}

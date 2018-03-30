package com.fuyin.demo.ninegrid;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuyin.R;

import java.util.List;

/**
 * 图片预览 Activity
 */
public class ImagePreviewActivity extends AppCompatActivity {

    private int index;
    private List<String> imageList;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        getIntentData();
        initView();
        renderView();
    }

    private void initView() {
        viewPager = findViewById(R.id.imageBrowseViewPager);
    }

    private void renderView() {
        ImagePreviewAdapter adapter = new ImagePreviewAdapter(this,imageList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);
    }

    private void getIntentData() {
        if(getIntent()!=null){
            index = getIntent().getIntExtra("index", 0);
            imageList = getIntent().getStringArrayListExtra("imageList");
        }
    }
}

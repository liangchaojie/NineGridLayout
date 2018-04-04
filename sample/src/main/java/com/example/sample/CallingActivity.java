package com.example.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CallingActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallingActivity.this, CalledActivity.class);
                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(CallingActivity.this, imageView,imageView.getTransitionName());
                startActivity(intent, compat.toBundle());
            }
        });
    }
}

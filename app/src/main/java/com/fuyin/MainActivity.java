package com.fuyin;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.fuyin.base.BaseHelper;
import com.fuyin.demo.ninegrid.ImagePreviewActivity;
import com.fuyin.holder.HomeIndexAdapter;
import com.fuyin.interfaces.OnItemPictureClickListener;
import com.fuyin.model.Girl;
import com.fuyin.utils.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_START_POSITION   = "start_position";
    public static final String EXTRA_CURRENT_POSITION = "current_position";
    private RecyclerView recyclerView;
    private HomeIndexAdapter homeIndexAdapter;
    private List<String> imageList;
    private List<Girl> girlList;
    private Bundle   mReenterState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initShareElement();
        initView();
        initData();
        doLogic();
    }

    private void doLogic() {
        BaseHelper.setLinearLayoutManagerVertical(this,recyclerView,homeIndexAdapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }


    private void initData() {
        imageList = new ArrayList<>();
        imageList.add("http://cuimg.zuyushop.com/cuxiaoPic/201511/2015110010091817554.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJlbKx2qIGStWAAePuU7wk_cAALHzQF9mKIAB4_R763.jpg");
        imageList.add("http://img1.imgtn.bdimg.com/it/u=3356331771,2093090619&fm=214&gp=0.jpg");
        imageList.add("http://img5.duitang.com/uploads/item/201405/12/20140512000053_axANX.thumb.700_0.jpeg");
        imageList.add("http://pic2.16pic.com/00/54/76/16pic_5476585_b.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170711/3f177d2be18143a48a9af1217e669855_th.png");
        imageList.add("http://img4.duitang.com/uploads/item/201509/26/20150926014223_BW8EG.jpeg");
        imageList.add("http://mvimg10.meitudata.com/569b9090af0526344.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170703/c8c1818222a547f78585f9b357c93613_th.png");

        girlList = new ArrayList<>();
        for(int i=0;i<10;i++){
            Girl girl = new Girl();
            girl.setName(i+"号选手");
            girl.setImageList(imageList);
            girlList.add(girl);
        }

        homeIndexAdapter = new HomeIndexAdapter(this, girlList, new OnItemPictureClickListener() {
            @Override
            public void onItemPictureClick(int itemPosition,int position, String url, List<String> urlList, ImageView imageView) {
                Intent intent = new Intent(MainActivity.this, ImagePreviewActivity.class);
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
                intent.putExtra("itemPosition", itemPosition);
                intent.putExtra(ImagePreviewActivity.EXTRA_START_POSITION, position);

                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imageView, imageView.getTransitionName());
                startActivity(intent, compat.toBundle());
            }
        });
    }


    private void initShareElement() {
        setExitSharedElementCallback(mCallback);
    }

    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
        mReenterState = new Bundle(data.getExtras());
        int startingPosition = mReenterState.getInt(EXTRA_START_POSITION);
        int currentPosition = mReenterState.getInt(EXTRA_CURRENT_POSITION);
        if (startingPosition != currentPosition) {
            recyclerView.scrollToPosition(currentPosition);
        }
        postponeEnterTransition();
        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                recyclerView.requestLayout();
                startPostponedEnterTransition();
                return true;
            }
        });
    }


    private final SharedElementCallback mCallback = new SharedElementCallback() {

        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mReenterState != null) {
                //从别的界面返回当前界面
                int startingPosition = mReenterState.getInt(EXTRA_START_POSITION);
                int currentPosition = mReenterState.getInt(EXTRA_CURRENT_POSITION);
                if (startingPosition != currentPosition) {
                    String newTransitionName = Utils.getNameByPosition(currentPosition,0);
                    View newSharedElement = recyclerView.findViewWithTag(newTransitionName);
                    if (newSharedElement != null) {
                        names.clear();
                        names.add(newTransitionName);
                        sharedElements.clear();
                        sharedElements.put(newTransitionName, newSharedElement);
                    }
                }
                mReenterState = null;
            }else {
                //从当前界面进入到别的界面
                PhotoView photoView = findViewById(R.id.all);
                if (photoView != null) {
                    names.add(photoView.getTransitionName());
                    sharedElements.put(photoView.getTransitionName(), photoView);
                }
            }
        }
    };

}

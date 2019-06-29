package com.fuyin.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.fuyin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @Description:  九宫格布局显示图片
 *                1    显示1张图片的时候可以按照自适应或者长宽比
 *                2    显示2张及2张以上都是正方形
 *                3    4张图片显示排列方式是 2*2
 *                4   一行最多三张图片
 * @Author: Liangchaojie
 * @Create On 2018/3/29 18:22
 */
public abstract class NineGridLayout extends ViewGroup {
    private static final float DEFUALT_SPACING = 15f;
    private float image_ratio = 1.7f;//默认图片长宽比例
    private int oneImageWidth;//一张图的宽度
    private int oneImageHeight;//一张图的高度
    protected Context mContext;
    private float mSpacing = DEFUALT_SPACING;
    private int mColumns;
    private int mRows;
    private int mTotalWidth;
    private int mSingleWidth;

    private boolean mIsFirst = true;
    private List<String> mUrlList = new ArrayList<>();

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        mSpacing = typedArray.getDimension(R.styleable.NineGridLayout_sapcing, DEFUALT_SPACING);
        oneImageWidth = (int) typedArray.getDimension(R.styleable.NineGridLayout_oneImageWidth, 0);
        oneImageHeight = (int) typedArray.getDimension(R.styleable.NineGridLayout_oneImageHeight, 0);
        image_ratio =   typedArray.getFloat(R.styleable.NineGridLayout_image_ratio, image_ratio);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        if (getListSize(mUrlList) == 0) {
            setVisibility(GONE);
        }else {
            setVisibility(VISIBLE);
        }
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mTotalWidth = right - left;
        mSingleWidth = (int) ((mTotalWidth - mSpacing * (3 - 1)) / 3);
        if (mIsFirst) {
            notifyDataSetChanged();
            mIsFirst = false;
        }
    }


    public void setSpacing(float spacing) {
        mSpacing = spacing;
    }


    public void setUrlList(List<String> urlList) {
        if (getListSize(urlList) == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);

        mUrlList.clear();
        mUrlList.addAll(urlList);
        //由于RecyclerView中牵扯到复用布局，所以需要判断当前布局是不是第一次使用，是的话就直接绘制，不是的话就移除掉恰他的布局再绘制
        if (!mIsFirst) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        post(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        });
    }

    private void refresh() {
        removeAllViews();
        int size = getListSize(mUrlList);
        if (size > 0) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
            return;
        }

        if (size == 1) {
            String url = mUrlList.get(0);
            RatioImageView imageView = createImageView(0, url);

            getRealOneImageSize();
            imageView.layout(0, 0, oneImageWidth, oneImageHeight);
            LayoutParams params = getLayoutParams();
            params.height = oneImageHeight;
            setLayoutParams(params);
            addView(imageView);
            displayImage(0,imageView, url);
            return;
        }

        generateChildrenLayout(size);
        layoutParams();

        for (int i = 0; i < size; i++) {
            String url = mUrlList.get(i);
            RatioImageView imageView = createImageView(i, url);
            layoutImageView(imageView, i, url);
        }
    }

    private void getRealOneImageSize() {
        if(oneImageWidth==0){
            oneImageWidth = mSingleWidth;
        }

        if(oneImageHeight==0){
            oneImageHeight = (int) (oneImageWidth * image_ratio);
        }
    }

    private void layoutParams() {
        int singleHeight = mSingleWidth;
        LayoutParams params = getLayoutParams();
        params.height = (int) (singleHeight * mRows + mSpacing * (mRows - 1));
        setLayoutParams(params);
    }

    private RatioImageView createImageView(final int i, final String url) {
        final RatioImageView imageView = new RatioImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage(i, url, mUrlList,imageView);
            }
        });
        imageView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext,"你长按我干啥",Toast.LENGTH_SHORT).show();
                //注意这里要返回true防止OnClickListener处理长按事件
                return true;
            }
        });
        return imageView;
    }

    /**
     * @param imageView
     * @param url
     */
    private void layoutImageView(RatioImageView imageView, int i, String url) {
        final int singleWidth = (int) ((mTotalWidth - mSpacing * (3 - 1)) / 3);
        int singleHeight = singleWidth;

        int[] position = findPosition(i);
        int left = (int) ((singleWidth + mSpacing) * position[1]);
        int top = (int) ((singleHeight + mSpacing) * position[0]);
        int right = left + singleWidth;
        int bottom = top + singleHeight;

        imageView.layout(left, top, right, bottom);
        addView(imageView);
        displayImage(i,imageView, url);
    }

    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mColumns; j++) {
                if ((i * mColumns + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    /**
     * 根据图片个数确定行列数量
     *
     * @param length
     */
    private void generateChildrenLayout(int length) {
        if (length <= 3) {
            mRows = 1;
            mColumns = length;
        } else if (length <= 6) {
            mRows = 2;
            mColumns = 3;
            if (length == 4) {
                mColumns = 2;
            }
        } else {
            mColumns = 3;
            mRows = 3;
        }

    }

    private int getListSize(List<String> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }



    protected abstract void displayImage(int position,RatioImageView imageView, String url);

    protected abstract void onClickImage(int position, String url, List<String> urlList,ImageView imageView);
}

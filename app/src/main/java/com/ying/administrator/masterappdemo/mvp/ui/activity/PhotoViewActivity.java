package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.photoview)
    PhotoView photoview;

    @BindView(R.id.view)
    View view;
    private String PhotoUrl;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_view_photos;
    }

    @Override
    protected void initData() {
        PhotoUrl=getIntent().getStringExtra("PhotoUrl");
        Log.d("=====>",PhotoUrl);
    }
    @Override
    protected void initView() {
        photoview.setScaleType(ImageView.ScaleType.CENTER);
        photoview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        photoview.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });

        RequestOptions options=new RequestOptions().fitCenter().error(R.drawable.image_loading);
        Glide.with(mActivity)
                .load(PhotoUrl)
                .apply(options)
                //   .placeholder(R.mipmap.ic_launcher)//加载过程中图片未显示时显示的本地图片
                // .error(R.mipmap.ic_launcher)//加载异常时显示的图片
                //  .centerCrop()//图片图填充ImageView设置的大小
                .into(photoview);
    }

    @Override
    protected void setListener() {

    }
}

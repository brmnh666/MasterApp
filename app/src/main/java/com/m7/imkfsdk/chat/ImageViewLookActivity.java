package com.m7.imkfsdk.chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.m7.imkfsdk.utils.ImageUtils;
import com.m7.imkfsdk.view.ActionSheetDialog;
import com.m7.imkfsdk.view.TouchImageView;
import com.ying.administrator.masterappdemo.R;

/**
 * Created by long on 2015/7/3.
 */
public class ImageViewLookActivity extends Activity{

    private TouchImageView touchImageView;
    private boolean isSaveSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.kf_activity_image_look);
        touchImageView = (TouchImageView) findViewById(R.id.matrixImageView);

        Window window = getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.kf_bg));
        }


        Intent intent = getIntent();
        final String imgPath = intent.getStringExtra("imagePath");
        final int fromother = intent.getIntExtra("fromwho",1);//谁发的

        if(imgPath != null && !"".equals(imgPath)) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.kf_pic_thumb_bg)//这里可自己添加占位图
                    .error(R.drawable.kf_image_download_fail_icon);
            Glide.with(this).load(imgPath)
                    .apply(options)
                    .into(touchImageView);
        }else {
            finish();
        }

        touchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        touchImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(fromother==0){//
                    //弹框
                    openDialog(imgPath);
                }else{
//                    ToastUtils.showShort("不保存本地的"+imgPath);
                }
                return true;
            }


        });

    }
    private void openDialog(final String imageUrl) {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem(
//                        this.getResources().getString(R.string.restartchat),
                        "保存图片",
                        ActionSheetDialog.SheetItemColor.BLACK, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                saveImage(imageUrl);
                            }
                        }).show();
    }


    //保存图片
    private void saveImage(String ImageUrl) {

        Glide.with(ImageViewLookActivity.this)
                .asBitmap()  // some .jpeg files are actually gif
                .load(ImageUrl)
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //得到bitmap
                        isSaveSuccess = ImageUtils.saveImageToGallery(ImageViewLookActivity.this, resource);
                        if (isSaveSuccess) {
                            Toast.makeText(ImageViewLookActivity.this, "保存图片成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ImageViewLookActivity.this, "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        Glide.with(ImageViewLookActivity.this)
//                .load(ImageUrl)
//                .asBitmap()
//                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                        //得到bitmap
//                        isSaveSuccess = ImageUtils.saveImageToGallery(ImageViewLookActivity.this, bitmap);
//                        if (isSaveSuccess) {
//                            Toast.makeText(ImageViewLookActivity.this, "保存图片成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(ImageViewLookActivity.this, "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


    }


}

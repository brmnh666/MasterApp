package com.ying.administrator.masterappdemo.v3.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 播放网络视频
 */

public class VideoActivity extends BaseActivity implements View.OnClickListener {


//    @BindView(R.id.iv_back)
//    ImageView mIvBack;
//    @BindView(R.id.tv_title)
//    TextView mTvTitle;
//    @BindView(R.id.tv_save)
//    TextView mTvSave;
//    @BindView(R.id.ll_customer_service)
//    LinearLayout mLlCustomerService;
    @BindView(R.id.jz_video)
    JzvdStd mJzVideo;
    private String url;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_video;
    }

    @Override
    protected void initData() {

    }
    /**
     * 香港卫视：http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8
     * CCTV1高清：http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
     * CCTV3高清：http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
     * CCTV5高清：http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8
     * CCTV5+高清：http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8
     * CCTV6高清：http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8
     * 苹果提供的测试源（点播）：http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8
     */
    @Override
    protected void initView() {
        url =getIntent().getStringExtra("url");
        mJzVideo.setUp(url, ""
                , JzvdStd.SCREEN_NORMAL);
        Glide.with(this)
                .load(url)
                .into(mJzVideo.posterImageView);
        mJzVideo.startVideo();
//        JzvdStd.startFullscreenDirectly(mActivity,JzvdStd.class,"http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8","haha");
    }


    @Override
    protected void setListener() {
//        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_back:
//                finish();
//                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();

        //Change these two variables back
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


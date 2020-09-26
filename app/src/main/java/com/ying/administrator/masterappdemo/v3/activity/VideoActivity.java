package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 播放网络视频
 */

public class VideoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
//    @BindView(R.id.jz_video)
//    JzvdStd mJzVideo;

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
//        JzvdStd.startFullscreenDirectly(mActivity,JzvdStd.class,"http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8","haha");
    }


    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


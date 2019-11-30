package com.ying.administrator.masterappdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.ying.administrator.masterappdemo.widget.MyProgress;
import com.ying.administrator.masterappdemo.widget.SaleProgressView;

public class UpgradeActivity  extends Activity {
    //    private TextView tv;
    private TextView title;
    private TextView version;
    //    private TextView size;
//    private TextView time;
    private TextView content;
    private ImageView cancel;
    private Button start;
    int upgradeType = Beta.getUpgradeInfo().upgradeType;
    private SaleProgressView spv;
    private int fileSize;
    private MyProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_upgrade);
//        tv = getView(R.id.tv);
        title = getView(R.id.tv_title);
        version = getView(R.id.tv_version);
//        size = getView(R.id.size);
//        time = getView(R.id.time);
        content = getView(R.id.tv_content);
        cancel = getView(R.id.iv_close);
        start = getView(R.id.btn_start);
        spv = getView(R.id.spv);
        progress = getView(R.id.progress);
        /*获取下载任务，初始化界面信息*/
        updateBtn(Beta.getStrategyTask());
//        tv.setText(tv.getText().toString() + Beta.getStrategyTask().getSavedLength() + "");

        //包大小
        fileSize = new Long(Beta.getUpgradeInfo().fileSize).intValue();
        progress.setMax(fileSize);
        /*获取策略信息，初始化界面信息*/
        title.setText(title.getText().toString() + Beta.getUpgradeInfo().title);
        version.setText(version.getText().toString() + Beta.getUpgradeInfo().versionName);
//        size.setText(size.getText().toString() + Beta.getUpgradeInfo().fileSize + "");
//        time.setText(time.getText().toString() + Beta.getUpgradeInfo().publishTime + "");
        content.setText(Beta.getUpgradeInfo().newFeature);
        if (upgradeType==2){
            cancel.setVisibility(View.GONE);
        }else {
            cancel.setVisibility(View.VISIBLE);
        }

        /*为下载按钮设置监听*/
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                DownloadTask task = Beta.startDownload();
                updateBtn(task);
                if (task.getStatus() == DownloadTask.DOWNLOADING) {
//                    finish();
                    ToastUtils.showShort("开始下载");
                }
            }
        });


        /*为取消按钮设置监听*/
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.cancelDownload();
                finish();
            }
        });

        /*注册下载监听，监听下载事件*/
        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask task) {
                updateBtn(task);
//                tv.setText(task.getSavedLength() + "");
                int li=  new Long(task.getSavedLength()).intValue();
                progress.setProgress(li);//更新进度条
                if (li==fileSize){
                    progress.setVisibility(View.GONE);
                    start.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCompleted(DownloadTask task) {
                updateBtn(task);
//                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onFailed(DownloadTask task, int code, String extMsg) {
                updateBtn(task);
//                tv.setText("failed");
                progress.setVisibility(View.GONE);
            }
        });
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;//设置对话框置顶显示
        win.setAttributes(lp);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*注销下载监听*/
        Beta.unregisterDownloadListener();
    }


    public void updateBtn(DownloadTask task) {

        /*根据下载任务状态设置按钮*/
        switch (task.getStatus()) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                start.setText("开始下载");
            }
            break;
            case DownloadTask.COMPLETE: {
                start.setText("安装");
            }
            break;
            case DownloadTask.DOWNLOADING: {
                start.setText("暂停");
            }
            break;
            case DownloadTask.PAUSED: {
                start.setText("继续下载");
            }
            break;
        }
    }

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }
}

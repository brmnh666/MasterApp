package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.ying.administrator.masterappdemo.BuildConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;
import com.ying.administrator.masterappdemo.mvp.model.VerifiedModel;
import com.ying.administrator.masterappdemo.mvp.presenter.VerifiedPresenter;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VerifiedPhotoActivity extends BaseActivity<VerifiedPresenter, VerifiedModel> implements View.OnClickListener, VerifiedContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.iv_positive)
    ImageView mIvPositive;
    @BindView(R.id.iv_negative)
    ImageView mIvNegative;
    @BindView(R.id.iv_selfie)
    ImageView mIvSelfie;
    @BindView(R.id.submit_application_bt)
    Button mSubmitApplicationBt;
    private int size;
    private ArrayList<String> permissions;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private List<Uri> mSelected;
    private Uri uri;
    private SPUtils spUtils;
    private String UserID;
    private String mPositiveCard = "";
    private String mNegativeCard = "";
    private String mSelfie = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_verified_photo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("实名认证");
        spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mIvNegative.setOnClickListener(this);
        mIvPositive.setOnClickListener(this);
        mIvSelfie.setOnClickListener(this);
        mSubmitApplicationBt.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_positive:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.iv_negative:
                if (requestPermissions()) {
                    showPopupWindow(201, 202);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }
                break;
            case R.id.iv_selfie:
                if (requestPermissions()) {
                    showPopupWindow(301, 302);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10003);
                }
                break;
            case R.id.submit_application_bt:
                if ("".equals(mPositiveCard)) {
                    ToastUtils.showShort("请添加正面身份证照片！");
                    return;
                } else if ("".equals(mNegativeCard)) {
                    ToastUtils.showShort("请添加反面身份证照片！");
                    return;
//                } else if ("".equals(mSelfie)) {
//                    ToastUtils.showShort("请添加清晰自拍照！");
//                    return;
                } else {
                    EventBus.getDefault().post("verified");
                    ToastUtils.showShort("完善成功");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void IDCardUpload(BaseResult<Data<String>> baseResult, int code) {
        switch (code) {
            case 0:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        if (baseResult.getData().isItem1()) {
                            mPositiveCard = baseResult.getData().getItem2();
                        }
                        break;
                    default:
                        ToastUtils.showShort("图片上传失败");
                        break;
                }
                break;
            case 1:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        if (baseResult.getData().isItem1()) {
                            mNegativeCard = baseResult.getData().getItem2();
                        }
                        break;
                    default:
                        ToastUtils.showShort("图片上传失败");
                        break;
                }
                break;
            case 2:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        if (baseResult.getData().isItem1()) {
                            mSelfie = baseResult.getData().getItem2();
                        }
                        break;
                    default:
                        ToastUtils.showShort("图片上传失败");
                        break;
                }
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void ApplyAuthInfo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApplyAuthInfoBysub(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                if (requestPermissions()) {
                Intent intent = new Intent();
                // 指定开启系统相机的Action
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                String f = System.currentTimeMillis() + ".jpg";
                String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
                FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
                File dirfile = new File(fileDir);
                if (!dirfile.exists()) {
                    dirfile.mkdirs();
                }
                File file = new File(FilePath);
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= 24) {
                    fileUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".fileprovider", file);
                } else {
                    fileUri = Uri.fromFile(file);
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, code1);
//                } else {
//                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
//                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                if (requestPermissions()) {
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("image/*");
//                startActivityForResult(Intent.createChooser(i, "test"), code2);
                Matisse.from(VerifiedPhotoActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(1)
//                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new Glide4Engine())
                        .forResult(code2);
                mPopupWindow.dismiss();
//                } else {
//                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
//                }

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size == grantResults.length) {//允许
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                    MyUtils.toSelfSetting(mActivity);
                }
                break;
            case 10002:
                if (size == grantResults.length) {//允许
                    showPopupWindow(201, 202);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                    MyUtils.toSelfSetting(mActivity);
                }
                break;
            case 10003:
                if (size == grantResults.length) {//允许
                    showPopupWindow(301, 302);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                    MyUtils.toSelfSetting(mActivity);
                }
                break;
            default:
                break;

        }
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvPositive);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 0);

                }
                break;
            //相册
            case 102:

                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvPositive);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 0);
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvNegative);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 1);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvNegative);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 1);
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvSelfie);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 2);
                }
                break;
            //相册
            case 302:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvSelfie);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile, 2);
                }
                break;
        }
    }

    public void uploadImg(File f, int code) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        builder.addFormDataPart("UserID", UserID);
        builder.addFormDataPart("Sort", (code + 1) + "");
        MultipartBody requestBody = builder.build();
        mPresenter.IDCardUpload(requestBody, code);
    }


}

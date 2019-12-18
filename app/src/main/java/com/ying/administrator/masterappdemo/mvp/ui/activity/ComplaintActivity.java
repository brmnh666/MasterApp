package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ComplaintContract;
import com.ying.administrator.masterappdemo.mvp.model.ComplaintModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ComplaintPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ComplaintActivity extends BaseActivity<ComplaintPresenter, ComplaintModel> implements View.OnClickListener, ComplaintContract.View {

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_orderid)
    TextView mTvOrderid;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.spinner_object)
    Spinner mSpinnerObject;
    @BindView(R.id.spinner_type)
    Spinner mSpinnerType;
    @BindView(R.id.ll_type)
    LinearLayout mLlType;
    @BindView(R.id.et_information)
    EditText mEtInformation;
    @BindView(R.id.ll_information)
    LinearLayout mLlInformation;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.et_fault_description)
    EditText mEtFaultDescription;
    @BindView(R.id.ll_microphone_one)
    LinearLayout mLlMicrophoneOne;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.btn_complaint)
    Button mBtnComplaint;
    private String orderId;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String ComplaintType;
    private ArrayList<String> permissions;
    private int size;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private HashMap<Integer, File> img = new HashMap<>();
    private String content;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_complaint;
    }

    @Override
    protected void initData() {
        mSpinnerObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        ComplaintType = "";
                        break;
                    case 1:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        ComplaintType = "F";
                        break;
                    case 2:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        ComplaintType = "T";
                        break;
                    case 3:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        ComplaintType = "P";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("投诉");
        mTvMessage.setText("投诉详情");
        mTvMessage.setVisibility(View.VISIBLE);
        orderId = getIntent().getStringExtra("orderId");
        mTvOrderid.setText(orderId);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mIvCopy.setOnClickListener(this);
        mBtnComplaint.setOnClickListener(this);
        mIvPicture.setOnClickListener(this);
        mTvMessage.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.iv_copy:
                myClip = ClipData.newPlainText("", orderId);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.iv_picture:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.btn_complaint:
                content = mEtFaultDescription.getText().toString();
                if ("".equals(ComplaintType) || ComplaintType.isEmpty()) {
                    ToastUtils.showShort("请选择投诉对象");
                    return;
                } else if (content.isEmpty()) {
                    ToastUtils.showShort("请输入投诉内容");
                    return;
                } else {
                    if (img.size() > 0) {
                        uploadImg(img);
                    } else {
                        mPresenter.WorkerComplaint(orderId, content, ComplaintType, "");
                    }

                }
                break;
            case R.id.tv_message:
                Intent intent=new Intent(mActivity,ComplaintDetailActivity.class);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
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
    public void WorkerComplaint(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                finish();
                break;
        }
    }

    @Override
    public void ComPlaintImg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    mPresenter.WorkerComplaint(orderId, content, ComplaintType, baseResult.getData().getItem2());
                }
                break;
            default:
                ToastUtils.showShort("图片上传失败");
                break;
        }
    }

    @Override
    public void GetComplaintListByOrderId(BaseResult<List<ComplaintList>> baseResult) {

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
                }
                break;
            case 10002:
                if (size == grantResults.length) {//允许
                    showPopupWindow(201, 202);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10003:
                if (size == grantResults.length) {//允许
                    showPopupWindow(301, 302);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            default:
                break;

        }
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
                    fileUri = FileProvider.getUriForFile(mActivity, "com.zhenhaikj.factoryside.fileProvider", file);
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
//                Matisse.from(mActivity)
//                        .choose(MimeType.ofImage())
//                        .countable(true)
//                        .maxSelectable(1)
////                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
////                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f)
//                        .imageEngine(new Glide4Engine())
//                        .forResult(code2);
//                mPopupWindow.dismiss();
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                intent1.setType("image/*");
                startActivityForResult(Intent.createChooser(intent1, "test"), code2);
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvPicture);
//                    mLlDel.setVisibility(View.VISIBLE);
                    file = new File(FilePath);
//                    mAnnexIv.setClickable(false);
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(mActivity.getApplicationContext()).compressToFile(file);
//                    uploadImg(newFile, 0);
                    img.put(0, newFile);
                }
                break;
            //相册
            case 102:
                if (data != null) {
//                    List<Uri> mSelected = Matisse.obtainResult(data);
//
//
//                    if (mSelected.size() == 1) {
//                        uri = mSelected.get(0);
//                    }
////                    uri = data.getData();
//                    Glide.with(mActivity).load(uri).into(mAnnexIv);
////                    mLlDel.setVisibility(View.VISIBLE);
////                    mAnnexIv.setClickable(false);
//                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvPicture);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));

                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(mActivity.getApplicationContext()).compressToFile(file);
//                    uploadImg(newFile, 0);
                    img.put(0, newFile);
                }
                break;
        }
    }

    public void uploadImg(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        MultipartBody requestBody = builder.build();
        mPresenter.ComPlaintImg(requestBody);
    }
}

package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.google.gson.Gson;
import com.hw.videoprocessor.VideoProcessor;
import com.hw.videoprocessor.util.VideoProgressListener;
import com.ying.administrator.masterappdemo.BuildConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.CompleteWorkOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CompleteWorkOrderPresenter;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.util.imageutil.FileSizeUtil;
import com.ying.administrator.masterappdemo.v3.bean.EndResult;
import com.ying.administrator.masterappdemo.v3.bean.PolicyToken;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompleteWorkOrderActivity extends BaseActivity<CompleteWorkOrderPresenter, CompleteWorkOrderModel> implements CompleteWorkOrderContract.View, View.OnClickListener {

    private static final String TAG = "CompleteWorkOrderActivi";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_cate)
    TextView mTvCate;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;
    @BindView(R.id.tv_gg)
    TextView mTvGg;
    @BindView(R.id.tv_memo)
    TextView mTvMemo;
    @BindView(R.id.et_single_number)
    EditText mEtSingleNumber;
    @BindView(R.id.et_express_name)
    EditText mEtExpressName;
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.ll_view_example)
    LinearLayout mLlViewExample;
    @BindView(R.id.iv_machine)
    ImageView mIvMachine;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
    @BindView(R.id.iv_bar_code)
    ImageView mIvBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.iv_fault_location)
    ImageView mIvFaultLocation;
    @BindView(R.id.ll_fault_location)
    LinearLayout mLlFaultLocation;
    @BindView(R.id.iv_new_and_old_accessories)
    ImageView mIvNewAndOldAccessories;
    @BindView(R.id.ll_new_and_old_accessories)
    LinearLayout mLlNewAndOldAccessories;
    @BindView(R.id.ll_return_information)
    LinearLayout mLlReturnInformation;
    @BindView(R.id.ll_view_example_two)
    LinearLayout mLlViewExampleTwo;
    @BindView(R.id.et_single_number1)
    EditText mEtSingleNumber1;
    @BindView(R.id.et_express_name1)
    EditText mEtExpressName1;
    @BindView(R.id.ll_scan1)
    LinearLayout mLlScan1;
    @BindView(R.id.ll_code1)
    LinearLayout mLlCode1;
    @BindView(R.id.iv_one)
    ImageView mIvOne;
    @BindView(R.id.iv_two)
    ImageView mIvTwo;
    @BindView(R.id.iv_three)
    ImageView mIvThree;
    @BindView(R.id.iv_four)
    ImageView mIvFour;
    @BindView(R.id.ll_service_process)
    LinearLayout mLlServiceProcess;
    @BindView(R.id.et_memo)
    EditText mEtMemo;
    @BindView(R.id.btn_complete_submit)
    Button mBtnCompleteSubmit;
    @BindView(R.id.et_result)
    EditText mEtResult;
    @BindView(R.id.ll_result)
    LinearLayout mLlResult;
    @BindView(R.id.iv_video)
    ImageView mIvVideo;
    @BindView(R.id.ll_video)
    LinearLayout mLlVideo;
    @BindView(R.id.iv_shipin)
    ImageView mIvShipin;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;

    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;

    private HashMap<Integer, File> return_img_map = new HashMap<>();//上传的返件图片
    private HashMap<Integer, File> service_img_map = new HashMap<>();//上传安装图片


    private List<Uri> mSelected;
    private Uri uri;
    private String barCode;
    private String ProductID;
    private String orderID;
    private String TypeID;
    private String BarCodeIsNo;
    private WorkOrder.OrderProductModelsBean data;
    private String RepairExplain = "";
    private int REQUEST_CODE_SCAN = 100;
    private int size;
    private String videopath="";
    private ArrayList<Media> select;
    private SPUtils spUtils;
    private String EndVideo="";
    private String filename;
    private String endRemark="";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_complete_work_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("提交完结信息");
        spUtils = SPUtils.getInstance("token");
        //接收传进来的工单id
        orderID = getIntent().getStringExtra("OrderID");
        ProductID = getIntent().getStringExtra("prodID");

        TypeID = getIntent().getStringExtra("TypeID");//安装还是维修
        BarCodeIsNo = getIntent().getStringExtra("BarCodeIsNo");
        //产品
        data = (WorkOrder.OrderProductModelsBean) getIntent().getSerializableExtra("data");
        mTvId.setText("产品编号：" + data.getOrderProdcutID());
        mTvCate.setText("类别：" + data.getSubCategoryName());
        mTvBrand.setText("品牌：" + data.getBrandName());
        mTvProductType.setText("型号：" + data.getProdModelName());
        mTvGg.setText("规格：" + data.getProductType());
        mTvMemo.setText("服务要求：" + data.getMemo());

        if ("2".equals(TypeID)) {//安装
            mLlReturnInformation.setVisibility(View.GONE);
            mLlServiceProcess.setVisibility(View.VISIBLE);
            mLlResult.setVisibility(View.GONE);
        } else {
            mLlReturnInformation.setVisibility(View.VISIBLE);
            mLlServiceProcess.setVisibility(View.GONE);
            mLlResult.setVisibility(View.VISIBLE);
        }
        if ("1".equals(BarCodeIsNo)) {
            mLlCode.setVisibility(View.VISIBLE);
            mLlCode1.setVisibility(View.VISIBLE);
        } else {
            mLlCode.setVisibility(View.GONE);
            mLlCode1.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlViewExample.setOnClickListener(this);
        mIvBarCode.setOnClickListener(this);
        mIvMachine.setOnClickListener(this);
        mIvFaultLocation.setOnClickListener(this);
        mIvNewAndOldAccessories.setOnClickListener(this);
        mLlScan.setOnClickListener(this);
        mLlScan1.setOnClickListener(this);
        mIvOne.setOnClickListener(this);
        mIvTwo.setOnClickListener(this);
        mIvThree.setOnClickListener(this);
        mIvFour.setOnClickListener(this);
        mBtnCompleteSubmit.setOnClickListener(this);
        mLlViewExampleTwo.setOnClickListener(this);
        mIvVideo.setOnClickListener(this);
        mIvDelete.setOnClickListener(this);
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

    }

    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    /*提交返件图片*/
    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {

    }


    @Override//新版完结
    public void End(EndResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode() == 0) {
                    MyUtils.showToast(mActivity, "提交成功");
                    EventBus.getDefault().post(21);
                    finish();
                } else {
                    MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
            default:
                MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                break;
        }
        hideProgress();
    }

    @Override
    public void FinishOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    /*上传维修图片*/


    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddbarCode(BaseResult<Data<String>> baseResult) {

    }
    void goPreviewActivity() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(videopath), "video/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                Glide.with(mActivity).load(R.mipmap.add).into(mIvVideo);
                videopath="";
                mIvShipin.setVisibility(View.GONE);
                mIvDelete.setVisibility(View.GONE);
                break;
            case R.id.iv_video:
                if (mIvShipin.getVisibility()==View.VISIBLE){
                    goPreviewActivity();
                }else{
                    if (requestPermissions()) {
                        showVideoPopupWindow(901, 909);
                    } else {
                        requestPermissions(permissions.toArray(new String[permissions.size()]), 10009);
                    }
                }
                break;
            case R.id.iv_back:
                CompleteWorkOrderActivity.this.finish();
                break;
            case R.id.ll_view_example_two:
            case R.id.ll_view_example:
                final ViewExampleDialog viewExampleDialog = new ViewExampleDialog(mActivity);
                viewExampleDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

                viewExampleDialog.setNoOnclickListener("取消", new ViewExampleDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        viewExampleDialog.dismiss();
                    }
                });
                viewExampleDialog.show();
                Window window = viewExampleDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                Display d = window.getWindowManager().getDefaultDisplay();
                wlp.height = d.getHeight();
                wlp.width = d.getWidth();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                break;
            case R.id.iv_bar_code:
                showPopupWindow(101, 102);
                break;
            case R.id.iv_machine:
                showPopupWindow(201, 202);
                break;
            case R.id.iv_fault_location:
                showPopupWindow(301, 303);
                break;
            case R.id.iv_new_and_old_accessories:
                showPopupWindow(401, 404);
                break;
            case R.id.iv_one:
                showPopupWindow(501, 505);
                break;
            case R.id.iv_two:
                showPopupWindow(601, 606);
                break;
            case R.id.iv_three:
                showPopupWindow(701, 707);
                break;
            case R.id.iv_four:
                showPopupWindow(801, 808);
                break;
            case R.id.ll_scan1:
            case R.id.ll_scan:
                if (requestPermissions()) {
                    scan();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10003);
                }
                break;

            case R.id.btn_complete_submit:
                showProgress();
//                compressVideo(videopath);
                submit();
                break;
        }
    }

    private void submit() {
        if ("2".equals(TypeID)) {//安装
            barCode = mEtSingleNumber1.getText().toString();
            if ("1".equals(BarCodeIsNo)) {
                if (barCode.isEmpty()) {
                    MyUtils.showToast(mActivity, "请填写条形码");
                    hideProgress();
                    return;
                }
            }
            if (service_img_map.get(0) == null || service_img_map.get(1) == null) {
                MyUtils.showToast(mActivity, "请上传安装前照片以及安装后一张照片");
                hideProgress();
            } else {
                endRemark = mEtMemo.getText().toString();
                if ("".equals(videopath)){
                    ReuturnAccessoryPicUpload(service_img_map);
                }else{
                    if ("".equals(EndVideo)){
                        compressVideo(videopath);
                    }else{
                        ReuturnAccessoryPicUpload(service_img_map);
                    }
                }
            }

        } else {//维修
            barCode = mEtSingleNumber.getText().toString();
            if ("1".equals(BarCodeIsNo)) {
                if (barCode.isEmpty()) {
                    MyUtils.showToast(mActivity, "请填写条形码");
                    hideProgress();
                    return;
                }
            }
            if (return_img_map.get(0) == null) {
                MyUtils.showToast(mActivity, "整机图片必传！");
                hideProgress();
                return;
            } else {
                endRemark = mEtMemo.getText().toString();
                RepairExplain = mEtResult.getText().toString();
                if ("".equals(videopath)){
                    ReuturnAccessoryPicUpload(return_img_map);
                }else{
                    if ("".equals(EndVideo)){
                        compressVideo(videopath);
                    }else{
                        ReuturnAccessoryPicUpload(return_img_map);
                    }
                }
            }
        }
    }

    void pickerVideo(int code) {
        Intent intent = new Intent(mActivity, PickerActivity.class);
        intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_VIDEO);//default image and video (Optional)
        long maxSize = 60*1024*1024L;//long long long
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 1);  //default 40 (Optional)
//        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,select); // (Optional)
        startActivityForResult(intent, code);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scan() {
//        IntentIntegrator integrator = new IntentIntegrator(CompleteWorkOrderActivity.this);
//        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
//        integrator.setPrompt("请扫描条形码"); //底部的提示文字，设为""可以置空
//        integrator.setCameraId(0); //前置或者后置摄像头
//        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
//        integrator.setBarcodeImageEnabled(true);
//        integrator.initiateScan();
        Intent intent = new Intent(mActivity, CaptureActivity.class);
        /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

        //ZxingConfig config = new ZxingConfig();
        //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        //config.setPlayBeep(true);//是否播放提示音
        //config.setShake(true);//是否震动
        //config.setShowAlbum(true);//是否显示相册
        //config.setShowFlashLight(true);//是否显示闪光灯
        //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    /**
     * 拍摄视频或选视频
     */
    public void showVideoPopupWindow(final int code1, final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                openVideo(code1);
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                pickerVideo(code2);
                mPopupWindow.dismiss();
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
//            popupWindow.showAsDropDown(tv, 0, 10);
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    public void openVideo(int code) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        String f = System.currentTimeMillis() + ".mp4";
        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
        videopath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
        File dirfile = new File(fileDir);
        if (!dirfile.exists()) {
            dirfile.mkdirs();
        }
        File file = new File(videopath);
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".fileprovider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 60*1024*1024L);//限制录制大小(10M=10 * 1024 * 1024L)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);//限制录制时间(10秒=10)


        startActivityForResult(intent, code);
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
                if (requestPermissions()) {
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
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
//                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                    i.setType("image/*");
//                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    Matisse.from(CompleteWorkOrderActivity.this)
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
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }

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
//            popupWindow.showAsDropDown(tv, 0, 10);
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
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
            if (mActivity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
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
    @RequiresApi(api = Build.VERSION_CODES.M)
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
            case 10003:
                if (size == grantResults.length) {//允许
                    scan();
                } else {//拒绝
                    toSetting();
                }
                break;
            case 10004:
                if (size == grantResults.length) {//允许
                    pickerVideo(909);
                } else {//拒绝
                    toSetting();
                }
                break;
            case 10009:
                if (size == grantResults.length) {//允许
                    showVideoPopupWindow(901, 909);
                } else {//拒绝
                    toSetting();
                }
                break;
            default:
                break;

        }
    }

    //设置权限
    private void toSetting() {

        new AlertDialog.Builder(this).setTitle("相机和存储权限未授权")
                .setMessage("是否前往设置权限？")
                //  取消选项
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                //  确认选项
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到手机原生设置页面
                        MyUtils.toSelfSetting(mActivity);
                    }
                })
                .setCancelable(false)
                .show();
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (scanResult != null) {
//            String result = scanResult.getContents();
//            if (result == null) {
//                return;
//            } else {
//                mEtSingleNumber.setText(result);
//                mEtSingleNumber1.setText(result);
//            }
//
//        }
        if (mLlCode.getVisibility() == View.VISIBLE) {
            mEtSingleNumber.setEnabled(true);
        }
        if (mLlCode1.getVisibility() == View.VISIBLE) {
            mEtSingleNumber1.setEnabled(true);
        }
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {

            if (data != null) {
                String result = data.getStringExtra(Constant.CODED_CONTENT);
                mEtSingleNumber.setText(result);
                mEtSingleNumber1.setText(result);
            }
        }

        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvBarCode);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(1, newFile);
                }

                break;
            //相册
            case 102:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvBarCode);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(1, newFile);
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvMachine);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(0, newFile);
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
                    Glide.with(mActivity).load(uri).into(mIvMachine);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(0, newFile);
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvFaultLocation);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(2, newFile);
                }
                break;
            //相册
            case 303:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvFaultLocation);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(2, newFile);
                }
                break;
            //拍照
            case 401:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvNewAndOldAccessories);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(3, newFile);
                }
                break;
            //相册
            case 404:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvNewAndOldAccessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(3, newFile);
                }
                break;

            /*服务过程图片*/
            //拍照
            case 501:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvOne);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(0, newFile);
                }
                break;
            //相册
            case 505:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvOne);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {


                    Log.d("未压缩图片的大小", String.valueOf(file.length()));

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(0, newFile);
                    Log.d("已压缩图片的大小", String.valueOf(newFile.length()));


                }
                break;
            //拍照
            case 601:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvTwo);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(1, newFile);
                }
                break;
            //相册
            case 606:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvTwo);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(1, newFile);
                }
                break;
            //拍照
            case 701:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvThree);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(2, newFile);
                }
                break;
            //相册
            case 707:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvThree);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(2, newFile);
                }
                break;
            //拍照
            case 801:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvFour);
                    file = new File(FilePath);
                    MyUtils.sendBroadcastToImg(mActivity,file);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(3, newFile);
                }
                break;
            //相册
            case 808:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvFour);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(3, newFile);
                }
                break;
            //拍摄视频
            case 901:
                if (resultCode == -1) {
                    if (data != null) {
                        Glide.with(mActivity).load(videopath).into(mIvVideo);
                        file = new File(videopath);
                        mIvShipin.setVisibility(View.VISIBLE);
                        mIvDelete.setVisibility(View.VISIBLE);
                    }
                }
//                if (file != null) {
//                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
//                    videopath=newFile.getPath();
//                }
                break;
            //选择视频
            case 909:
                if (resultCode == PickerConfig.RESULT_CODE) {
                    select =((ArrayList) data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT));
                    ArrayList<String> list = new ArrayList<>();
                    Log.i("select", "select.size" + select.size());
                    for (Media media : select) {
                        list.add(media.path);
                        Glide.with(mActivity).load(media.path).into(mIvVideo);
                        videopath=media.path;
//                        file = new File(videopath);
//                        if (file != null) {
//                            File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
//                            videopath=newFile.getPath();
//                        }
                        mIvShipin.setVisibility(View.VISIBLE);
                        mIvDelete.setVisibility(View.VISIBLE);
                    }
                }
                break;


        }

    }

    public void ReuturnAccessoryPicUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map.get(0) != null) {
            builder.addFormDataPart("img1", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        }
        if (map.get(1) != null) {
            builder.addFormDataPart("img2", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        }
        if (map.get(2) != null) {
            builder.addFormDataPart("img3", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        }
        if (map.get(3) != null) {
            builder.addFormDataPart("img4", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        }
        builder.addFormDataPart("OrderID", orderID);
        builder.addFormDataPart("RepairExplain", RepairExplain);
        builder.addFormDataPart("EndRemark", endRemark);
        builder.addFormDataPart("EndVideo", EndVideo);
        builder.addFormDataPart("ProductID", ProductID);
        builder.addFormDataPart("BarCode", barCode);
        Log.d(TAG, builder.toString());
        MultipartBody requestBody = builder.build();
        mPresenter.End(requestBody);

    }

    /*安装服务图片*/
    public void ServiceOrderPicUpload(HashMap<Integer, File> map, String EndRemark) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map.get(0) != null) {
            builder.addFormDataPart("img1", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        }
        if (map.get(1) != null) {
            builder.addFormDataPart("img2", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        }
        if (map.get(2) != null) {
            builder.addFormDataPart("img3", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        }
        if (map.get(3) != null) {
            builder.addFormDataPart("img4", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        }
        builder.addFormDataPart("OrderID", orderID);
        builder.addFormDataPart("EndRemark", EndRemark);
        MultipartBody requestBody = builder.build();
//        mPresenter.ServiceOrderPicUpload(requestBody);
        mPresenter.ReuturnAccessoryPicUpload(requestBody);
    }

    private void getHost(String filePath) {
        //接口
        String path = Config.BASE_URL + "Oss/GetPolicyToken";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        final Request request = new Request.Builder()
                .addHeader("adminToken", spUtils.getString("adminToken"))
                .addHeader("userName", spUtils.getString("userName"))
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hideProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson=new Gson();
                PolicyToken policyToken=gson.fromJson(str.replaceAll(" ",""),PolicyToken.class);
                uploadVideo(policyToken.getData(),filePath);
            }
        });

    }
    public String getVideoOutCompressPath(){
        String f = System.currentTimeMillis() + ".mp4";
        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
        String videoCompressPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
        File dirfile = new File(fileDir);
        if (!dirfile.exists()) {
            dirfile.mkdirs();
        }
        return videoCompressPath;
    }
    private void uploadVideo(PolicyToken.DataBean policyToken,String filePath) {
        File file=new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("OSSAccessKeyId", policyToken.getAccessid());
        builder.addFormDataPart("policy", policyToken.getPolicy());
        builder.addFormDataPart("Signature", policyToken.getSignature());
        filename = policyToken.getFileName() + filePath.substring(filePath.lastIndexOf("."));
        builder.addFormDataPart("name", filename);
        builder.addFormDataPart("key", "Video/OrderEnd/${filename}");
        builder.addFormDataPart("success_action_status", "201");
        builder.addFormDataPart("file", filename, RequestBody.create(MediaType.parse("application/octet-stream"), file));
        MultipartBody requestBody = builder.build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        final Request request = new Request.Builder()
                .url(policyToken.getHost())
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hideProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                System.out.println("上传结果："+str);
                if (response.code()==201){
                    EndVideo=filename;
                    if ("2".equals(TypeID)) {//安装
                        ReuturnAccessoryPicUpload(service_img_map);
                    }else{
                        ReuturnAccessoryPicUpload(return_img_map);
                    }

                }
            }
        });

    }
//    压缩视频
    private void compressVideo(String videoPath){
//        mBinding.progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(videoPath);
                    Log.e("压缩前大小==", FileSizeUtil.getAutoFileOrFilesSize(videoPath));
                    int originWidth = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                    int originHeight = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                    int bitrate = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE));
                    Log.e("video","originWidth="+originWidth+" originHeight=="+originHeight+" bitrate=="+bitrate);
                    String videoOutCompressPath = getVideoOutCompressPath();
                    VideoProcessor.processor(mActivity)
                            .input(videoPath)
                            .bitrate(bitrate / 8)
                            .output(videoOutCompressPath)
                            .progressListener(new VideoProgressListener() {
                                @Override
                                public void onProgress(float progress) {
                                    int intProgress = (int) (progress * 100);
                                    Message message = mHandler.obtainMessage();
                                    message.what=0;
                                    message.arg1 = intProgress;
                                    mHandler.sendMessage(message);
                                    if (intProgress==100){
                                        message.what=1;
                                        message.obj = videoOutCompressPath;
                                        mHandler.sendMessage(message);
                                    }

                                }
                            })
                            .process();
                } catch (Exception e) {
                    e.printStackTrace();
                    hideProgress();
                }
            }
        }).start();
    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    mBinding.progressBar.setProgress(msg.arg1);
                    break;
                case 1:
//                    mBinding.progressBar.setVisibility(View.INVISIBLE);
//                    ToastUtil.showLong("压缩完成！");
                    String videoOutCompressPath  = (String) msg.obj;
                    getHost(videoOutCompressPath);
                    Log.e("压缩后大小==", FileSizeUtil.getAutoFileOrFilesSize(videoOutCompressPath));
                    hideProgress();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

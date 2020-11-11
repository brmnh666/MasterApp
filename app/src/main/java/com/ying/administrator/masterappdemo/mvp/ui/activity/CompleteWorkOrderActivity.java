package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.PreviewActivity;
import com.dmcbig.mediapicker.TakePhotoActivity;
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
import com.ying.administrator.masterappdemo.mvp.ui.adapter.PicAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.FileSizeUtil;
import com.ying.administrator.masterappdemo.util.imageutil.ImageCompress;
import com.ying.administrator.masterappdemo.v3.bean.EndConfig;
import com.ying.administrator.masterappdemo.v3.bean.EndResult;
import com.ying.administrator.masterappdemo.v3.bean.PolicyToken;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
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
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.rv_icons)
    RecyclerView mRvIcons;
    @BindView(R.id.ll_img)
    LinearLayout mLlImg;
    @BindView(R.id.iv_video)
    ImageView mIvVideo;
    @BindView(R.id.iv_shipin)
    ImageView mIvShipin;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.ll_video)
    LinearLayout mLlVideo;
    @BindView(R.id.et_result)
    EditText mEtResult;
    @BindView(R.id.ll_result)
    LinearLayout mLlResult;
    @BindView(R.id.et_memo)
    EditText mEtMemo;
    @BindView(R.id.btn_complete_submit)
    Button mBtnCompleteSubmit;
    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;


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
    private String videopath = "";
    private ArrayList<Media> select = new ArrayList<>();
    private SPUtils spUtils;
    private String EndVideo = "";
    private String filename;
    private String endRemark = "";
    private List<String> piclist = new ArrayList<>();
    private List<String> selectpiclist = new ArrayList<>();
    private PicAdapter picAdapter;
    private OSSPlainTextAKSKCredentialProvider credentialProvider;

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

        EndConfig config1=new EndConfig("条形码",true,true,false);
        EndConfig config2=new EndConfig("整机",true,false,false);
        EndConfig config3=new EndConfig("故障位置",true,false,false);
        EndConfig config4=new EndConfig("购机凭证",true,false,false);
        EndConfig config5=new EndConfig("新旧配件",true,false,false);
        EndConfig config6=new EndConfig("试机视频",false,false,true);
        List<EndConfig> list=new ArrayList<>();
        list.add(config1);
        list.add(config2);
        list.add(config3);
        list.add(config4);
        list.add(config5);
        list.add(config6);
        for (int i = 0; i < list.size(); i++) {
            EndConfig config=list.get(i);
            View view_img=LayoutInflater.from(mActivity).inflate(R.layout.layout_img,null);
            View view_text=LayoutInflater.from(mActivity).inflate(R.layout.layout_text,null);
            View view_video=LayoutInflater.from(mActivity).inflate(R.layout.layout_video,null);
            TextView imgName=view_img.findViewById(R.id.tv_name);
            TextView textName=view_img.findViewById(R.id.tv_name);
            TextView videoName=view_img.findViewById(R.id.tv_name);
            imgName.setText(config.getName());
            textName.setText(config.getName());
            videoName.setText(config.getName());
            if (config.isImgOn()) {
                mLlParent.addView(view_img);
            }
            if (config.isTextOn()) {
                mLlParent.addView(view_text);
            }
            if (config.isVideoOn()) {
                mLlParent.addView(view_video);
            }
        }


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
            mLlResult.setVisibility(View.GONE);
        } else {
            mLlResult.setVisibility(View.VISIBLE);
        }
        if ("1".equals(BarCodeIsNo)) {
            mLlCode.setVisibility(View.VISIBLE);
        } else {
            mLlCode.setVisibility(View.GONE);
        }

        piclist.add("add");
        picAdapter = new PicAdapter(R.layout.item_picture, piclist);
        mRvIcons.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvIcons.setAdapter(picAdapter);
        picAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img:
                        if ("add".equals(adapter.getItem(position))) {
                            if (requestPermissions()) {
                                showPopupWindow();
                            } else {
                                requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                            }
                        } else {
                            goPreviewActivity_img();
                        }
                        break;
                    case R.id.ll_delete:
                        select.remove(position);
                        ArrayList<String> list = new ArrayList<>();
                        Log.i("select", "select.size" + select.size());
                        for (Media media : select) {
                            list.add(media.path);
                            Log.i("media", media.path);
                            Log.e("media", "s:" + media.size);
                        }
                        loadAdpater(list);
                        break;
                }
            }
        });
    }

    private void loadAdpater(ArrayList<String> paths) {
        piclist.clear();
        piclist.addAll(paths);
        selectpiclist.clear();
        for (int i = 0; i < paths.size(); i++) {
            selectpiclist.add(ImageCompress.compressImage(paths.get(i), Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg", 80));
        }
        if (piclist.size() != 9) {
            piclist.add("add");
        }
        System.out.println(selectpiclist);
        picAdapter.setNewData(piclist);
    }

    void goImage() {
        Intent intent = new Intent(mActivity, PickerActivity.class);
        intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_IMAGE);//default image and video (Optional)
        long maxSize = 188743680L;//long long long
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
        //旧件照片数量最多为配件数量+5
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 9 - piclist.size() + 1);  //default 40 (Optional)
//        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,select); // (Optional)
        startActivityForResult(intent, 200);
    }

    void goPreviewActivity_img() {
        Intent intent = new Intent(mActivity, PreviewActivity.class);
        intent.putExtra(PickerConfig.PRE_RAW_LIST, select);//default image and video (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, select.size());//default image and video (Optional)
        startActivityForResult(intent, 200);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);

        mLlScan.setOnClickListener(this);

        mBtnCompleteSubmit.setOnClickListener(this);

        mIvVideo.setOnClickListener(this);
        mIvDelete.setOnClickListener(this);
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

    }

    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

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


    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddbarCode(BaseResult<Data<String>> baseResult) {

    }

    void goPreviewActivity_video() {
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
                videopath = "";
                mIvShipin.setVisibility(View.GONE);
                mIvDelete.setVisibility(View.GONE);
                break;
            case R.id.iv_video:
                if (mIvShipin.getVisibility() == View.VISIBLE) {
                    goPreviewActivity_video();
                } else {
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

    private void uploadToOSS(String filePath) {
        String bucketName = "xigyubuckettest";
        String AK = "LTAI4G4kcRFjMF4xARFULpSD";
        String SK = "QxW3J8hn4VfpSLmDATeN2XF1J0oRtk";
        // 获取文件的后缀名
        String suffixName = filePath.substring(filePath.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = "APP/EndPic/" + orderID + "/" + finalFileName;

        credentialProvider = new OSSPlainTextAKSKCredentialProvider(AK, SK);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), "https://oss-cn-hangzhou.aliyuncs.com", credentialProvider, conf);
        OSSLog.enableLog();
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(bucketName, objectName, filePath);

// 异步上传时可以设置进度回调。
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String url = oss.presignPublicObjectURL(bucketName, objectName);
                Log.d("访问地址：", url);
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
// task.cancel(); // 可以取消任务。
// task.waitUntilFinished(); // 等待任务完成。
    }

    private void submit() {
        if ("2".equals(TypeID)) {//安装
            barCode = mEtSingleNumber.getText().toString();
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
                if ("".equals(videopath)) {
                    ReuturnAccessoryPicUpload(service_img_map);
                } else {
                    if ("".equals(EndVideo)) {
                        compressVideo(videopath);
                    } else {
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
                if ("".equals(videopath)) {
                    ReuturnAccessoryPicUpload(return_img_map);
                } else {
                    if ("".equals(EndVideo)) {
                        compressVideo(videopath);
                    } else {
                        ReuturnAccessoryPicUpload(return_img_map);
                    }
                }
            }
        }
    }

    void pickerVideo(int code) {
        Intent intent = new Intent(mActivity, PickerActivity.class);
        intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_VIDEO);//default image and video (Optional)
        long maxSize = 60 * 1024 * 1024L;//long long long
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
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 60 * 1024 * 1024L);//限制录制大小(10M=10 * 1024 * 1024L)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);//限制录制时间(10秒=10)


        startActivityForResult(intent, code);
    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mActivity, TakePhotoActivity.class), 200);
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                goImage();
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
            case 10001:
                if (size == grantResults.length) {//允许
                    showPopupWindow();
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
        if (mLlCode.getVisibility() == View.VISIBLE) {
            mEtSingleNumber.setEnabled(true);
        }
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {

            if (data != null) {
                String result = data.getStringExtra(Constant.CODED_CONTENT);
                mEtSingleNumber.setText(result);
            }
        }

        File file = null;
        switch (requestCode) {
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
                break;
            //选择视频
            case 909:
                if (resultCode == PickerConfig.RESULT_CODE) {
                    select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                    ArrayList<String> list = new ArrayList<>();
                    Log.i("select", "select.size" + select.size());
                    for (Media media : select) {
                        list.add(media.path);
                        Glide.with(mActivity).load(media.path).into(mIvVideo);
                        videopath = media.path;
                        uploadToOSS(videopath);
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
        if (requestCode == 200 && data != null) {
            select.addAll(data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT));
            ArrayList<String> list = new ArrayList<>();
            Log.i("select", "select.size" + select.size());
            for (Media media : select) {
                if (media.size != 0) {
                    uploadToOSS(media.path);
                    list.add(media.path);
                    Log.i("media", media.path);
                    Log.e("media", "s:" + media.size);
                } else {
                    select.remove(media);
                }
            }
            loadAdpater(list);
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
                Gson gson = new Gson();
                PolicyToken policyToken = gson.fromJson(str.replaceAll(" ", ""), PolicyToken.class);
                uploadVideo(policyToken.getData(), filePath);
            }
        });

    }

    public String getVideoOutCompressPath() {
        String f = System.currentTimeMillis() + ".mp4";
        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
        String videoCompressPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
        File dirfile = new File(fileDir);
        if (!dirfile.exists()) {
            dirfile.mkdirs();
        }
        return videoCompressPath;
    }

    private void uploadVideo(PolicyToken.DataBean policyToken, String filePath) {
        File file = new File(filePath);
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
                System.out.println("上传结果：" + str);
                if (response.code() == 201) {
                    EndVideo = filename;
                    if ("2".equals(TypeID)) {//安装
                        ReuturnAccessoryPicUpload(service_img_map);
                    } else {
                        ReuturnAccessoryPicUpload(return_img_map);
                    }

                }
            }
        });

    }

    //    压缩视频
    private void compressVideo(String videoPath) {
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
                    Log.e("video", "originWidth=" + originWidth + " originHeight==" + originHeight + " bitrate==" + bitrate);
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
                                    message.what = 0;
                                    message.arg1 = intProgress;
                                    mHandler.sendMessage(message);
                                    if (intProgress == 100) {
                                        message.what = 1;
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
                    String videoOutCompressPath = (String) msg.obj;
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

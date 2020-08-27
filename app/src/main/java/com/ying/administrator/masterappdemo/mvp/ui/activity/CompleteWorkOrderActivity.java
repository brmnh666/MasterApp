package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ying.administrator.masterappdemo.BuildConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.CompleteWorkOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CompleteWorkOrderPresenter;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.v3.bean.EndResult;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;

    private HashMap<Integer, File> return_img_map = new HashMap<>();//上传的返件图片
    private HashMap<Integer, File> service_img_map = new HashMap<>();//上传安装图片


    ZLoadingDialog dialog = new ZLoadingDialog(this);
    private List<Uri> mSelected;
    private Uri uri;
    private String barCode;
    private String ProductID;
    private String orderID;
    private String TypeID;
    private String BarCodeIsNo;
    private WorkOrder.OrderProductModelsBean data;

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
        //接收传进来的工单id
        orderID = getIntent().getStringExtra("OrderID");
        ProductID = getIntent().getStringExtra("prodID");

        TypeID = getIntent().getStringExtra("TypeID");//安装还是维修
        BarCodeIsNo = getIntent().getStringExtra("BarCodeIsNo");
        //产品
        data = (WorkOrder.OrderProductModelsBean) getIntent().getSerializableExtra("data");
        mTvId.setText("产品编号："+data.getOrderProdcutID());
        mTvCate.setText("类别："+data.getSubCategoryName());
        mTvBrand.setText("品牌："+data.getBrandName());
        mTvProductType.setText("型号："+data.getProdModelName());
        mTvGg.setText("规格："+data.getProductType());
        mTvMemo.setText("服务要求："+data.getMemo());

        if ("1".equals(TypeID)) {//维修
            mLlReturnInformation.setVisibility(View.VISIBLE);
            mLlServiceProcess.setVisibility(View.GONE);
        } else if ("2".equals(TypeID)) {
            mLlReturnInformation.setVisibility(View.GONE);
            mLlServiceProcess.setVisibility(View.VISIBLE);
        } else {
            mLlReturnInformation.setVisibility(View.VISIBLE);
            mLlServiceProcess.setVisibility(View.GONE);
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
        cancleLoading();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                IntentIntegrator integrator = new IntentIntegrator(CompleteWorkOrderActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描条形码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;

            case R.id.btn_complete_submit:
                if ("2".equals(TypeID)) {//安装
                    barCode = mEtSingleNumber1.getText().toString();
                    if ("1".equals(BarCodeIsNo)) {
                        if (barCode.isEmpty()) {
                            ToastUtils.showShort("请填写条形码");
                            return;
                        }
                    }
                    if (service_img_map.get(0) == null || service_img_map.get(1) == null) {
                        MyUtils.showToast(mActivity, "请上传安装前照片以及安装后一张照片");
                    } else {
                        String EndRemark = mEtMemo.getText().toString();
                        ReuturnAccessoryPicUpload(service_img_map, EndRemark);
                    }

                } else {//维修
                    barCode = mEtSingleNumber.getText().toString();
                    if ("1".equals(BarCodeIsNo)) {
                        if (barCode.isEmpty()) {
                            ToastUtils.showShort("请填写条形码");
                            return;
                        }
                    }
                    if (return_img_map.get(0) == null) {
                        MyUtils.showToast(mActivity, "整机图片必传！");
                        return;
                    } else {
                        String EndRemark = mEtMemo.getText().toString();
                        ReuturnAccessoryPicUpload(return_img_map, EndRemark);
                    }
                }
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
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            if (result == null) {
                return;
            } else {
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


        }

    }

    public void ReuturnAccessoryPicUpload(HashMap<Integer, File> map, String EndRemark) {
        showLoading();
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
        builder.addFormDataPart("ProductID", ProductID);
        builder.addFormDataPart("BarCode", barCode);
        Log.d(TAG, builder.toString());
        MultipartBody requestBody = builder.build();
        mPresenter.End(requestBody);

    }

    /*安装服务图片*/
    public void ServiceOrderPicUpload(HashMap<Integer, File> map, String EndRemark) {
        showLoading();
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

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("提交中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

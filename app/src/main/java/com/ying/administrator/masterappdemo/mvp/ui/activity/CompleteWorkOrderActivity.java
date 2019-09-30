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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
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
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_work_order_status)
    TextView mTvWorkOrderStatus;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_reason_pending_appointment)
    TextView mTvReasonPendingAppointment;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_service)
    TextView mTvService;
    @BindView(R.id.tv_service_goods)
    TextView mTvServiceGoods;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_service_address)
    TextView mTvServiceAddress;
    @BindView(R.id.et_single_number)
    EditText mEtSingleNumber;
    @BindView(R.id.et_express_name)
    EditText mEtExpressName;
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_view_example)
    LinearLayout mLlViewExample;
    @BindView(R.id.iv_bar_code)
    ImageView mIvBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.iv_machine)
    ImageView mIvMachine;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
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
    @BindView(R.id.btn_complete_submit)
    Button mBtnCompleteSubmit;
    @BindView(R.id.et_memo)
    EditText mEtMemo;
    private String orderID;

    /*工单详情*/
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;

  /*  private ArrayList<ReturnaccessoryImg> return_img = new ArrayList<>();//返件图片
    private ArrayList<OrderImg> service_img = new ArrayList<>();//服务过程图片*/

    private HashMap<Integer, File> return_img_map = new HashMap<>();//上传的返件图片
    private HashMap<Integer, File> service_img_map = new HashMap<>();//上传安装图片


    ZLoadingDialog dialog = new ZLoadingDialog(this);
    private List<Uri> mSelected;
    private Uri uri;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_complete_work_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("完成工单");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);

        //接收传进来的工单id
        orderID = getIntent().getStringExtra("OrderID");

        mPresenter.GetOrderInfo(orderID);

        // mPresenter.GetReturnAccessoryByOrderID(orderID);

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mLlViewExample.setOnClickListener(this);
        mIvBarCode.setOnClickListener(this);
        mIvMachine.setOnClickListener(this);
        mIvFaultLocation.setOnClickListener(this);
        mIvNewAndOldAccessories.setOnClickListener(this);
        mLlScan.setOnClickListener(this);
        mIvOne.setOnClickListener(this);
        mIvTwo.setOnClickListener(this);
        mIvThree.setOnClickListener(this);
        mIvFour.setOnClickListener(this);
        mBtnCompleteSubmit.setOnClickListener(this);
        mLlViewExampleTwo.setOnClickListener(this);

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mTvWorkOrderStatus.setText(data.getStateStr());
                mTvPaymentMethod.setText(data.getOrderPayStr());
                mTvNumber.setText(data.getNum());

                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvOrderTime.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                if (("Y").equals(data.getGuarantee())) {
                    mTvPaymentMethod.setText("平台代付");
                } else {
                    mTvPaymentMethod.setText("客户付款");
                }
                mTvReasonPendingAppointment.setText(data.getMemo());
                mTvServiceGoods.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                if ("1".equals(data.getTypeID())) {//维修
                    mTvService.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvService.setBackgroundResource(R.color.color_custom_01);
                    mLlReturnInformation.setVisibility(View.VISIBLE);
                    mLlServiceProcess.setVisibility(View.GONE);

                } else if ("2".equals(data.getTypeID())) {
                    mTvService.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvService.setBackgroundResource(R.color.color_custom_04);
                    mLlReturnInformation.setVisibility(View.GONE);
                    mLlServiceProcess.setVisibility(View.VISIBLE);

                } else {
                    mTvService.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvService.setBackgroundResource(R.color.color_custom_01);
                    mLlReturnInformation.setVisibility(View.VISIBLE);
                    mLlServiceProcess.setVisibility(View.GONE);
                }
                mTvAddress.setText(data.getAddress());
                mTvName.setText(data.getUserName());
                mTvPhone.setText(data.getPhone());


                break;

            default:
                Log.d("detail", baseResult.getData().toString());
                //  data=null;
                break;
        }


    }

    /*添加服务过程的图片*/
    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {//图片上传成功
//                    if ("0".equals(data.getAccessorySearchState())){
//                        mPresenter.UpdateOrderState(orderID, "7");
//                    }else{
//                        mPresenter.UpdateOrderState(orderID, "5");
//                    }
                    mPresenter.UpdateOrderState(orderID, "5","");
                } else {
                    cancleLoading();
                }

                break;
        }
    }

    /*提交返件图片*/
    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {//图片上传成功
//                    if ("0".equals(data.getAccessorySearchState())){
//                        mPresenter.UpdateOrderState(orderID, "7");
//                    }else{

                    if (!"1".equals(data.getIsReturn())) {//不需要返件
                        mPresenter.UpdateOrderState(orderID, "5","");
                    } else {
                        mPresenter.UpdateOrderState(orderID, "8","");
                    }

//                    }

                } else {
                    cancleLoading();
                }

                break;
        }

    }


    @Override
    public void FinishOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    /*上传维修图片*/


    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    CompleteWorkOrderActivity.this.finish();
                    EventBus.getDefault().post("6");
                    EventBus.getDefault().post("8");
//                    EventBus.getDefault().post("");
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
                    if ("0".equals(data.getAccessorySearchState())) {
                        EventBus.getDefault().post(4);
                    } else {
                        if (!"1".equals(data.getIsReturn())) {//不需要返件
                            EventBus.getDefault().post(4);
                        } else {
                            EventBus.getDefault().post(3);
                        }
                    }
                }
                break;

        }
    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
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


            case R.id.ll_scan:
                IntentIntegrator integrator = new IntentIntegrator(CompleteWorkOrderActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;

            case R.id.btn_complete_submit:
                /*    if (return_img_map.size()<4||service_img_map.size()<4){
                        Toast.makeText(CompleteWorkOrderActivity.this,"请添加四张图片",Toast.LENGTH_SHORT).show();
                    }else {
                     if (return_img_map.size()>=4){

                         ReuturnAccessoryPicUpload(return_img_map);

                     }
                     if (service_img_map.size()>=4){

                         ServiceOrderPicUpload(service_img_map);
                     }

                    }*/
                if ("2".equals(data.getTypeID())) {//安装
                    if (service_img_map.get(0)==null||service_img_map.get(1)==null) {
                        MyUtils.showToast(mActivity, "请上传安装前照片以及安装后一张照片");
                        return;
                    } else {
                        String EndRemark=mEtMemo.getText().toString();
                        ServiceOrderPicUpload(service_img_map,EndRemark);
                    }

                } else {//维修
                    if (data.getAccessory() == null || "".equals(data.getAccessory())) {
                        //没配件
                        if (return_img_map.get(1) == null) {
                            MyUtils.showToast(mActivity, "整机图片必传！");
                            return;
                        }
                    } else {
                        //有配件
                        if (return_img_map.get(1) == null || return_img_map.get(2) == null || return_img_map.get(3) == null) {
                            MyUtils.showToast(mActivity, "整机、故障位置、新旧配件图片必传！");
                            return;
                        }
                    }
                    String EndRemark=mEtMemo.getText().toString();
                    ReuturnAccessoryPicUpload(return_img_map,EndRemark);
//                    if (return_img_map.size() < 4) {
//                        Toast.makeText(CompleteWorkOrderActivity.this, "请添加四张返件图片", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
////                        if (!mEtExpressName.getText().toString().equals("") && mEtSingleNumber.getText().toString().equals("")) {
////                            mPresenter.AddReturnAccessory(orderID, mEtSingleNumber.getText().toString() + mEtExpressName.getText().toString());
////                        }
//                        ReuturnAccessoryPicUpload(return_img_map);
//                    }
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
                        fileUri = FileProvider.getUriForFile(mActivity, "com.ying.administrator.masterappdemo.fileProvider", file);
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
                    return_img_map.put(0, newFile);
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
                    return_img_map.put(0, newFile);
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
                    return_img_map.put(1, newFile);
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
                    return_img_map.put(1, newFile);
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
        builder.addFormDataPart("EndRemark",EndRemark);
        MultipartBody requestBody = builder.build();
        mPresenter.ReuturnAccessoryPicUpload(requestBody);

    }

    /*安装服务图片*/
    public void ServiceOrderPicUpload(HashMap<Integer, File> map,String EndRemark ) {
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
        builder.addFormDataPart("EndRemark",EndRemark);
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

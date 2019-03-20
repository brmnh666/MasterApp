package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.OrderImg;
import com.ying.administrator.masterappdemo.entity.ReturnaccessoryImg;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.CompleteWorkOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CompleteWorkOrderPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.ShareDialog;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CompleteWorkOrderActivity extends BaseActivity<CompleteWorkOrderPresenter, CompleteWorkOrderModel> implements CompleteWorkOrderContract.View {
    private String orderID;
    private LinearLayout ll_return;
    private TextView tv_actionbar_title;
    private ImageView img_actionbar_message;
    private LinearLayout ll_view_example;
    private ImageView iv_bar_code;
    private ImageView iv_machine;
    private ImageView iv_fault_location;
    private ImageView iv_new_and_old_accessories;

    /*工单详情*/
    private TextView tv_order_time;//接单时间
    private TextView tv_work_order_number;//工单编号
    private TextView tv_reason_pending_appointment;//原因
    private TextView tv_service;//维修或安装
    private TextView tv_service_goods;//产品名称
    private TextView tv_service_address;//服务地址
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private LinearLayout ll_scan;
    private EditText et_single_number; //快递单号
    private EditText et_express_name;//快递公司名字

    private Button btn_complete_submit;

  /*  private ArrayList<ReturnaccessoryImg> return_img = new ArrayList<>();//返件图片
    private ArrayList<OrderImg> service_img = new ArrayList<>();//服务过程图片*/

    private HashMap<Integer,File> return_img_map=new HashMap<>();//上传的返件图片
    private HashMap<Integer,File> service_img_map=new HashMap<>();//上传安装图片



    private LinearLayout mll_return_information;
    private LinearLayout mll_service_process;

    /*服务过程图片*/
    private ImageView iv_one;
    private ImageView iv_two;
    private ImageView iv_three;
    private ImageView iv_four;


    ZLoadingDialog dialog = new ZLoadingDialog(this);
    @Override
    protected int setLayoutId() {
        return R.layout.activity_complete_work_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ll_return = findViewById(R.id.ll_return);
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        img_actionbar_message = findViewById(R.id.img_actionbar_message);
        tv_actionbar_title.setText("完成工单");
        img_actionbar_message.setVisibility(View.INVISIBLE);
        ll_view_example = findViewById(R.id.ll_view_example);
        iv_bar_code = findViewById(R.id.iv_bar_code);
        iv_machine = findViewById(R.id.iv_machine);
        iv_fault_location = findViewById(R.id.iv_fault_location);
        iv_new_and_old_accessories = findViewById(R.id.iv_new_and_old_accessories);
        ll_scan = findViewById(R.id.ll_scan);
        et_single_number = findViewById(R.id.et_single_number);
        et_express_name=findViewById(R.id.et_express_name);
        tv_order_time = findViewById(R.id.tv_order_time);
        tv_work_order_number = findViewById(R.id.tv_work_order_number);
        tv_reason_pending_appointment = findViewById(R.id.tv_reason_pending_appointment);
        tv_service = findViewById(R.id.tv_service);
        tv_service_goods = findViewById(R.id.tv_service_goods);
        tv_service_address = findViewById(R.id.tv_service_address);
        mll_return_information = findViewById(R.id.ll_return_information);
        mll_service_process = findViewById(R.id.ll_service_process);
        iv_one = findViewById(R.id.iv_one);
        iv_two = findViewById(R.id.iv_two);
        iv_three = findViewById(R.id.iv_three);
        iv_four = findViewById(R.id.iv_four);

        btn_complete_submit=findViewById(R.id.btn_complete_submit);
        //接收传进来的工单id
        orderID = getIntent().getStringExtra("OrderID");

        mPresenter.GetOrderInfo(orderID);

        // mPresenter.GetReturnAccessoryByOrderID(orderID);

    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickLister());
        ll_view_example.setOnClickListener(new CustomOnclickLister());
        iv_bar_code.setOnClickListener(new CustomOnclickLister());
        iv_machine.setOnClickListener(new CustomOnclickLister());
        iv_fault_location.setOnClickListener(new CustomOnclickLister());
        iv_new_and_old_accessories.setOnClickListener(new CustomOnclickLister());
        ll_scan.setOnClickListener(new CustomOnclickLister());
        iv_one.setOnClickListener(new CustomOnclickLister());
        iv_two.setOnClickListener(new CustomOnclickLister());
        iv_three.setOnClickListener(new CustomOnclickLister());
        iv_four.setOnClickListener(new CustomOnclickLister());
        btn_complete_submit.setOnClickListener(new CustomOnclickLister());

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                tv_work_order_number.setText(data.getOrderID());
                tv_order_time.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                tv_reason_pending_appointment.setText(data.getMemo());
                tv_service_goods.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                if (data.getTypeID() == 1) {//维修
                    tv_service.setText(data.getTypeName()+"/"+data.getGuaranteeText());
                    tv_service.setBackgroundResource(R.color.color_custom_01);
                    mll_return_information.setVisibility(View.VISIBLE);
                    mll_service_process.setVisibility(View.GONE);

                } else if (data.getTypeID() == 2){
                    tv_service.setText(data.getTypeName()+"/"+data.getGuaranteeText());
                    tv_service.setBackgroundResource(R.color.color_custom_04);
                    mll_return_information.setVisibility(View.GONE);
                    mll_service_process.setVisibility(View.VISIBLE);

                }else{
                    tv_service.setText(data.getTypeName()+"/"+data.getGuaranteeText());
                    tv_service.setBackgroundResource(R.color.color_custom_01);
                    mll_return_information.setVisibility(View.VISIBLE);
                    mll_service_process.setVisibility(View.GONE);
                }
                tv_service_address.setText(data.getAddress());






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
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){//图片上传成功
                    mPresenter.UpdateOrderState(orderID,"7");
                }else {
                    cancleLoading();
                }

                break;
        }
    }

    /*提交返件图片*/
    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){//图片上传成功
                   mPresenter.UpdateOrderState(orderID,"7");
                }else {
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
            if (baseResult.getData().isItem1()){
                CompleteWorkOrderActivity.this.finish();
                }
                            break;

        }
    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    public class CustomOnclickLister implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_return:
                    CompleteWorkOrderActivity.this.finish();
                    break;
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
                if (data.getTypeID()==2){//安装
                    if (service_img_map.size()<4){
                        Toast.makeText(CompleteWorkOrderActivity.this,"请添加四张服务图片",Toast.LENGTH_SHORT).show();
                    }else {


                        ServiceOrderPicUpload(service_img_map);
                    }

                }else {//维修

                    if (return_img_map.size()<4){
                        Toast.makeText(CompleteWorkOrderActivity.this,"请添加四张返件图片",Toast.LENGTH_SHORT).show();

                    }else {

                        if (!et_express_name.getText().toString().equals("")&&et_single_number.getText().toString().equals("")){
                            mPresenter.AddReturnAccessory(orderID,et_single_number.getText().toString()+et_express_name.getText().toString());
                        }
                        ReuturnAccessoryPicUpload(return_img_map);
                    }
                }




                    break;



            }
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
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "test"), code2);
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
            if (result==null){
                return;
            }else {
                et_single_number.setText(result);
            }

        }
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_bar_code);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(0,newFile);
                }

                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_bar_code);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(0,newFile);
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_machine);
                    file = new File(FilePath);
                }
                if (file!=null){

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(1,newFile);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_machine);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(1,newFile);
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_fault_location);
                    file = new File(FilePath);
                }
                if (file!=null){

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(2,newFile);
                }
                break;
            //相册
            case 303:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_fault_location);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(2,newFile);
                }
                break;
            //拍照
            case 401:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_new_and_old_accessories);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(3,newFile);
                }
                break;
            //相册
            case 404:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_new_and_old_accessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    return_img_map.put(3,newFile);
                }
                break;

            /*服务过程图片*/
            //拍照
            case 501:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_one);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(0,newFile);
                }
                break;
            //相册
            case 505:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_one);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){


                    Log.d("未压缩图片的大小", String.valueOf(file.length()));

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(0,newFile);
                    Log.d("已压缩图片的大小", String.valueOf(newFile.length()));




                }
                break;
            //拍照
            case 601:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_two);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(1,newFile);
                }
                break;
            //相册
            case 606:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_two);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(1,newFile);
                }
                break;
            //拍照
            case 701:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_three);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(2,newFile);
                }
                break;
            //相册
            case 707:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_three);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(2,newFile);
                }
                break;
            //拍照
            case 801:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_four);
                    file = new File(FilePath);
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(3,newFile);
                }
                break;
            //相册
            case 808:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_four);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    service_img_map.put(3,newFile);
                }
                break;


        }

    }

    public void ReuturnAccessoryPicUpload(HashMap<Integer, File> map) {
        showLoading();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("img", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        builder.addFormDataPart("img", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.ReuturnAccessoryPicUpload(requestBody);

    }

    /*安装服务图片*/
    public void ServiceOrderPicUpload(HashMap<Integer, File> map) {
        showLoading();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("img", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        builder.addFormDataPart("img", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.ServiceOrderPicUpload(requestBody);
    }

  public void showLoading(){
      dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
              .setLoadingColor(Color.BLACK)//颜色
              .setHintText("提交中请稍后...")
              .setHintTextSize(14) // 设置字体大小 dp
              .setHintTextColor(Color.BLACK)  // 设置字体颜色
              .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
              .setCanceledOnTouchOutside(false)//点击外部无法取消
              .show();
  }

  public void cancleLoading(){
      dialog.dismiss();

  }

}

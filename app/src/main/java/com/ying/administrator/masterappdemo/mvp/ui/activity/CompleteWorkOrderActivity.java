package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
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

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.CompleteWorkOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CompleteWorkOrderPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.ShareDialog;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;

import java.io.File;
import java.util.ArrayList;

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
    private WorkOrder.DataBean data=new WorkOrder.DataBean();

    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private LinearLayout ll_scan;
    private EditText et_single_number;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//
//
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_complete_work_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ll_return=findViewById(R.id.ll_return);
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        img_actionbar_message=findViewById(R.id.img_actionbar_message);
        tv_actionbar_title.setText("完成工单");
        img_actionbar_message.setVisibility(View.INVISIBLE);
        ll_view_example = findViewById(R.id.ll_view_example);
        iv_bar_code = findViewById(R.id.iv_bar_code);
        iv_machine = findViewById(R.id.iv_machine);
        iv_fault_location = findViewById(R.id.iv_fault_location);
        iv_new_and_old_accessories = findViewById(R.id.iv_new_and_old_accessories);
        ll_scan = findViewById(R.id.ll_scan);
        et_single_number = findViewById(R.id.et_single_number);
        tv_order_time=findViewById(R.id.tv_order_time);
        tv_work_order_number=findViewById(R.id.tv_work_order_number);
        tv_reason_pending_appointment=findViewById(R.id.tv_reason_pending_appointment);
        tv_service=findViewById(R.id.tv_service);
        tv_service_goods=findViewById(R.id.tv_service_goods);
        tv_service_address=findViewById(R.id.tv_service_address);

        //接收传进来的工单id
        orderID = getIntent().getStringExtra("OrderID");

        mPresenter.GetOrderInfo(orderID);

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
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()){

            case 200:
                data=baseResult.getData();
                tv_work_order_number.setText(data.getOrderID());
                tv_order_time.setText(data.getAudDate().replace("T"," ")); //将T替换为空格
                tv_reason_pending_appointment.setText(data.getMemo());
                tv_service_goods.setText(data.getCategoryName()+"/"+data.getBrandName()+"/"+data.getProductType());

                if (data.getTypeID().equals("1")){//维修
                    tv_service.setText("维修");
                    tv_service.setBackgroundResource(R.color.color_custom_01);
                }else {
                    tv_service.setText("安装");
                    tv_service.setBackgroundResource(R.color.color_custom_04);
                }
                tv_service_address.setText(data.getAddress());

                break;

            default:
                Log.d("detail",baseResult.getData().toString());
                //  data=null;
                break;
        }


    }

    public class CustomOnclickLister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
                    CompleteWorkOrderActivity.this.finish();
                    break;
                case R.id.ll_view_example:
                    final ViewExampleDialog viewExampleDialog=new ViewExampleDialog(mActivity);
                    viewExampleDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

                    viewExampleDialog.setNoOnclickListener("取消", new ViewExampleDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                            viewExampleDialog.dismiss();
                        }
                    });
                    viewExampleDialog.show();
                    Window window=viewExampleDialog.getWindow();
                    WindowManager.LayoutParams wlp=window.getAttributes();
                    Display d=window.getWindowManager().getDefaultDisplay();
                    wlp.height=d.getHeight();
                    wlp.width=d.getWidth();
                    wlp.gravity= Gravity.CENTER;
                    window.setAttributes(wlp);
                    break;
                case R.id.iv_bar_code:
                    showPopupWindow(101,102);
                    break;
                case R.id.iv_machine:
                    showPopupWindow(201,202);
                    break;
                case R.id.iv_fault_location:
                    showPopupWindow(301,303);
                    break;
                case R.id.iv_new_and_old_accessories:
                    showPopupWindow(401,404);
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

            }
        }
    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1,final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn= popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn= popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn= popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()){
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis()+".jpg";
                    String fileDir= Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy";
                    FilePath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy/"+f;
                    File dirfile=new File(fileDir);
                    if (!dirfile.exists()){
                        dirfile.mkdirs();
                    }
                    File file=new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity,"com.ying.administrator.masterappdemo.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);
                }else{
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()){
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    mPopupWindow.dismiss();
                }else{
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
                MyUtils.setWindowAlpa(mActivity,false);
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
    private boolean requestPermissions(){
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
            et_single_number.setText("单号:"+result);
        }
        File file = null;
        switch (requestCode){
            //拍照
            case 101:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_bar_code);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_bar_code);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 201:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_machine);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_machine);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 301:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_fault_location);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 303:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_fault_location);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 401:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_new_and_old_accessories);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 404:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_new_and_old_accessories);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;

        }
//        if (file!=null){
//            uploadImg(file);
//        }
    }
//    public void uploadImg(File f){
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
//        MultipartBody requestBody=builder.build();
//        mPresenter.UploadImg(requestBody);
//    }
//
}

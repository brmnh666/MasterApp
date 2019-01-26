package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.entity.Media;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;
import com.ying.administrator.masterappdemo.mvp.model.VerifiedModel;
import com.ying.administrator.masterappdemo.mvp.presenter.VerifiedPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Verified_Activity extends BaseActivity<VerifiedPresenter,VerifiedModel> implements View.OnClickListener,VerifiedContract.View {


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
    @BindView(R.id.bank_card_ll)
    LinearLayout mBankCardLl;
    @BindView(R.id.actual_name_et)
    EditText mActualNameEt;
    @BindView(R.id.id_number_et)
    EditText mIdNumberEt;
    @BindView(R.id.iv_positive)
    ImageView mIvPositive;
    @BindView(R.id.iv_negative)
    ImageView mIvNegative;
    @BindView(R.id.ll_shop_address)
    LinearLayout mLlShopAddress;
    @BindView(R.id.submit_application_bt)
    Button mSubmitApplicationBt;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    ArrayList<Media> select=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_verified;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
        mIvPositive.setOnClickListener(this);
        mIvNegative.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_positive:
                showPopupWindow(101,102);
                break;
            case R.id.iv_negative:
                showPopupWindow(201,202);
                break;
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
                    String fileDir=Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy";
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
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "test"), code2);
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


    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 10001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED) {//允许
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis()+".jpg";
                    FilePath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy/"+f;
                    File file=new File(FilePath);
                    if (!file.exists()){
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Uri fileUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, 0);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
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
        switch (requestCode){
            //拍照
            case 101:
                Glide.with(mActivity).load(FilePath).into(mIvPositive);
                file=new File(FilePath);
                break;
                //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvPositive);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 201:
                Glide.with(mActivity).load(FilePath).into(mIvNegative);
                file=new File(FilePath);
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvNegative);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
        }
        if (file!=null){
            uploadImg(file);
        }
    }
    public void uploadImg(File f){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        MultipartBody requestBody=builder.build();
        mPresenter.UploadImg(requestBody);
    }

    @Override
    public void UploadImg(BaseResult<String> baseResult) {
        MyUtils.showToast(mActivity,"上传成功！");
    }
    /*@Override
    public void Login(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                SPUtils spUtils = SPUtils.getInstance("token");
                spUtils.put("adminToken", baseResult.getData());
                spUtils.put("userName", userName);

                //  Log.d("loginlogin",baseResult.getData());
//                GetUserInfo getUserInfo=new GetUserInfo(userName,baseResult.getData(),"","");
//                Gson gson=new Gson();
//                RequestBody json=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),gson.toJson(getUserInfo));
//                mPresenter.GetUserInfo(json);
//                mPresenter.GetUserInfo(userName);
                //Log.d("TokenTokenToken",Config.TOKEN);


//token在设备卸载重装的时候有可能会变

                mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"7",userName);
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
//                        Config.TOKEN= (String) data;
                //SPUtils spUtils = SPUtils.getInstance("token");
                //spUtils.put("Token", (String) data);
//
                // String token = spUtils.getString("Token");

                // Log.d("Token",token);





                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }
    *//*获取用户消息*//*
    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e("userInfo", baseResult.getData());
                ToastUtils.showShort(baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e("AddAndUpdatePushAccount", baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }*/
}

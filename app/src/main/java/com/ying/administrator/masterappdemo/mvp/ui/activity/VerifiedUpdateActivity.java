package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.searchdemo.MainActivity;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.entity.Media;
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
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*实名认证*/
public class VerifiedUpdateActivity extends BaseActivity<VerifiedPresenter, VerifiedModel> implements View.OnClickListener, VerifiedContract.View {


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
    @BindView(R.id.ll_service_skill)
    LinearLayout mLlServiceSkill;
    @BindView(R.id.tv_shop_address)
    TextView mTvShopAddress;
    @BindView(R.id.tv_skills)
    TextView mTvSkills;
    @BindView(R.id.iv_selfie)
    ImageView mIvSelfie;
    @BindView(R.id.ll_select_service_area)
    LinearLayout mLlSelectServiceArea;
    @BindView(R.id.tv_codestr)
    TextView mTvCodestr;
    @BindView(R.id.cb_under_warranty)
    CheckBox mCbUnderWarranty;
    @BindView(R.id.ll_under_warranty)
    LinearLayout mLlUnderWarranty;
    @BindView(R.id.cb_outside_the_warranty)
    CheckBox mCbOutsideTheWarranty;
    @BindView(R.id.ll_outside_the_warranty)
    LinearLayout mLlOutsideTheWarranty;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    ArrayList<Media> select = new ArrayList<>();

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int mLocationType;
    private double mLatitude;
    private double mLongitude;
    private float mAccuracy;
    private String mAddress = "";
    private String mCountry;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String mStreet;
    private String mStreetNum;
    private String mCityCode;
    private String mAdCode;
    private String mAoiName;
    private String mBuildingId;
    private String mFloor;
    private int mGpsAccuracyStatus;
    private String mTime;
    private ObjectAnimator animator; //刷新图片属性动画
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            //定位结果回调
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    mLocationType = aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    mLatitude = aMapLocation.getLatitude();//获取纬度
                    mLongitude = aMapLocation.getLongitude();//获取经度
                    mAccuracy = aMapLocation.getAccuracy();//获取精度信息
                    mAddress = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    mCountry = aMapLocation.getCountry();//国家信息
                    mProvince = aMapLocation.getProvince();//省信息
                    mCity = aMapLocation.getCity();//城市信息
                    mDistrict = aMapLocation.getDistrict();//城区信息
                    mStreet = aMapLocation.getStreet();//街道信息
                    mStreetNum = aMapLocation.getStreetNum();//街道门牌号信息
                    mCityCode = aMapLocation.getCityCode();//城市编码
                    mAdCode = aMapLocation.getAdCode();//地区编码
                    mAoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    mBuildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    mFloor = aMapLocation.getFloor();//获取当前室内定位的楼层
                    mGpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    mTime = df.format(date);
                    if (mAddress != null) {
                        mTvShopAddress.setText(mAddress);
                    } else {
                        mTvShopAddress.setText(aMapLocation.getAddress());
                    }

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };

    private SPUtils spUtils;
    private String UserID;
    private String mActualName;
    private String mIdNumber;
    private String mSkills = "";
    private String NodeIds = "";
    private String mPositiveCard = "";
    private String mNegativeCard = "";
    private String mSelfie = "";
    private Uri uri;
    private int size;
    private String codestr = "";
    private String Guarantee="";
    private String Sex;
    private List<Uri> mSelected;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_verified_update;
    }

    public void Location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mActivity);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
/**
 * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
 */
        /*mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }*/
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        if (requestLocationPermissions()) {
            Location();
        } else {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 20002);
        }
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("修改实名认证");
        spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIvPositive.setOnClickListener(this);
        mIvNegative.setOnClickListener(this);
        mIvSelfie.setOnClickListener(this);
        mLlReturn.setOnClickListener(this);
        mLlShopAddress.setOnClickListener(this);
        mLlServiceSkill.setOnClickListener(this);
        mSubmitApplicationBt.setOnClickListener(this);
        mLlSelectServiceArea.setOnClickListener(this);

        mLlUnderWarranty.setOnClickListener(this);
        mLlOutsideTheWarranty.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_under_warranty:
                mCbUnderWarranty.setChecked(true);
                mCbOutsideTheWarranty.setChecked(false);
                Guarantee = "Y";
                break;
            case R.id.ll_outside_the_warranty:
                mCbUnderWarranty.setChecked(false);
                mCbOutsideTheWarranty.setChecked(true);
                Guarantee = "N";
                break;
            case R.id.ll_return:
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
            case R.id.ll_shop_address:
                startActivityForResult(new Intent(mActivity, MainActivity.class), 100);
                break;
            case R.id.ll_service_skill:
                startActivityForResult(new Intent(mActivity, MySkillsActivity.class), 1000);
                break;
            case R.id.submit_application_bt:
                mActualName = mActualNameEt.getText().toString();
                mIdNumber = mIdNumberEt.getText().toString();

                if ("".equals(mActualName)) {
                    ToastUtils.showShort("请输入真实姓名！");
                    return;
                }
                if ("".equals(mIdNumber)) {
                    ToastUtils.showShort("请输入身份证号码！");
                    return;
                }
                if (!RegexUtils.isIDCard18Exact(mIdNumber)) {
                    ToastUtils.showShort("身份证号码格式错误！");
                    return;
                }
                if ("".equals(mPositiveCard)) {
                    ToastUtils.showShort("请添加正面身份证照片！");
                    return;
                }
                if ("".equals(mNegativeCard)) {
                    ToastUtils.showShort("请添加反面身份证照片！");
                    return;
                }
                if ("".equals(mSelfie)) {
                    ToastUtils.showShort("请添加清晰自拍照！");
                    return;
                }
                if ("".equals(mSkills)) {
                    ToastUtils.showShort("请添加你的服务技能！");
                    return;
                }
                if ("".equals(mAddress)) {
                    ToastUtils.showShort("未定位到店铺地址！");
                    return;
                }
                if ("".equals(codestr)) {
                    ToastUtils.showShort("请添加服务区域！");
                    return;
                }
                if ("".equals(Guarantee)) {
                    ToastUtils.showShort("请选择服务起点！");
                    return;
                }
                if ("Y".equals(Guarantee)) {
                    showProgress();
                    mPresenter.ApplyAuthInfo(UserID, mActualName, Sex, mIdNumber, mAddress, NodeIds, mProvince, mCity, mDistrict, mStreet, Double.toString(mLongitude), Double.toString(mLatitude), codestr,"2");
                }else{
                    showProgress();
                    mPresenter.ApplyAuthInfo(UserID, mActualName, Sex, mIdNumber, mAddress, NodeIds, mProvince, mCity, mDistrict, mStreet, "", "", codestr,"2");
                }
                break;
            case R.id.ll_select_service_area:
                startActivityForResult(new Intent(mActivity, AddServiceAreaUpdataActivity.class), 317);
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
                Matisse.from(VerifiedUpdateActivity.this)
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


    //请求定位权限
    private boolean requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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
            case 20002:
                if (size == grantResults.length) {//允许
                    Location();
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
                }
                if (file != null) {
                    uploadImg(file, 0);
                }
                break;
            //相册
            case 102:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size()==1){
                        uri = mSelected.get(0);
                    }
//                    uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvPositive);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    uploadImg(file, 0);
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvNegative);
                    file = new File(FilePath);
                }
                if (file != null) {
                    uploadImg(file, 1);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size()==1){
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvNegative);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    uploadImg(file, 1);
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvSelfie);
                    file = new File(FilePath);
                }
                if (file != null) {
                    uploadImg(file, 2);
                }
                break;
            //相册
            case 302:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size()==1){
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvSelfie);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    uploadImg(file, 2);
                }
                break;
            case 100:
                if (data != null) {
                    mAddress = data.getStringExtra("address");
                    mProvince = data.getStringExtra("Province");
                    mCity = data.getStringExtra("City");
                    mDistrict = data.getStringExtra("Area");
                    mStreet = data.getStringExtra("District");
                    mLongitude = data.getDoubleExtra("Longitude", -1);
                    mLatitude = data.getDoubleExtra("Dimension", -1);
                    if (mAddress != null) {
                        mTvShopAddress.setText(mAddress);
                    }
                } else {
                    mTvShopAddress.setText(mAddress);
                }
                break;
            case 1000:
                if (data != null) {
                    mSkills = data.getStringExtra("skills");
                    NodeIds = data.getStringExtra("NodeIds");
                    if (mSkills != null) {
                        mTvSkills.setText(mSkills);
                    }
                }
                break;
            case 317:
                if (data != null) {
                    codestr = data.getStringExtra("codestr");
                    if (codestr != null) {
                        mTvCodestr.setText("已添加服务区域");
                    }
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

    /*如果是子账号不选择地址*/
    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void ApplyAuthInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");

                    EventBus.getDefault().post("GetUserInfoList");
                    finish();
                }
                break;
            default:
                hideProgress();
                ToastUtils.showShort("提交失败");
                break;
        }
    }

    @Override
    public void ApplyAuthInfoBysub(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

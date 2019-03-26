package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.InfoManageContract;
import com.ying.administrator.masterappdemo.mvp.model.InfoMangeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.InfoManagePresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.util.imageutil.FileUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Personal_Information_Activity extends BaseActivity<InfoManagePresenter, InfoMangeModel> implements InfoManageContract.View, View.OnClickListener {
    private LinearLayout ll_return;
    private LinearLayout ll_avatar;
    private LinearLayout ll_nickname;
    private LinearLayout ll_password;
    private LinearLayout ll_my_skills;
    private LinearLayout ll_male; //男
    private LinearLayout ll_female;//女
    private TextView tv_actionbar_title;
    private ImageView img_actionbar_message;
    private String userID;//用户id
    private ImageView iv_avatar;//头像
    private TextView tv_nickname;//昵称
    private TextView tv_name;//真实姓名
    private TextView tv_certification;//已认证
    private TextView tv_un_certification;//未认证
    private TextView tv_phone;//手机号
    private TextView tv_id_card;//身份证
    private TextView tv_shop_address;//身份证
    private ImageView img_male_select;//男图选中
    private ImageView img_male_unselect;//男图未选中
    private ImageView img_female_select;//女图选中
    private ImageView img_female_unselect;//女图未选中
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private String FilePath;
    private String Path;
    private int size;
    private Uri uri;
    private ArrayList<String> permissions;
    private ArrayList<IDCard.IDCardBean> idCardBeans=new ArrayList<>();
    SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();
    private LinearLayout ll_select_service_area;
    private LinearLayout ll_under_warranty;
    private CheckBox cb_under_warranty;
    private LinearLayout ll_outside_the_warranty;
    private CheckBox cb_outside_the_warranty;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        mPresenter.GetUserInfoList(userID,"1");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initData() {
        userID=spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID,"1");
        mPresenter.GetIDCardImg(userID);
    }

    @Override
    protected void initView() {
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setText("个人信息管理");
        img_actionbar_message=findViewById(R.id.img_actionbar_message);
        img_actionbar_message.setVisibility(View.INVISIBLE);
        ll_return=findViewById(R.id.ll_return);
        iv_avatar=findViewById(R.id.iv_avatar);//头像
        ll_avatar=findViewById(R.id.ll_avatar);//头像栏
        tv_nickname=findViewById(R.id.tv_nickname);//昵称
        tv_name=findViewById(R.id.tv_name);//真实姓名
        tv_certification=findViewById(R.id.tv_certification);//已认证
        tv_un_certification=findViewById(R.id.tv_un_certification);//未认证
        tv_phone=findViewById(R.id.tv_phone);//手机号
        tv_id_card=findViewById(R.id.tv_id_card);//身份证
        tv_shop_address=findViewById(R.id.tv_shop_address);//店铺地址
        img_male_select=findViewById(R.id.img_male_select);
        img_male_unselect=findViewById(R.id.img_male_unselect);
        img_female_select=findViewById(R.id.img_female_select);
        img_female_unselect=findViewById(R.id.img_female_unselect);
        ll_nickname=findViewById(R.id.ll_nickname);
        ll_password=findViewById(R.id.ll_password);
        ll_my_skills=findViewById(R.id.ll_my_skills);
        ll_male=findViewById(R.id.ll_male);
        ll_female=findViewById(R.id.ll_female);
        ll_select_service_area = findViewById(R.id.ll_select_service_area);
        ll_under_warranty = findViewById(R.id.ll_under_warranty);
        cb_under_warranty = findViewById(R.id.cb_under_warranty);
        ll_outside_the_warranty = findViewById(R.id.ll_outside_the_warranty);
        cb_outside_the_warranty = findViewById(R.id.cb_outside_the_warranty);
    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(this);
        ll_avatar.setOnClickListener(this);
        ll_nickname.setOnClickListener(this);
        ll_password.setOnClickListener(this);
        ll_my_skills.setOnClickListener(this);
        ll_male.setOnClickListener(this);
        ll_female.setOnClickListener(this);
        ll_select_service_area.setOnClickListener(this);
        ll_under_warranty.setOnClickListener(this);
        ll_outside_the_warranty.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_return:
                Personal_Information_Activity.this.finish();
                break;
            case R.id.ll_avatar:
                if (requestPermissions()){
                    showPopupWindow(101, 102);
                }else{
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;

            case R.id.ll_nickname:
                //跳转到修改昵称的页面
                startActivity(new Intent(this,ChageUserNameActivity.class));
                break;
            case R.id.ll_password:
                //跳转到修改密码的页面
                startActivity(new Intent(this,ChagePasswordActivity.class));
                break;
            case R.id.ll_my_skills:

                if (userInfo.getIfAuth()==null){//未实名认证
                 return;
                }else {
                    startActivity(new Intent(this,MyInfoSkillsActivity.class));
                }

                break;
            case R.id.ll_male: //选择了男性
                if (userInfo.getSex()==null){//调用接口
                    changeSex("男");
                }else if (userInfo.getSex().equals("女")){//调用接口
                    changeSex("男");
                }else { //原本是男性不操作
                  return;
                }

            break;

            case R.id.ll_female: //选择了女性

                if (userInfo.getSex()==null){//调用接口
                    changeSex("女");
                }else if (userInfo.getSex().equals("男")){//调用接口
                    changeSex("女");
                }else { //原本是女性不操作
                    return;
                }

                break;

            case R.id.ll_select_service_area:
                startActivity(new Intent(mActivity, AddServiceAreaInfoActivity.class));
                break;
            case R.id.ll_under_warranty:
                cb_under_warranty.setChecked(true);
                cb_outside_the_warranty.setChecked(false);
                break;
            case R.id.ll_outside_the_warranty:
                cb_outside_the_warranty.setChecked(true);
                cb_under_warranty.setChecked(false);
                break;

                default:
                    break;
        }

    }

    /*获取个人信息*/
    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo=baseResult.getData().getData().get(0);
                /*设置头像*/
                if (userInfo.getAvator()==null){//显示默认头像
                    return;
                }else {
                    Glide.with(mActivity)
                            .load(Config.HEAD_URL+userInfo.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(iv_avatar);
                }
                /*设置昵称*/
                if (userInfo.getNickName()==null){//测试账号可能出现昵称为null的情况 这里暂不处理
                    return;

                }else {
                    tv_nickname.setText(userInfo.getNickName());
                }
                /*真实姓名*/
                if (userInfo.getTrueName()==null){ //如果为空说明未认证
                    tv_name.setVisibility(View.INVISIBLE);
                    tv_certification.setVisibility(View.GONE);
                    tv_un_certification.setVisibility(View.VISIBLE);

                }else {
                    tv_name.setText(userInfo.getTrueName());
                    tv_certification.setVisibility(View.VISIBLE);
                    tv_un_certification.setVisibility(View.GONE);
                }
                /*手机号*/
                if (userInfo.getPhone()==null){
                    tv_phone.setText("");
                }else {
                    tv_phone.setText(userInfo.getPhone());
                }
                /*身份证*/
                if (userInfo.getIDCard()==null){
                    tv_id_card.setText("");
                }else {
                    StringBuilder sb=new StringBuilder(userInfo.getIDCard());
                    sb.replace(6,14,"********");
                    tv_id_card.setText(sb.toString());
                }
                /*店铺地址*/
                if (userInfo.getAddress()==null){
                    tv_shop_address.setText("");
                }else {
                    tv_shop_address.setText(userInfo.getAddress());
                }
                /*性别*/
                if (userInfo.getSex()==null){
                    img_male_unselect.setVisibility(View.VISIBLE);
                    img_male_select.setVisibility(View.GONE);
                    img_female_select.setVisibility(View.GONE);
                    img_female_unselect.setVisibility(View.VISIBLE);
                }else if (userInfo.getSex().equals("男")){
                    img_male_unselect.setVisibility(View.GONE);
                    img_male_select.setVisibility(View.VISIBLE);
                    img_female_unselect.setVisibility(View.VISIBLE);
                    img_female_select.setVisibility(View.GONE);
                }else
                {//女
                    img_male_unselect.setVisibility(View.VISIBLE);
                    img_male_select.setVisibility(View.GONE);
                    img_female_unselect.setVisibility(View.GONE);
                    img_female_select.setVisibility(View.VISIBLE);

                }

                break;
                default:
                    break;
        }
    }

    /*更新头像*/
    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (!baseResult.getData().isItem1()){

                    Toast.makeText(Personal_Information_Activity.this,"图片上传失败",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(Personal_Information_Activity.this,"图片上传成功",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("");
                }

                break;

                default:
                    Toast.makeText(mActivity,"修改失败",Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    /*获取身份证图片*/
    @Override
    public void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isEmpty()){
                    return;
                }else {
                    idCardBeans.addAll(baseResult.getData());
                   // Log.d("身份图片的张数", String.valueOf(idCardBeans.size()));
                }

                break;
                default:
                    break;
        }
    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {
     switch (baseResult.getStatusCode()){

         case 200:

             break;
             default:
                 Toast.makeText(Personal_Information_Activity.this,"性别修改失败",Toast.LENGTH_SHORT).show();
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
            if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size ==grantResults.length) {//允许
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10002:
                if (size ==grantResults.length) {//允许
                    showPopupWindow(201, 202);
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
        switch (requestCode) {
            //拍照获取图片
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_avatar);
                    file = new File(FilePath);
                    startCrop(Uri.fromFile(file));
                }

                break;
            //从相册中获取
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_avatar);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                   startCrop(Uri.fromFile(file));
                }
              /*  if (file!=null){
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    uploadImg(newFile);
                }*/

                break;
            //裁剪后的效果
            case UCrop.REQUEST_CROP:
                if (resultCode==RESULT_OK){
                    Uri resultUri=UCrop.getOutput(data);

                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                       // headimageView.setImageBitmap(bitmap);
                        Glide.with(mActivity).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_avatar);

                        File file1=uritoFile(resultUri);

                        if (file1!=null){
                            File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                            uploadImg(newFile);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                //错误裁剪的结果
            case UCrop.RESULT_ERROR:
                if(resultCode==RESULT_OK){
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                }
                break;


                default:
                    break;
        }

    }

    //处理剪切失败的返回值
    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "output_iamge.jpg");
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }

    public void uploadImg(File f) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        builder.addFormDataPart("UserID", userID);
        //builder.addFormDataPart("Sort", (code+1)+"");
        MultipartBody requestBody = builder.build();
        mPresenter.UploadAvator(requestBody);
    }

    public void changeSex(String target){
       if (target.equals("男")){//变为男性
           img_male_unselect.setVisibility(View.GONE);
           img_male_select.setVisibility(View.VISIBLE);
           img_female_unselect.setVisibility(View.VISIBLE);
           img_female_select.setVisibility(View.GONE);
           mPresenter.UpdateSex(userID,"男");
           userInfo.setSex("男");

       }else {  //变为女性
           img_male_unselect.setVisibility(View.VISIBLE);
           img_male_select.setVisibility(View.GONE);
           img_female_unselect.setVisibility(View.GONE);
           img_female_select.setVisibility(View.VISIBLE);
           mPresenter.UpdateSex(userID,"女");
           userInfo.setSex("女");
       }

    }


    //图片裁剪的方法
    private void startCrop(Uri uri){
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(getExternalCacheDir(), "uCrop.jpg"));
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1,1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.ALL);
        options.setToolbarTitle("头像裁剪");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
        options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);
    }


    private File uritoFile(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }


}

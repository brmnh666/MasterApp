package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.moor.imkf.eventbus.EventBus;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.AccessoriesName;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;
import com.ying.administrator.masterappdemo.mvp.model.NewAddAccessoriesModel;
import com.ying.administrator.masterappdemo.mvp.presenter.NewAddAccessoriesPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.AccessoriesPictureAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.NewAddAccessoriesAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter2;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccessoriesPictureActivity extends BaseActivity<NewAddAccessoriesPresenter, NewAddAccessoriesModel> implements View.OnClickListener, NewAddAccessoriesContract.View,Pre_order_Add_Ac_Adapter2.OnItemEditTextChangedListener {
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
    @BindView(R.id.rv_accessories_list)
    RecyclerView mRvAccessoriesList;
    @BindView(R.id.rv_accessories_picture)
    RecyclerView mRvAccessoriesPicture;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 用于存放预接单页面显示的数据
    private Pre_order_Add_Ac_Adapter2 mPre_order_add_ac_adapter;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private AccessoriesPictureAdapter accessoriesPictureAdapter;
    private View popupWindow_view;
    private String FilePath;
    private ArrayList<Object> permissions;
    private PopupWindow mPopupWindow;
    private HashMap<Integer, File> accessories_picture = new HashMap<>();
    private Map<Integer, Accessory> map_collect = new HashMap<>();//收藏的配件map集合
    private List<Uri> mSelected;
    private Uri uri;
    private int pos;
    private List<AccessoriesName> nameList;
    private int postion;
    private ArrayList<Accessory> list;
    private NewAddAccessoriesAdapter newAddAccessoriesAdapter;
    private List<Accessory> list_collect = new ArrayList<>(); //收藏的配件
    private HashMap<Integer, String> img_list = new HashMap<>(); //图片集合
    private String select_state;
    private List<String> contents;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accessories_picture;
    }

    @Override
    protected void initData() {

        list = (ArrayList<Accessory>) getIntent().getSerializableExtra("list_collect");
        select_state = getIntent().getStringExtra("select_state");
        for (int i = 0; i < list.size(); i++) {
            mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
            mfAccessory.setFAccessoryID(list.get(i).getFAccessoryID());//获取id
            mfAccessory.setFAccessoryName(list.get(i).getAccessoryName()); //获取名字
            mfAccessory.setFCategoryID(list.get(i).getFCategoryID() + ""); //分类id
            mfAccessory.setQuantity(list.get(i).getCount() + ""); //数量 默认数字为1
            mfAccessory.setPrice(Double.valueOf("0"));//原价
            mfAccessory.setDiscountPrice(Double.valueOf("0"));//折扣价
            mfAccessory.setSizeID(list.get(i).getSizeID());//小修中修大修
            mfAccessory.setSendState("N");
            mfAccessory.setRelation("");
            mfAccessory.setState("0");
            mfAccessory.setIsPay("N");
            mfAccessory.setExpressNo("");
            mfAccessory.setNeedPlatformAuth("N");
            mfAccessory.setPrice(list.get(i).getAccessoryPrice());//原价
            mfAccessory.setDiscountPrice(list.get(i).getAccessoryPrice());//原价
            fAcList.add(mfAccessory);
        }
        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter2(R.layout.item_pre_order_add_accessories2, fAcList, "0", select_state);
        mPre_order_add_ac_adapter.setListener(this);
        mRvAccessoriesList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAccessoriesList.setAdapter(mPre_order_add_ac_adapter);
        contents = mPre_order_add_ac_adapter.contents;
        nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(new AccessoriesName(list.get(i).getAccessoryName(), null));
        }
        nameList.add(new AccessoriesName("主机", null));
        accessoriesPictureAdapter = new AccessoriesPictureAdapter(R.layout.item_accessories_picture, nameList);
        if (nameList.size() == 2) {
            mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity, 2));
        } else if (nameList.size() == 3) {
            mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity, 3));
        } else {
            mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity, 4));
        }

        mRvAccessoriesPicture.setAdapter(accessoriesPictureAdapter);
        accessoriesPictureAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (view.getId()) {
                    case R.id.iv_host:
                        showPopupWindow(801, 809);
                        break;
                }
            }
        });

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("配件图片");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_sure:
                if (img_list.size()!=nameList.size()) {
                    ToastUtils.showShort("未添加全部图片或图片上传失败，请重试");
                    return;
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setImg1(img_list.get(i));//配件照片
                        list.get(i).setImg2(img_list.get(list.size()));//整机照片
                    }
                    if ("1".equals(select_state)){
                        for (int i = 0; i < list.size(); i++) {
                            if (fAcList.get(i).getPrice()==0){
                                ToastUtils.showShort("请输入配件价格");
                                return;
                            }
                            list.get(i).setAccessoryPrice(fAcList.get(i).getPrice());
                        }
                    }
                    Intent intent = new Intent();
                    intent.putExtra("list_collect", (Serializable) list);
                    setResult(Config.APPLY_RESULT, intent);
                    finish();
                }



                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                    Matisse.from(AccessoriesPictureActivity.this)
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
        File file = null;
        switch (requestCode) {
            //====配件照片
            //拍照
            case 801:
                if (resultCode == -1) {
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(pos, newFile);
                    nameList.get(pos).setPic(newFile);
                    accessoriesPictureAdapter.setNewData(nameList);
                    ApplyAccessoryphotoUpload(newFile);
                }
                break;
            //相册
            case 809:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(pos, newFile);
                    nameList.get(pos).setPic(newFile);
                    accessoriesPictureAdapter.setNewData(nameList);
                    ApplyAccessoryphotoUpload(newFile);
                }
                break;
        }
    }

    /**
     * 添加配件图片
     *
     */
    public void ApplyAccessoryphotoUpload(File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("img/png"), file));
        MultipartBody requestBody = builder.build();
        mPresenter.ApplyAccessoryphotoUpload(requestBody);
    }

    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void ApplyAccessoryphotoUpload(BaseResult<Data2> baseResult) {
        if (baseResult.getStatusCode() == 200) {
            img_list.put(pos,baseResult.getData().getItem1());
        }
    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void onEditTextAfterTextChanged(String price, int position) {
        mfAccessory = fAcList.get(position);
        mfAccessory.setPrice(Double.parseDouble(price));
    }
}

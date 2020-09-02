package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.PreviewActivity;
import com.dmcbig.mediapicker.entity.Media;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.AccPicResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AddAddressActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Ac_List_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.PicAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.ImageCompress;
import com.ying.administrator.masterappdemo.v3.bean.ApplicationRequest;
import com.ying.administrator.masterappdemo.v3.bean.ApplicationResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ApplyAccPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ApplyAccContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ApplyAccModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

/**
 * 新版申请配件
 */

public class ApplyAccActivity extends BaseActivity<ApplyAccPresenter, ApplyAccModel> implements View.OnClickListener, ApplyAccContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_add_acc)
    LinearLayout mLlAddAcc;
    @BindView(R.id.rv_acc)
    RecyclerView mRvAcc;
    @BindView(R.id.rv_icons)
    RecyclerView mRvIcons;
    @BindView(R.id.et_bak)
    EditText mEtBak;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.iv_me)
    ImageView mIvMe;
    @BindView(R.id.ll_me)
    LinearLayout mLlMe;
    @BindView(R.id.iv_user)
    ImageView mIvUser;
    @BindView(R.id.ll_user)
    LinearLayout mLlUser;
    @BindView(R.id.ll_receipttype)
    LinearLayout mLlReceipttype;
    @BindView(R.id.tv_adddr_me)
    TextView mTvAdddrMe;
    @BindView(R.id.ll_addr_me)
    LinearLayout mLlAddrMe;
    @BindView(R.id.tv_adddr_user)
    TextView mTvAdddrUser;
    @BindView(R.id.ll_addr_user)
    LinearLayout mLlAddrUser;
    private String SubCategoryID;
    private List<String> piclist = new ArrayList<>();
    private List<String> selectpiclist = new ArrayList<>();
    private List<String> permissions = new ArrayList<>();
    private List<String> successpiclist = new ArrayList<>();
    private PicAdapter picAdapter;
    private ArrayList<Media> select = new ArrayList<>();
    private int size;

    private Ac_List_Adapter ac_list_adapter;
    private List<Accessory> list_accessory = new ArrayList<>();// 添加的配件
    private String cj_or_zg;
    private String OrderID;
    private String msg;
    private List<AddressList> addressList;
    private SPUtils spUtils;
    private String UserID;
    private String sendAddr;
    private String prodName;
    private String prodID;
    private WorkOrder.DataBean dataBean;
    private int RecipientType = -1;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_apply_acc;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("userName");
        mRvAcc.setLayoutManager(new LinearLayoutManager(mActivity));
        ac_list_adapter = new Ac_List_Adapter(R.layout.item_accessory, list_accessory);
        mRvAcc.setAdapter(ac_list_adapter);

        dataBean = (WorkOrder.DataBean) getIntent().getSerializableExtra("order");
        mTvAdddrUser.setText("收件人信息："+dataBean.getAddress()+"（"+dataBean.getUserName()+" 收）"+dataBean.getPhone());
        cj_or_zg = getIntent().getStringExtra("cj_or_zg");
        prodName = getIntent().getStringExtra("prodName");
        prodID = getIntent().getStringExtra("prodID");
        if ("厂寄".equals(cj_or_zg)) {
            mTvTitle.setText("厂家寄件申请(" + prodName + ")");
            mLlReceipttype.setVisibility(View.VISIBLE);
            mPresenter.GetAccountAddress(UserID);
        } else {
            mTvTitle.setText("师傅自购件申请(" + prodName + ")");
            mLlReceipttype.setVisibility(View.GONE);
        }
        OrderID = getIntent().getStringExtra("OrderID");
        SubCategoryID = getIntent().getStringExtra("SubCategoryID");

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
                        if (list_accessory.size() == 0) {
                            ToastUtils.showShort("请先添加配件");
                            return;
                        }
                        if ("add".equals(adapter.getItem(position))) {
                            if (requestPermissions()) {
                                goImage();
                            } else {
                                requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                            }
                        } else {
                            goPreviewActivity();
                        }
                }
            }
        });
    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    sendAddr = addressList.get(0).getProvince() + addressList.get(0).getCity() + addressList.get(0).getArea() + addressList.get(0).getDistrict() + addressList.get(0).getAddress() + "(" + addressList.get(0).getUserName() + "收)" + addressList.get(0).getPhone();
                    mTvAdddrMe.setText("收件人信息：" + sendAddr);
                } else {
                    mTvAdddrMe.setText("请添加收件人信息");
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void Application(ApplicationResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isStatus()) {
                    ToastUtils.showShort("提交成功");
                    EventBus.getDefault().post(21);
                    finish();
                } else {
                    MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                }
                hideProgress();
                break;
            default:
                hideProgress();
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

    }

    private void loadAdpater(ArrayList<String> paths) {
        piclist.clear();
        piclist.addAll(paths);
        selectpiclist.clear();
        for (int i = 0; i < paths.size(); i++) {
            selectpiclist.add(ImageCompress.compressImage(paths.get(i), Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg", 80));
        }
        if (piclist.size() != list_accessory.size() * 3) {
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
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, list_accessory.size() * 3 - piclist.size() + 1);  //default 40 (Optional)
//        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,select); // (Optional)
        startActivityForResult(intent, 200);
    }

    void goPreviewActivity() {
        Intent intent = new Intent(mActivity, PreviewActivity.class);
        intent.putExtra(PickerConfig.PRE_RAW_LIST, select);//default image and video (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, select.size());//default image and video (Optional)
        startActivityForResult(intent, 300);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlAddAcc.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mLlAddrMe.setOnClickListener(this);
        mLlMe.setOnClickListener(this);
        mLlUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_me://师傅收件
                RecipientType = 2;   //1用户 2师傅
                mIvMe.setSelected(true);
                mIvUser.setSelected(false);
                mLlAddrMe.setVisibility(View.VISIBLE);
                mLlAddrUser.setVisibility(View.GONE);
                break;
            case R.id.ll_user://用户收件
                RecipientType = 1;   //1用户 2师傅
                mIvMe.setSelected(false);
                mIvUser.setSelected(true);
                mLlAddrMe.setVisibility(View.GONE);
                mLlAddrUser.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_addr_me:
                try {
                    if (addressList.size() == 0) {
                        startActivityForResult(new Intent(mActivity, AddAddressActivity.class), 500);
                    } else {
                        Intent intent = new Intent(mActivity, AddAddressActivity.class);
                        intent.putExtra("address", addressList.get(0));
                        startActivityForResult(intent, 500);
                    }
                } catch (Exception e) {
                    return;
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_add_acc:
                Intent intent = new Intent(mActivity, AccListActivity.class);
                intent.putExtra("cj_or_zg", cj_or_zg);
                intent.putExtra("SubCategoryID", SubCategoryID);
                startActivityForResult(intent, 100);
                break;
            case R.id.btn_submit:
                if ("厂寄".equals(cj_or_zg)) {
                    if (RecipientType == -1) {
                        MyUtils.showToast(mActivity, "请选择师傅收件还是用户收件");
                        return;
                    }
                    if (RecipientType==2){
                        if (addressList.size() == 0) {
                            MyUtils.showToast(mActivity, "请添加收件人信息");
                            return;
                        }
                    }
                }
                if (list_accessory.size() == 0) {
                    MyUtils.showToast(mActivity, "请添加配件");
                    return;
                }
                msg = mEtBak.getText().toString();
                if (selectpiclist.size() < list_accessory.size()) {
                    MyUtils.showToast(mActivity, "请至少添加" + list_accessory.size() + "张配件照片");
                    return;
                }
                showProgress();
                uploadImg();
                break;
        }
    }

    //上传配件图片
    public void uploadImg() {
        successpiclist.clear();
        for (int i = 0; i < selectpiclist.size(); i++) {
            File file = new File(selectpiclist.get(i));
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("img/png"), file));
            MultipartBody requestBody = builder.build();
            //接口
            String path = Config.BASE_URL + "Upload/ApplyAccessoryphotoUpload";
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();
            final Request request = new Request.Builder()
                    .url(path)
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    hideProgress();
                    ToastUtils.showShort("配件图片上传失败，请稍后重试");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String str = response.body().string();
                    System.out.println(str);
                    Gson gson = new Gson();
                    AccPicResult result = gson.fromJson(str.replaceAll(" ", ""), AccPicResult.class);
                    if (result.getStatusCode() == 200) {
                        successpiclist.add(result.getData().getItem1());
                        if (successpiclist.size() == selectpiclist.size()) {//全部上传成功
                            List<ApplicationRequest.FAccessorysBean> FAccessorys = new ArrayList<>();
                            for (int i = 0; i < list_accessory.size(); i++) {
                                Accessory accessory = list_accessory.get(i);
                                ApplicationRequest.FAccessorysBean acc = new ApplicationRequest.FAccessorysBean();
                                acc.setFAccessoryID(accessory.getFAccessoryID());
                                acc.setFAccessoryName(accessory.getAccessoryName());
                                acc.setPrice(Double.toString(accessory.getAccessoryPrice()));
                                acc.setQuantity(Integer.toString(accessory.getCount()));
                                FAccessorys.add(acc);
                            }
                            ApplicationRequest data = new ApplicationRequest();
                            data.setAccessoryState("厂寄".equals(cj_or_zg) ? 0 : 1);
                            data.setAccessorys(FAccessorys);
                            data.setRecipientType(RecipientType);
                            data.setImgUrls(successpiclist);
                            data.setOrderID(OrderID);
                            data.setOrderProdID(prodID);
                            data.setBak(msg);
                            String s = gson.toJson(data);
                            Log.d("申请配件", s);
                            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                            mPresenter.Application(body);
                        }
                    } else {
                        hideProgress();
                        ToastUtils.showShort("配件图片上传失败，请稍后重试");
                    }
                }
            });
        }
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
                    goImage();
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                    MyUtils.toSelfSetting(mActivity);
                }
                break;
            default:
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        if (requestCode == 200 && resultCode == PickerConfig.RESULT_CODE) {
            select.addAll((ArrayList) data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT));
            ArrayList<String> list = new ArrayList<>();
            Log.i("select", "select.size" + select.size());
            for (Media media : select) {
                list.add(media.path);
                Log.i("media", media.path);
                Log.e("media", "s:" + media.size);
            }
            loadAdpater(list);
        }
        if (requestCode == 300) {
            select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
            ArrayList<String> list = new ArrayList<>();
            Log.i("select", "select.size" + select.size());
            for (Media media : select) {
                list.add(media.path);
                Log.i("media", media.path);
                Log.e("media", "s:" + media.size);
            }
            loadAdpater(list);
        }
        //添加的配件
        if (requestCode == 100) {
            if (data == null) {
                return;
            }
            list_accessory = (List<Accessory>) data.getSerializableExtra("list");
            ac_list_adapter.setNewData(list_accessory);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"address".equals(message)) {
            return;
        }
        mPresenter.GetAccountAddress(UserID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


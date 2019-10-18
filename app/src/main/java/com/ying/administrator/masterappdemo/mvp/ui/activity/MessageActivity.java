package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.MessageContract;
import com.ying.administrator.masterappdemo.mvp.model.MessageModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MessagePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.LeaveMessageAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.LeaveMessageImgAdapter;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener, MessageContract.View {
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
    @BindView(R.id.message_rv)
    RecyclerView mMessageRv;
    @BindView(R.id.ll_message_list)
    LinearLayout mLlMessageList;
    @BindView(R.id.annex_iv)
    ImageView mAnnexIv;
    @BindView(R.id.et_message)
    EditText mEtMessage;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.iv_del)
    ImageView mIvDel;
    @BindView(R.id.ll_del)
    LinearLayout mLlDel;
    private String userID;
    private String orderId;
    private WorkOrder.DataBean data;
    private List<WorkOrder.LeavemessageListBean> list = new ArrayList<>();
    private LeaveMessageAdapter leaveMessageAdapter;
    private ArrayList<String> permissions;
    private int size;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private Uri uri;
    private HashMap<Integer, File> img = new HashMap<>();
    private String position;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("留言");

        SPUtils spUtils = SPUtils.getInstance("token");
        //获取用户id
        userID = spUtils.getString("userName");
        orderId = getIntent().getStringExtra("orderId");
        mPresenter.GetOrderInfo(orderId);

        leaveMessageAdapter = new LeaveMessageAdapter(R.layout.leave_message_item, list);
        mMessageRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mMessageRv.setAdapter(leaveMessageAdapter);
        leaveMessageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.img:
                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                        intent.putExtra("PhotoUrl", Config.Leave_Message_URL+((WorkOrder.LeavemessageListBean)adapter.getData().get(position)).getPhoto());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mAnnexIv.setOnClickListener(this);
        mLlDel.setOnClickListener(this);
        mEtMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.et_message:
                        // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                        if (canVerticalScroll(mEtMessage))
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }
                }
                return false;
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    public static  boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @SingleClick
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_submit:
                if (img.size()>0) {
                    uploadImg(img);
                }else{
                    String message = mEtMessage.getText().toString();
                    if (message == null || "".equals(message)) {
                        ToastUtils.showShort("请输入留言内容");
                    } else {
                        mPresenter.AddLeaveMessageForOrder(userID, orderId, message,"");
                    }
                }
                break;
            case R.id.annex_iv:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.ll_del:
                img.clear();
                Glide.with(mActivity)
                        .load(R.drawable.annex)
                        .into(mAnnexIv);
                mAnnexIv.setClickable(true);
                mLlDel.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void AddLeaveMessageForOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                mEtMessage.setText("");
                img.clear();
                Glide.with(mActivity)
                        .load(R.drawable.annex)
                        .into(mAnnexIv);
                mAnnexIv.setClickable(true);
                mLlDel.setVisibility(View.GONE);
                mPresenter.GetOrderInfo(orderId);
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                if (data.getLeavemessageList().size() == 0) {
                    mLlMessageList.setVisibility(View.GONE);
                } else {
                    mLlMessageList.setVisibility(View.VISIBLE);
                    list=data.getLeavemessageList();
                    Collections.reverse(list);
                    leaveMessageAdapter.setNewData(list);
//                    leaveMessageAdapter.setNewData(data.getLeavemessageList());
                }
                    break;
        }
    }

    @Override
    public void LeaveMessageImg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    String message = mEtMessage.getText().toString();
                    if (message == null || "".equals(message)) {
                        ToastUtils.showShort("请输入留言内容");
                    } else {
                        mPresenter.AddLeaveMessageForOrder(userID, orderId, message,baseResult.getData().getItem2());
                    }

                }
                break;
            default:
                ToastUtils.showShort("图片上传失败");
                break;
        }
    }

    @Override
    public void DeleteLeaveMessageImg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Glide.with(mActivity)
                        .load(R.drawable.annex)
                        .into(mAnnexIv);
                mAnnexIv.setClickable(true);
                mLlDel.setVisibility(View.GONE);
                break;
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
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10002:
                if (size == grantResults.length) {//允许
                    showPopupWindow(201, 202);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10003:
                if (size == grantResults.length) {//允许
                    showPopupWindow(301, 302);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            default:
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
                    fileUri = FileProvider.getUriForFile(mActivity, "com.ying.administrator.masterappdemo.fileProvider", file);
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
                Matisse.from(MessageActivity.this)
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mAnnexIv);
                    mLlDel.setVisibility(View.VISIBLE);
                    file = new File(FilePath);
//                    mAnnexIv.setClickable(false);
                }
                if (file != null) {

                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
//                    uploadImg(newFile, 0);
                    img.put(0, newFile);
                }
                break;
            //相册
            case 102:
                if (data != null) {
                    List<Uri> mSelected = Matisse.obtainResult(data);


                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mAnnexIv);
                    mLlDel.setVisibility(View.VISIBLE);
//                    mAnnexIv.setClickable(false);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
//                    uploadImg(newFile, 0);
                    img.put(0, newFile);
                }
                break;
        }
    }

    public void uploadImg(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        MultipartBody requestBody = builder.build();
        mPresenter.LeaveMessageImg(requestBody);

    }
}
